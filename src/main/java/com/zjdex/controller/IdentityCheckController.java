package com.zjdex.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjdex.core.utils.shujt.DesEncrypter;
import com.zjdex.entity.RecSjtNameCid;
import com.zjdex.entity.param.NameCidParam;
import com.zjdex.service.AbstractInputService;
import com.zjdex.service.IdentityCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by matrix_stone on 2017/1/11.
 */
@RestController
public class IdentityCheckController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdentityCheckController.class);

    private AtomicInteger count = new AtomicInteger(0);

    @Autowired
    private IdentityCheckService identityCheckService;

    @PostMapping("/check")
    public String check(@RequestBody String pjson) {

        count.addAndGet(1);
        System.out.println("--count->" + count);

        JSONObject json = JSON.parseObject(pjson);

        NameCidParam param = new NameCidParam();

        param.setName(json.getString("name"));
        param.setCid(json.getString("cid"));
        param.setUserId(json.getLong("userId"));
        param.setOutInterfaceNo(json.getString("outInterfaceNo"));
        RecSjtNameCid result = identityCheckService.trade(param);

        String rest = JSON.toJSONString(result);
        String en = null;
        try {
            en = DesEncrypter.encrypt(rest, "aaaaaaa");
            System.out.println("--en-->" + en);
            String de = DesEncrypter.decrypt(en, "aaaaaaa");
            System.out.println("--de-->" + de);
        } catch (Exception e) {
            LOGGER.error("error ..." + e.getMessage());
        }
        return en;
    }
}
