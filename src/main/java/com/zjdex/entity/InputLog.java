package com.zjdex.entity;

import javax.persistence.Entity;

/**
 * Created by matrix_stone on 2017/1/11.
 */
@Entity
public class InputLog extends BaseEntity{

    private long inInterfaceId;

    private long userId;

    private String isFree;

    private String response;

    private double amount;

    public long getInInterfaceId() {
        return inInterfaceId;
    }

    public void setInInterfaceId(long inInterfaceId) {
        this.inInterfaceId = inInterfaceId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
