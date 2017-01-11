package com.zjdex.core.constant;

/**
 * 与资源配置文件放置在src/main/resources/locale/messages_zh_CN.properties 中的key一一对应
 * 
 * @author LIUAOZ
 * @since 2017年1月3日 下午3:35:04
 * @version 1.0
 */
public class ConstantKey {

    /** 平台出现了不可预计的错误 */
    public static final String SYS_9999 = "9999";
    /** 请求异常! */
    public static final String SYS_9998 = "9998";
    /** 信息填写有误! */
    public static final String SYS_9997 = "9997";

    /* ===== 以下定义Hibernate query错误信息 ===== */
    /** 查询HQL语句不能为空 */
    public static final String E_BASE_00001 = "e_base_00001";

    /** 查询条件个数{0}与查询值个数{1}不相等 */
    public static final String E_BASE_00002 = "e_base_00002";

    /** 查询条件个数{0}与操作符个数{1}不相等 */
    public static final String E_BASE_00003 = "e_base_00003";

}
