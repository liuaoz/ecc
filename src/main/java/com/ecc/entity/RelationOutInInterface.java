package com.ecc.entity;

import javax.persistence.Entity;

/**
 * Created by matrix on 2017/1/13.
 */
@Entity
public class RelationOutInInterface extends BaseEntity {

    private Long outId;

    private Long inId;

    private Integer orderNum;

    public Long getOutId() {
        return outId;
    }

    public void setOutId(Long outId) {
        this.outId = outId;
    }

    public Long getInId() {
        return inId;
    }

    public void setInId(Long inId) {
        this.inId = inId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
