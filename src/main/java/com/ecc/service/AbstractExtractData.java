package com.ecc.service;

import com.ecc.core.exception.GetDataException;
import com.ecc.core.exception.ParseDataException;
import com.ecc.core.exception.SaveDataException;
import com.ecc.core.exception.TimeOutException;
import com.ecc.entity.BaseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;


/**
 * Description 数据源取数基类。包含一个获取数据流程模板，默认如下：<br>
 * 1.调用数据源接口，获取返回报文<br>
 * 2.解析返回报文（是否异常、是否告警、是否收费等）<br>
 * 3.保存解析后的数据到数据库<br>
 * 4.
 * 
 * @author LIUAOZ
 * @version 1.0
 * @since 2017年1月1日 上午11:36:54
 */
public abstract class AbstractExtractData<E extends BaseEntity> implements Callable<E> {

    /** logger available to subclasses */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected boolean isFetchRealData = true;// 是否从数据源取数

    protected boolean isPass;

    protected boolean isSave;

    protected AbstractExtractData() {
        super();
    }

    @Override
    public E call() throws Exception {

        return null;
    }

    /**
     * 数据源获取流程
     */
    public final void deal() {

        String resp = getData();

        parseData(resp);
    }

    /**
     * 调用数据源接口，获取数据
     * 
     * @return
     * @throws GetDataException
     * @throws TimeOutException
     */
    public abstract String getData() throws GetDataException, TimeOutException;

    /**
     * 解析数据源返回的报文
     * 
     * @param content
     * @throws ParseDataException
     */
    public abstract void parseData(String content) throws ParseDataException;

    /**
     * 保存从数据源获取的数据到数据库
     * 
     * @throws SaveDataException
     */
    public abstract void saveData() throws SaveDataException;

    /**
     * 是否要跳过从数据源获取数据<br>
     * 
     * @return false:不跳过 true跳过
     */
    public boolean isPass() {

        return false;
    }

}
