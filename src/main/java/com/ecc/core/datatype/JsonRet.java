package com.ecc.core.datatype;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonRet {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonRet.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    private Object data;
    private String msg;
    private int code;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        try {
            JsonRet ret = new JsonRet();
            BeanUtils.copyProperties(this, ret);
            ret.setMsg((0 == this.getCode() ? "成功:" : "失败:") + this.getMsg());
            return objectMapper.writeValueAsString(this);
        } catch (IOException e) {
            LOGGER.error("error ==>"+e.getMessage());
            return super.toString();
        }
    }
}
