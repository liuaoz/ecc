package com.zjdex.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by matrix_stone on 2017/1/13.
 */
@Entity
public class UserOutInterface extends BaseEntity {

    private Long userId;

    private Long outInterfaceId;

    private BigDecimal price;

    private String status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOutInterfaceId() {
        return outInterfaceId;
    }

    public void setOutInterfaceId(Long outInterfaceId) {
        this.outInterfaceId = outInterfaceId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
