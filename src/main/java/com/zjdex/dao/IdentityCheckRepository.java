package com.zjdex.dao;

import com.zjdex.entity.RecSjtNameCid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by matrix_stone on 2017/1/11.
 */
@Repository
public interface IdentityCheckRepository extends JpaRepository<RecSjtNameCid,Long>{

    public RecSjtNameCid findByNameAndCid(final String name, final String cid);
}
