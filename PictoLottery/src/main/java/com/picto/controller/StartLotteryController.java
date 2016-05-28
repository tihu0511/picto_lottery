package com.picto.controller;

import com.picto.constants.Constants;
import com.picto.dao.MerchantDao;
import com.picto.entity.Merchant;
import com.picto.service.StartLotteryService;
import com.picto.util.WechatUtil;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wujigang on 2016/5/22.
 */
@Controller
public class StartLotteryController {
    @Autowired
    private MerchantDao mechantDao;
    @Autowired
    private StartLotteryService startLotteryService;

    @Value("${environment}")
    private String environment;
    @Value("${validateOpenid}")
    private String isValidateOpenid;

    @RequestMapping(value = "/startLottery", method = RequestMethod.GET)
    public String startLottery(@RequestParam("merchantId") Integer merchantId, @RequestParam("code") String code,
                               Model model, HttpServletRequest request) {
        Merchant merchant = mechantDao.queryMechantById(merchantId);
//        model.addAttribute("merchant", merchant);
        model.addAttribute("merchantId", merchantId);
        request.getSession().setAttribute("merchant", merchant);
        model.addAttribute("code", code);
        return "front/startLottery";
    }

    @RequestMapping(value = "/verifyLottery", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> verifyLottery(HttpServletRequest request) throws IOException, JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        String merchantId = request.getParameter("merchantId");
        String code = request.getParameter("code");

        String openid = null;
        if (!StringUtils.hasLength(code)) {
            retMap.put("success", false);
            retMap.put("errorMsg", "请从微信公众号进入");
        } else {
            //开发环境
            if (Constants.ENV_DEV.equalsIgnoreCase(environment)) {
                openid = "TEST555511118888";
            } else {
                openid = WechatUtil.getOpenIdByCode(code);
            }

            boolean hadLottery = startLotteryService.judgeHadLottery(openid);
            if (hadLottery && "true".equalsIgnoreCase(isValidateOpenid)) {
                retMap.put("success", false);
                retMap.put("errorMsg", "今日已抽过奖，请明日再来");
                return retMap;
            }
        }

        retMap.put("success", "true");
        retMap.put("openid", openid);
        return retMap;
    }
}
