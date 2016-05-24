package com.picto.controller;

import com.picto.dao.DiscountProductDao;
import com.picto.entity.Coupon;
import com.picto.entity.CouponType;
import com.picto.entity.DiscountProduct;
import com.picto.entity.Merchant;
import com.picto.service.LotteryService;
import com.picto.util.ListUtil;
import com.picto.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wujigang on 2016/5/22.
 */
@Controller
public class LotteryController {
    @Autowired
    private LotteryService lotteryService;
    @Autowired
    private DiscountProductDao discountProductDao;

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

    @RequestMapping("lotteryFinish")
    public String lotteryFinish(Model model, HttpServletRequest request) {
        String luckyCouponTypeId = request.getParameter("luckyCouponTypeId");
        if (StringUtil.isBlank(luckyCouponTypeId)) {
            return "thanks";
        } else {
            Merchant merchant = (Merchant) request.getSession().getAttribute("merchant");
            String couponTypeId = request.getParameter("couponTypeId");

            List<DiscountProduct> discountProducts = discountProductDao.queryDiscountByCouponTypeId(Integer.valueOf(couponTypeId), merchant.getId());
            //奖项下有多个优惠，提供优惠选择
            if (!ListUtil.isEmptyList(discountProducts) && discountProducts.size() > 1) {
                model.addAttribute("disproducts", discountProducts);
                return "toChoiceDiscount";
            } else {
                //TODO 生成优惠券并跳转到优惠券信息页
                Coupon coupon = new Coupon();

                model.addAttribute("coupon", coupon);
                return "couponInfo";
            }
        }
    }
}
