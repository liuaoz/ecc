package com.zjdex.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 数据源供应商信息
 * 
 * @author LIUAOZ
 * @version 1.0
 */
@Entity
public class Supplier extends BaseEntity {

    private String supplierNo;

    private String supplierName;

    private String status;

    public String getSupplierNo() {
        return supplierNo;
    }

    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
