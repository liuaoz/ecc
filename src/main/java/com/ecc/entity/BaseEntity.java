package com.ecc.entity;

import javax.persistence.*;

import com.ecc.core.constant.ConstantDB;

import java.util.Date;

/**
 * Created by matrix_stone on 2017/1/11.
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    public Long id;

    @Column(length = ConstantDB.LENGTH_NAME)
    public String creator;

    @Column(length = ConstantDB.LENGTH_NAME)
    public String operator;

    @Version
    private int version;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    public Date createTime;

    @Column(name = "update_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
    public Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
