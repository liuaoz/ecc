package com.zjdex.service;

import com.alibaba.fastjson.JSONObject;
import com.zjdex.core.utils.HttpUtil;
import com.zjdex.core.utils.shujt.DesEncrypter;
import com.zjdex.dao.IdentityCheckRepository;
import com.zjdex.dao.OutInterfaceRepository;
import com.zjdex.dao.UserOutInterfaceRepository;
import com.zjdex.dao.UserRepository;
import com.zjdex.entity.OutInterface;
import com.zjdex.entity.RecNameCid;
import com.zjdex.entity.User;
import com.zjdex.entity.UserOutInterface;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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



    public RecNameCid trade(Long userId, String outInterfaceNo, RecNameCid rec) {

        //判断余额是否足够
        User user = userRepository.findOne(userId);

        OutInterface outInterface = outInterfaceRepository.findByOutInterfaceNo(outInterfaceNo);
        if (null == outInterface) {
            throw new RuntimeException("此接口不存在");
        }

        UserOutInterface userOutInterface =
            userOutInterfaceRepository.findByUserIdAndOutInterfaceId(userId, outInterface.getInInterfaceId());
        if (null == userOutInterface) {
            throw new RuntimeException("没有调用此接口的权限");
        }

        if (user.getAmount() - userOutInterface.getPrice() < 0) {
            throw new RuntimeException("余额不足");
        }

        RecNameCid recNameCid;
        recNameCid = identityCheckRepository.findByNameAndCid(rec.getName(), rec.getCid());

        if (null != recNameCid) {
            return recNameCid;
        } else {
            String respContent = getData(rec);
            recNameCid = parseData(respContent);
            identityCheckRepository.save(recNameCid);

            //扣款
            user.setAmount(user.getAmount() - userOutInterface.getPrice());
            userRepository.save(user);
        }
        return recNameCid;
    }

    /**
     * 从数据源获取数据
     *
     * @return
     */
    public String getData(RecNameCid recNameCid) {

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

    public RecNameCid parseData(String respContent) {
        JSONObject jsonobj = JSONObject.parseObject(respContent);
        RecNameCid rec = null;
        if (jsonobj.containsKey("resCode") && "0000".equals(jsonobj.getString("resCode"))) {
            JSONObject rec_data = jsonobj.getJSONObject("data");
            rec = new RecNameCid();
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
