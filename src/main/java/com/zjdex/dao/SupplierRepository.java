package com.zjdex.dao;

import com.zjdex.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by matrix_stone on 2017/1/12.
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long>{
}
