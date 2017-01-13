package com.zjdex.dao;

import com.zjdex.entity.InputLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by matrix on 2017/1/13.
 */
@Repository
public interface InputRepository extends JpaRepository<InputLog,Long> {
}
