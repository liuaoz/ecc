package com.zjdex.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjdex.core.utils.shujt.DesEncrypter;
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
    private IdentityCheckService identityCheckService;

    @PostMapping("/check")
    public String check(String name,String cid,String outInterfaceNo) {

        Long userId = 1L;
        RecNameCid param = new RecNameCid();

        param.setName(name);
        param.setCid(cid);
        RecNameCid result = identityCheckService.trade(userId,outInterfaceNo, param);

        String rest = JSON.toJSONString(result);
        String en = null;
        try {
            en = DesEncrypter.encrypt(rest, "aaaaaaa");
            System.out.println("--en-->" + en);
            String de = DesEncrypter.decrypt(en, "aaaaaaa");
            System.out.println("--de-->" + de);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return en;
    }
}
