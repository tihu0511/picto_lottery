package com.picto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by BF100271 on 2016/5/27.
 */
@Controller
public class WechatController {
    private static final String APP_ID = "wx8f4239ff75ca1770";

    @RequestMapping("welcome")
    public String welcomeToMrPrize(@RequestParam("merchantId") Integer merchantId) {
        String redirectUrl = "http%3a%2f%2fwww.mr-prize.com%2fstartLottery.do%3fmerchantId%3d1";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APP_ID + "&redirect_uri="
                + redirectUrl + "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
        return "redirect:" + url;
    }

    @RequestMapping("toQuery")
    public String toQuery() {
        String redirectUrl = "http%3a%2f%2fwww.mr-prize.com%2fqueryCoupon.do";
        return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APP_ID + "&redirect_uri="
                + redirectUrl + "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
    }
}
