package com.zjdex.service;

import com.zjdex.core.constant.ConstantDB;
import com.zjdex.core.exception.*;
import com.zjdex.dao.*;
import com.zjdex.entity.*;
import com.zjdex.entity.param.BaseParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.zjdex.core.constant.ConstantKey.*;

/**
 * Created by matrix_stone on 2017/1/16.
 */
public abstract class AbstractInputService<E extends BaseEntity, T extends BaseParam> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserOutInterfaceRepository userOutInterfaceRepository;
    @Autowired
    private OutInterfaceRepository outInterfaceRepository;
    @Autowired
    private RelationOutInInterfaceRepository relationOutInInterfaceRepository;
    @Autowired
    private SupplierInterfaceRepository supplierInterfaceRepository;
    @Autowired
    private InputRepository inputRepository;
    @Autowired
    private OutputRepository outputRepository;

    private boolean stopSwitch = false;//是否停止切换到备用数据源取数，false表示不停止

    protected String resCode;//数据源返回响应码。为"0000"表示为成功交易，不需要切换到下一个数据源


    protected Map<String, Object> params;

    public final E trade(T param) {
        E entity = null;
        //1.校验请求信息（用户、权限）
        checkUser(param.getUserId(), param.getOutInterfaceNo());

        OutInterface outInterface =
            outInterfaceRepository.findByInterfaceNo(param.getOutInterfaceNo());

        UserOutInterface userOutInterface =
            userOutInterfaceRepository.findByUserIdAndOutInterfaceId(param.getUserId(), outInterface.getId());

        //2.判断本地库是否有合法的数据
        entity = getLocalData(param);
        if (null != entity) {
            //记录下游日志
            OutputLog outlog = new OutputLog();
            outlog.setUserId(param.getUserId());
            outlog.setOutInterfaceId(outInterface.getId());
            //            outlog.setParam(param.get + "." + param.getCid());
            outlog.setRespCode(ConstantDB.OUT_RESP_SUCCESS);
            outlog.setIsFree(ConstantDB.CHARGE_NOT_FREE);
            outlog.setIsOwner(ConstantDB.DATA_FROM_LOCAL);
            outlog.setPrice(userOutInterface.getPrice());
            outputRepository.save(outlog);

            //扣款
            User user = userRepository.findOne(param.getUserId());
            user.setAmount(user.getAmount().subtract(userOutInterface.getPrice()));
            userRepository.save(user);
            return entity;
        } else {
            //根据路由配置数据源顺序，从上游获取数据
            String respContent = null;

            List<RelationOutInInterface> interfacesList =
                relationOutInInterfaceRepository.findByOutIdOrderByOrderNum(outInterface.getId());
            //遍历路由配置的数据源
            for (RelationOutInInterface relation : interfacesList) {
                if (stopSwitch) {
                    break;
                }
                SupplierInterface inInterface =
                    supplierInterfaceRepository.findOne(relation.getInId());
                //按数据源优先顺序调用接口
                //TODO
                respContent = getData(param);

                entity = parseData(respContent);

                setResCode();

                stopSwitch();

                //记录上游请求日志
                InputLog inlog = new InputLog();
                inlog.setUserId(param.getUserId());
                inlog.setAmount(inInterface.getPrice());
                inlog.setResponse(respContent);
                inlog.setInInterfaceId(inInterface.getId());
                inputRepository.save(inlog);
                saveData(entity);
            }

            //记录下游日志并扣款
            OutputLog outlog = new OutputLog();
            outlog.setUserId(param.getUserId());
            outlog.setOutInterfaceId(outInterface.getId());
            //            outlog.setParam(param.get + "." + param.getCid());
            outlog.setRespCode(ConstantDB.OUT_RESP_SUCCESS);
            outlog.setIsFree(ConstantDB.CHARGE_NOT_FREE);
            outlog.setIsOwner(ConstantDB.DATA_FROM_SUPPLIER);
            outlog.setPrice(userOutInterface.getPrice());
            outputRepository.save(outlog);

            //扣款
            User user = userRepository.findOne(param.getUserId());
            user.setAmount(user.getAmount().subtract(userOutInterface.getPrice()));
            userRepository.save(user);
        }
        return entity;
    }

    /**
     * 校验用户信息
     *
     * @return true:通过 false:不通过
     */
    public boolean checkUser(final Long userId, final String outInterfaceNo) {
        //判断用户是否合法
        User user = userRepository.findOne(userId);
        if (null == user) {
            throw new BaseUncheckedException(USER_1000);
        }

        //判断接口是否合法
        OutInterface outInterface = outInterfaceRepository.findByInterfaceNo(outInterfaceNo);
        if (null == outInterface) {
            throw new BaseUncheckedException(USER_1002);
        }

        //判断用户是否有接口权限
        UserOutInterface userOutInterface =
            userOutInterfaceRepository.findByUserIdAndOutInterfaceId(userId, outInterface.getId());
        if (null == userOutInterface) {
            throw new BaseUncheckedException(USER_1001);
        }

        //判断余额是否足够
        if (user.getAmount().subtract(userOutInterface.getPrice()).compareTo(BigDecimal.ZERO) < 0) {
            throw new BaseUncheckedException(USER_1003);
        }

        return true;
    }

    /**
     * 是否停止切换数据源
     *
     * @return
     */
    public void stopSwitch() {
        if ("0000".equals(resCode)) {
            this.stopSwitch = true;
        }
    }

    /**
     * 设置响应码
     */
    public abstract void setResCode();

    /**
     * 获取本地数据，若本地没有数据则返回 null
     *
     * @return
     */
    public abstract E getLocalData(T param);


    /**
     * 调用数据源接口，获取数据
     *
     * @return
     * @throws GetDataException
     * @throws TimeOutException
     */
    public abstract String getData(T param) throws GetDataException, TimeOutException;

    /**
     * 解析数据源返回的报文
     *
     * @param content
     * @throws ParseDataException
     */
    public abstract E parseData(String content) throws ParseDataException;

    /**
     * 保存从数据源获取的数据到数据库
     *
     * @throws SaveDataException
     */
    public abstract void saveData(E retData) throws SaveDataException ;
}
