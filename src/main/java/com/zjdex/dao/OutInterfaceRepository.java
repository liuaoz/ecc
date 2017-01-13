package com.zjdex.dao;

import com.zjdex.entity.OutInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by matrix_stone on 2017/1/13.
 */
@Repository
public interface OutInterfaceRepository extends JpaRepository<OutInterface,Long> {

    public OutInterface findByOutInterfaceNo(String outInterfaceNo);
}
