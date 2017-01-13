package com.zjdex.dao;

import com.zjdex.entity.RelationOutInInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by matrix on 2017/1/13.
 */
@Repository
public interface RelationOutInInterfaceRepository extends JpaRepository<RelationOutInInterface,Long>{

    public List<RelationOutInInterface> findByOutIdOrderByOrderNum(Long outId);
}
