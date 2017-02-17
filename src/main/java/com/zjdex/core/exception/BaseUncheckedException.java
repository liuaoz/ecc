package com.zjdex.core.exception;

/**
 * Description base unchecked exception
 * 
 * @author LIUAOZ
 * @version 1.0
 */
public class BaseUncheckedException extends RuntimeException {

    private static final long serialVersionUID = -2121599154084035677L;

    /**
     * 错误代码，用于唯一标识错误类型。
     */
    private String code;

    /**
     * 根据code从资源文件转成明文的错误信息
     */
    private String msg;

    /**
     * 传递给变量的错误值
     */
    private transient Object[] values;


    public BaseUncheckedException() {
        super();
    }

    public BaseUncheckedException(String code) {
        super();
        this.code = code;
        this.values = null;
        // 转成明文错误信息
        this.msg = Message.getMessage(code);
    }

    public BaseUncheckedException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public BaseUncheckedException(String code, Object[] args) {
        super();
        this.code = code;
        this.values = args;
        // 转成明文错误信息
        this.msg = Message.getMessage(code, args);
    }

    public BaseUncheckedException(Throwable rootCause, String code, Object[] args) {
        super(rootCause);
        this.code = code;
        this.values = args;
        // 转成明文错误信息
        this.msg = Message.getMessage(code, args);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

}
