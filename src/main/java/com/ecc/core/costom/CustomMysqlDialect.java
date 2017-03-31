package com.ecc.core.costom;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Created by matrix_stone on 2017/1/11.
 */
public class CustomMysqlDialect extends MySQL5InnoDBDialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
