package com.ecc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecc.entity.UserOutInterface;

/**
 * Created by matrix_stone on 2017/1/13.
 */
@Repository
public interface UserOutInterfaceRepository extends JpaRepository<UserOutInterface, Long> {

    UserOutInterface findByUserIdAndOutInterfaceId(Long userId, Long outInterfaceId);
}
