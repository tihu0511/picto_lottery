package com.picto.controller;

import com.picto.dao.MerchantDao;
import com.picto.entity.Coupon;
import com.picto.entity.Merchant;
import com.picto.service.CouponService;
import com.picto.util.StringUtil;
import com.picto.util.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by BF100271 on 2016/5/24.
 */
@Controller
public class QueryCouponController {
    @Autowired
    private CouponService couponService;
    @Autowired
    private MerchantDao merchantDao;

    @RequestMapping("queryCoupon")
    public String queryCoupon(@RequestParam("merchantId") Integer merchantId, @RequestParam("code") String code,
                              @RequestParam(value="openid", required = false) String openid, Model model) {
        String openId = "";
        if (null == code) {
            openId = openid;
        } else {
            openId = WechatUtil.getOpenIdByCode(code);
            openId = StringUtil.isBlank(openId) ? openid : openId;
        }

        List<Coupon> coupons = couponService.queryAllCouponsByOpenid(openId);
        model.addAttribute("coupons", coupons);

        return "couponList";
    }

    @RequestMapping("viewCoupon")
    public String viewCoupon(@RequestParam("selectedCouponId") Integer selectedCouponId, Model model) {
        Coupon coupon = couponService.queryCouponById(selectedCouponId);
        model.addAttribute("coupon", coupon);

        Merchant merchant = merchantDao.queryMechantById(coupon.getMerchantId());
        model.addAttribute("merchant", merchant);

        return "couponInfo";
    }
}
