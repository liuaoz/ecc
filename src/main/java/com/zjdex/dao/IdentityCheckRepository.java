package com.zjdex.dao;

import com.zjdex.entity.RecNameCid;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by matrix_stone on 2017/1/11.
 */
public interface IdentityCheckRepository extends JpaRepository<RecNameCid,Long>{
}
