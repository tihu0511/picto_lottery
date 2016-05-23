package com.picto.controller;

import com.picto.entity.CouponType;
import com.picto.entity.Merchant;
import com.picto.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wujigang on 2016/5/22.
 */
@Controller
public class LotteryController {
    @Autowired
    private LotteryService lotteryService;

    @RequestMapping("/lottery")
    public String lottery(Model model, HttpServletRequest request) {
        Merchant merchant = (Merchant)request.getSession().getAttribute("merchant");
        String openid = request.getParameter("openid");
        //生成中奖的奖项
        CouponType couponType = lotteryService.lotyCouponType(openid, merchant.getId());

        if (null == couponType) {
            //谢谢惠顾生成显示的奖项图标
            model.addAttribute("showIcons", lotteryService.getUnluckyShowIcons(merchant.getId()));
        } else if (!couponType.getIsThanks()) {
            String luckyIcon = couponType.getIcon();
            model.addAttribute("luckyCouponIcon", luckyIcon);
            model.addAttribute("showIcons", luckyIcon + ";" + luckyIcon + ";" + luckyIcon);
        } else {
            //谢谢惠顾生成显示的奖项图标
            model.addAttribute("showIcons", lotteryService.getUnluckyShowIcons(merchant.getId()));
        }

        return "lottery";
    }
}
