package com.zjdex.core.utils;

import java.util.UUID;

/**
 * Description TODO
 * 
 * @author LIUAOZ
 * @version 1.0
 */
public class FwUtil {

    /**
     * 生成交易编号，全局唯一，规则如下：<br>
     * 
     * 
     * @return
     */
    public static String generateSeqNo() {

        String s = UUID.randomUUID().toString();
        // 去掉"-"符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23)
                + s.substring(24);
    }



}
