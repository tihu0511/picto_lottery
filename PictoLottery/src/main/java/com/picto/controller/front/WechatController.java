package com.picto.controller.front;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by BF100271 on 2016/5/27.
 */
@Controller
public class WechatController {
    private static final Logger logger = Logger.getLogger(WechatController.class);
    private static final String APP_ID = "wx8f4239ff75ca1770";

    @RequestMapping("welcome")
    public String welcomeToMrPrize(@RequestParam("merchantId") Integer merchantId) {
        String redirectUrl = "http%3a%2f%2fwww.mr-prize.com%2fstartLottery.do%3fmerchantId%3d1";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APP_ID + "&redirect_uri="
                + redirectUrl + "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";

        logger.info("url=" + url);
        return "redirect:" + url;
    }

    @RequestMapping("toQuery")
    public String toQuery(@RequestParam(value = "merchantId", required = false) Integer merchantId) {
        String redirectUrl = "http%3a%2f%2fwww.mr-prize.com%2fqueryCoupon.do";

        if (null != merchantId) {
            redirectUrl += "%3fmerchantId%3d" + merchantId;
        }

        return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APP_ID + "&redirect_uri="
                + redirectUrl + "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
    }
}
