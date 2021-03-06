package com.ecc.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * 数据源供应商接口信息
 *
 * @author LIUAOZ
 * @version 1.0
 */
@Entity
public class SupplierInterface extends BaseEntity {

    private String supplierId;

    private String interfaceNo;

    private String interfaceName;

    private String chargeMode;

    private BigDecimal price;

    private String status;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

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

    public String getChargeMode() {
        return chargeMode;
    }

    public void setChargeMode(String chargeMode) {
        this.chargeMode = chargeMode;
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
