package com.ecc.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by matrix_stone on 2017/1/11.
 */
@Entity
public class OutputLog extends BaseEntity {

    private long userId;

    private long outInterfaceId;

    private String param;

    private String isFree;

    private BigDecimal price;

    private String respCode;

    private String isOwner;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOutInterfaceId() {
        return outInterfaceId;
    }

    public void setOutInterfaceId(long outInterfaceId) {
        this.outInterfaceId = outInterfaceId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(String isOwner) {
        this.isOwner = isOwner;
    }
}
