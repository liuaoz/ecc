package com.ecc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecc.entity.InputLog;

/**
 * Created by matrix on 2017/1/13.
 */
@Repository
public interface InputRepository extends JpaRepository<InputLog,Long> {
}
