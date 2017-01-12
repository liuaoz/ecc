package com.zjdex.controller;

import com.zjdex.dao.IdentityCheckRepository;
import com.zjdex.entity.RecNameCid;
import com.zjdex.service.IdentityCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by matrix_stone on 2017/1/11.
 */
@RestController
public class IdentityCheckController {
    @Value("${sjt.apikey}")
    private String apikey;
    @Value("${sjt.url}")
    private static String url;


    @Autowired
    IdentityCheckRepository identityCheckRepository;
    @Autowired
    private IdentityCheckService identityCheckService;

    @PostMapping("/check")
    public RecNameCid check(@RequestBody RecNameCid entity) {
        System.out.println("--apikey-->"+apikey);
        System.out.println("--url-->"+url);
        Long userId = 1L;
        RecNameCid result =  identityCheckService.trade(userId,entity);
        return result;
    }
}
