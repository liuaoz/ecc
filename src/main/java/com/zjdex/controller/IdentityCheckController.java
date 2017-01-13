package com.zjdex.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjdex.core.utils.shujt.DesEncrypter;
import com.zjdex.entity.RecSjtNameCid;
import com.zjdex.service.IdentityCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by matrix_stone on 2017/1/11.
 */
@RestController
public class IdentityCheckController {

    @Autowired
    private IdentityCheckService identityCheckService;

    @PostMapping("/check")
    public String check(@RequestBody String pjson) {

        JSONObject json = JSON.parseObject(pjson);

        Long userId = 1L;
        RecSjtNameCid param = new RecSjtNameCid();

        param.setName(json.getString("name"));
        param.setCid(json.getString("cid"));
        RecSjtNameCid result = identityCheckService.trade(userId, json.getString("outInterfaceNo"), param);

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
