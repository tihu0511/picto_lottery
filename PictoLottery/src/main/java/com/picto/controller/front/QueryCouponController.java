package com.picto.controller.front;

import com.picto.constants.Constants;
import com.picto.dao.MerchantDao;
import com.picto.entity.Coupon;
import com.picto.entity.Merchant;
import com.picto.service.CouponService;
import com.picto.util.DateUtil;
import com.picto.util.ListUtil;
import com.picto.util.StringUtil;
import com.picto.util.WechatUtil;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by BF100271 on 2016/5/24.
 */
@Controller
public class QueryCouponController {
    private static final Logger logger = Logger.getLogger(QueryCouponController.class);

    @Autowired
    private CouponService couponService;
    @Autowired
    private MerchantDao merchantDao;
    @Value("${environment}")
    private String environment;

    @RequestMapping("queryCoupon")
    public String queryCoupon(@RequestParam(value = "code", required = false) String code,
                              @RequestParam(value = "merchantId", required = false) Integer merchantId,
                              Model model, HttpServletRequest request) throws IOException, JSONException {
        String openId = "";
        if (Constants.ENV_DEV.equals(environment)) {
            openId = "TEST555511118888";
        }else if (code != null) {
            openId = WechatUtil.getOpenIdByCode(code);
        }

        if (StringUtil.isBlank(openId)) {
            openId = (String) request.getSession().getAttribute("openid");
        }
        request.getSession().setAttribute("openid", openId);
        logger.info("openId=" + openId);

        List<Coupon> coupons = null;
        Merchant merchant = null;
        if (null != merchantId) {
            merchant = merchantDao.queryMechantById(merchantId);
            coupons = couponService.queryAllCouponsByOpenidAndMerId(merchantId, openId, new Date());
        } else {
            coupons = couponService.queryAllCouponsByOpenid(openId, new Date());
        }
        model.addAttribute("coupons", coupons);
        model.addAttribute("merchant", merchant);

        return "front/couponList";
    }

    @RequestMapping("viewCoupon")
    public String viewCoupon(@RequestParam("couponId") Integer couponId, Model model) {
        Coupon coupon = couponService.queryCouponById(couponId);
        model.addAttribute("coupon", coupon);
        String expireDateStr = coupon.getIsImediate() ? DateUtil.formatDate(coupon.getExpiredTime(), "yyyy/MM/dd")
                : DateUtil.formatDate(coupon.getCreateTime(), "MM/dd") + "-" + DateUtil.formatDate(coupon.getExpiredTime(), "MM/dd");
        model.addAttribute("expireDateStr", expireDateStr);

        Merchant merchant = merchantDao.queryMechantById(coupon.getMerchantId());
        model.addAttribute("merchant", merchant);

        return "front/couponInfo";
    }
}
