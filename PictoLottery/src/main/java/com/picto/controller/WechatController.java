package com.picto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by BF100271 on 2016/5/27.
 */
@Controller
public class WechatController {
    @RequestMapping("welcome")
    public String welcomeToMrPrize(@RequestParam("merchantId") Integer merchantId) {
        String url = ""; //TODO 微信鉴权url
        return "redirect:" + url;
    }
}
