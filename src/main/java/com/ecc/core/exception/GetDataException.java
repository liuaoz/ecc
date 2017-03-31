package com.ecc.core.exception;

/**
 * Description 取数异常
 *
 * @author LIUAOZ
 * @version 1.0
 */
public class GetDataException extends BaseUncheckedException {

    private static final long serialVersionUID = 1747760509437238345L;

    public GetDataException() {
        super();
    }

    public GetDataException(String message) {
        super(message);
    }
}
