package com.ecc.entity;

import javax.persistence.Entity;

/**
 * Created by matrix_stone on 2017/1/11.
 */
@Entity
public class Role extends BaseEntity{

    private String roleName;

    private String status;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
