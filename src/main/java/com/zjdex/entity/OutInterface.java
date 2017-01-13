package com.zjdex.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by matrix_stone on 2017/1/13.
 */
@Entity
public class OutInterface extends BaseEntity {

    private String interfaceNo;

    private String interfaceName;

    private String status;

    private BigDecimal price;

    public String getInterfaceNo() {
        return interfaceNo;
    }

    public void setInterfaceNo(String interfaceNo) {
        this.interfaceNo = interfaceNo;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
