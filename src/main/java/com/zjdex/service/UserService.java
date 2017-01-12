package com.zjdex.service;

import com.zjdex.dao.UserRepository;
import com.zjdex.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by matrix_stone on 2017/1/12.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public User queryUserById(Long id){
        return userRepo.findOne(id);
    }
}
