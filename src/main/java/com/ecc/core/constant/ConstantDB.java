package com.ecc.core.constant;

/**
 * Description 常用数据库字段长度
 *
 * @author LIUAOZ
 * @version 1.0
 */
public final class ConstantDB {

    private ConstantDB() {
    }

    /**
     * 交易流水号 64
     */
    public static final int LENGTH_SEQ_NO = 64;
    /**
     * 姓名 64
     */
    public static final int LENGTH_NAME = 64;
    /**
     * 身份证号 18
     */
    public static final int LENGTH_CID = 18;
    /**
     * 常用编号(供应商编号、接口编号、用户编号) 8
     */
    public static final int LENGTH_CODE = 8;
    /**
     * COMMENT '状态 0不可用,1可用'
     */
    public static final String COMMENT_STATUS = "COMMENT '状态 0不可用,1可用'";

    /**
     * output_interface_supplier_interface
     */
    public static final String TABLE_OUTPUT_INTERFACE_SUPPLIER_INTERFACE =
        "output_interface_supplier_interface";
    /**
     * output_interface
     */
    public static final String TABLE_OUTPUT_INTERFACE = "output_interface";
    /**
     * fw_user_interface
     */
    public static final String TABLE_FW_USER_INTERFACE = "fw_user_interface";
    /**
     * supplier
     */
    public static final String TABLE_SUPPLIER = "supplier";
    /**
     * supplier_interface
     */
    public static final String TABLE_SUPPLIER_INTERFACE = "supplier_interface";
    /**
     * fw_user
     */
    public static final String TABLE_FW_USER = "fw_user";
    /**
     * input_trade
     */
    public static final String TABLE_INPUT_TRADE = "input_trade";
    /**
     * output_trade
     */
    public static final String TABLE_OUTPUT_TRADE = "output_trade";



    /**
     * 下游请求成功响应码
     */
    public static final String OUT_RESP_SUCCESS = "0000";
    /**
     * 下游请求失败响应码
     */
    public static final String OUT_RESP_FAILED = "0001";

    /**
     * 数据来源本地库
     */
    public static final String DATA_FROM_LOCAL = "1";

    /**
     * 数据来源供应商
     */
    public static final String DATA_FROM_SUPPLIER = "2";

    /**
     * 不收费
     */
    public static final String CHARGE_FREE = "1";

    /**
     * 收费
     */
    public static final String CHARGE_NOT_FREE = "0";


}
