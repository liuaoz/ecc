package com.ecc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecc.entity.User;

/**
 * Created by matrix_stone on 2017/1/12.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
