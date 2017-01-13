package com.zjdex.entity;

import javax.persistence.Entity;

/**
 * Created by matrix_stone on 2017/1/13.
 */
@Entity
public class OutInterface extends BaseEntity{

    private String outInterfaceNo;

    private Long inInterfaceId;

    private Integer orderNumber;

    public String getOutInterfaceNo() {
        return outInterfaceNo;
    }

    public void setOutInterfaceNo(String outInterfaceNo) {
        this.outInterfaceNo = outInterfaceNo;
    }

    public Long getInInterfaceId() {
        return inInterfaceId;
    }

    public void setInInterfaceId(Long inInterfaceId) {
        this.inInterfaceId = inInterfaceId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
