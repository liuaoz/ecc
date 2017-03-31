package com.ecc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecc.entity.OutInterface;

/**
 * Created by matrix_stone on 2017/1/13.
 */
@Repository
public interface OutInterfaceRepository extends JpaRepository<OutInterface,Long> {

    public OutInterface findByInterfaceNo(String interfaceNo);
}
