package com.zjdex.service;

import com.alibaba.fastjson.JSONObject;
import com.zjdex.core.utils.shujt.DesEncrypter;
import com.zjdex.dao.*;
import com.zjdex.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by matrix_stone on 2017/1/12.
 */
@Service
public class IdentityCheckService {

    @Value("${sjt.apikey}")
    private String apikey;
    @Value("${sjt.url}")
    private String url;

    private static String NAME_CID_CHECK = "validateIDCard";

    @Autowired
    IdentityCheckRepository identityCheckRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserOutInterfaceRepository userOutInterfaceRepository;
    @Autowired
    OutInterfaceRepository outInterfaceRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    RelationOutInInterfaceRepository relationOutInInterfaceRepository;
    @Autowired
    SupplierInterfaceRepository supplierInterfaceRepository;
    @Autowired
    InputRepository inputRepository;


    public RecSjtNameCid trade(Long userId, String outInterfaceNo, RecSjtNameCid rec) {

        //判断余额是否足够
        User user = userRepository.findOne(userId);

        OutInterface outInterface = outInterfaceRepository.findByInterfaceNo(outInterfaceNo);
        if (null == outInterface) {
            throw new RuntimeException("此接口不存在");
        }

        UserOutInterface userOutInterface =
                userOutInterfaceRepository.findByUserIdAndOutInterfaceId(userId, outInterface.getId());
        if (null == userOutInterface) {
            throw new RuntimeException("没有调用此接口的权限");
        }

        if (user.getAmount().subtract(userOutInterface.getPrice()).compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("余额不足");
        }

        RecSjtNameCid recNameCid;
        recNameCid = identityCheckRepository.findByNameAndCid(rec.getName(), rec.getCid());

        if (null != recNameCid) {
            //扣款
            user.setAmount(user.getAmount().subtract(userOutInterface.getPrice()));
            //记录下游请求日志
            OutputLog outlog = new OutputLog();
            outlog.setUserId(userId);
            outlog.setOutInterfaceId(outInterface.getId());
            outlog.setParam(rec.getName() + "." + rec.getCid());
            outlog.setRespCode(recNameCid.getResCode());
            outlog.setIsFree("0");
            outlog.setPrice(userOutInterface.getPrice());
            outputRepository.save(outlog);
            return recNameCid;
        } else {
            String respContent = null;
            //查询数据源接口
            List<RelationOutInInterface> interfacesList = relationOutInInterfaceRepository.findByOutIdOrderByOrderNum(outInterface.getId());
            for (RelationOutInInterface relation : interfacesList) {
                SupplierInterface inInterface = supplierInterfaceRepository.findOne(relation.getInId());
                //按数据源优先顺序调用接口
                //TODO
                respContent = getData(rec);
                //记录上游请求日志
                InputLog inlog = new InputLog();
                inlog.setUserId(userId);
                inlog.setAmount(inInterface.getPrice());
                inlog.setResponse(respContent);
                inlog.setInInterfaceId(inInterface.getId());
                inputRepository.save(inlog);
            }


            recNameCid = parseData(respContent);
            identityCheckRepository.save(recNameCid);

            //扣款
            user.setAmount(user.getAmount().subtract(userOutInterface.getPrice()));
            userRepository.save(user);
        }

        //记录下游请求日志
        OutputLog outlog = new OutputLog();
        outlog.setUserId(userId);
        outlog.setOutInterfaceId(outInterface.getId());
        outlog.setParam(rec.getName() + "." + rec.getCid());
        outlog.setRespCode(recNameCid.getResCode());
        outlog.setIsFree("0");
        outlog.setPrice(userOutInterface.getPrice());
        outputRepository.save(outlog);

        return recNameCid;
    }

    /**
     * 从数据源获取数据
     *
     * @return
     */
    public String getData(RecSjtNameCid recNameCid) {

        String result = null;

        String param = "idCardName=" + recNameCid.getName() + "&idCardCode=" + recNameCid.getCid();

        StringBuffer sb = new StringBuffer(
                url + NAME_CID_CHECK + "?apikey=" + apikey + "&rettype=json&encryptParam=");

        try {
            String fullUrl = sb.append(DesEncrypter.encrypt(param, apikey)).toString();
            //            result = HttpUtil.sendGet(fullUrl, "UTF-8");
            result =
                    "{\"data\":{\"message\":\"一致\",\"result\":\"00\",\"idCardName\":\"王娟\",\"idCardCode\":\"431121199003108447\"},\"resCode\":\"0000\",\"resMsg\":\"SUCCESS\",\"orderNo\":\"20170112160746100031\"}";
            System.out.println("--result-->" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public RecSjtNameCid parseData(String respContent) {
        JSONObject jsonobj = JSONObject.parseObject(respContent);
        RecSjtNameCid rec = null;
        if (jsonobj.containsKey("resCode") && "0000".equals(jsonobj.getString("resCode"))) {
            JSONObject rec_data = jsonobj.getJSONObject("data");
            rec = new RecSjtNameCid();
            rec.setResCode(jsonobj.getString("resCode"));
            rec.setResMsg(rec_data.getString("message"));
            rec.setName(rec_data.getString("idCardName"));
            rec.setCid(rec_data.getString("idCardCode"));
            rec.setStatus(rec_data.getString("result"));
        } else {
            String status = jsonobj.getString("status");
            String message = jsonobj.getString("message");
            System.out.println("--error response:-->" + "status:" + status + ",message:" + message);
        }
        return rec;
    }
}
