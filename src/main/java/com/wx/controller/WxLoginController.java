package com.wx.controller;

import com.wx.util.AuthUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by matrix on 2017/1/20.
 */
@Controller
public class WxLoginController {

    @RequestMapping("wxLogin")
    public void wxLogin(String xx) {
        String backurl = "callback";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=+" + AuthUtil.APPID
                + "&redirect_uri=" + backurl
                + "&response_type=code+"
                + "&scope=snsapi_userinfo"
                + "&state=STATE#wechat_redirect";

    }
}
