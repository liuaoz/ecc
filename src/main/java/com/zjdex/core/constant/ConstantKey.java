package com.zjdex.core.constant;

/**
 * 与资源配置文件放置在src/main/resources/locale/messages_zh_CN.properties 中的key一一对应
 *
 * @author LIUAOZ
 * @version 1.0
 * @since 2017年1月3日 下午3:35:04
 */
public final class ConstantKey {

    private ConstantKey() {
    }

    /**
     * 平台出现了不可预计的错误
     */
    public static final String SYS_9999 = "9999";
    /**
     * 请求异常!
     */
    public static final String SYS_9998 = "9998";
    /**
     * 信息填写有误!
     */
    public static final String SYS_9997 = "9997";

    /* ===== 以下定义Hibernate query错误信息 ===== */
    /**
     * 查询HQL语句不能为空
     */
    public static final String E_BASE_00001 = "e_base_00001";

    /**
     * 查询条件个数{0}与查询值个数{1}不相等
     */
    public static final String E_BASE_00002 = "e_base_00002";

    /**
     * 查询条件个数{0}与操作符个数{1}不相等
     */
    public static final String E_BASE_00003 = "e_base_00003";

    /**
     * 该用户不存在！
     */
    public static final String USER_1000 = "1000";
    /**
     * 该用户没有此接口调用权限！
     */
    public static final String USER_1001 = "1001";
    /**
     * 该接口不存在！
     */
    public static final String USER_1002 = "1002";
    /**
     * 余额不足！
     */
    public static final String USER_1003 = "1003";

}
