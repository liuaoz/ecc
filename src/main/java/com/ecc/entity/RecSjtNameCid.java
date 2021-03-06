package com.ecc.entity;

import javax.persistence.Entity;

/**
 * Created by matrix_stone on 2017/1/11.
 */
@Entity
public class RecSjtNameCid extends BaseEntity {

    private String name;

    private String cid;

    private String status;

    private String resCode;

    private String resMsg;

    public RecSjtNameCid() {}

    public RecSjtNameCid(String name, String cid) {
        this.name = name;
        this.cid = cid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
}
