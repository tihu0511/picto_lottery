package com.picto.controller;

import com.picto.dao.DiscountProductDao;
import com.picto.entity.Coupon;
import com.picto.entity.DiscountProduct;
import com.picto.entity.Merchant;
import com.picto.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by BF100271 on 2016/5/24.
 */
@Controller
public class ChoiceDiscountController {
    @Autowired
    private DiscountProductDao discountProductDao;
    @Autowired
    private CouponService couponService;

    @RequestMapping("/choiceDiscount")
    public String choiceDiscount(@RequestParam("selectedDiscountProductId") Integer selectedDiscountProductId,
                                 @RequestParam("openid") String openid, Model model, HttpServletRequest request) {
        Merchant merchant = (Merchant) request.getSession().getAttribute("merchant");

        //根据选择的优惠产品生成优惠券并跳转到优惠券信息页
        DiscountProduct discountProduct = discountProductDao.queryDiscountById(selectedDiscountProductId);
        Coupon coupon = couponService.genCoupon(discountProduct, openid);
        model.addAttribute("coupon", coupon);
        return "couponInfo";
    }
}
