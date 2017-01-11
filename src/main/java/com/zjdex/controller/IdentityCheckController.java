package com.zjdex.controller;

import com.zjdex.entity.RecNameCidEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zjdex.dao.IdentityCheckRepository;

/**
 * Created by matrix_stone on 2017/1/11.
 */
@RestController
public class IdentityCheckController {

    @Autowired
    IdentityCheckRepository identityCheckRepository;

    @PostMapping("/check")
    public RecNameCidEntity check(@RequestBody RecNameCidEntity entity) {
        return identityCheckRepository.save(entity);
    }
}
