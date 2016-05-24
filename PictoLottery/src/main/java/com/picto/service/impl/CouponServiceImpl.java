package com.picto.service.impl;

import com.picto.constants.Constants;
import com.picto.dao.CouponDao;
import com.picto.dao.CouponTypeDao;
import com.picto.entity.Coupon;
import com.picto.entity.CouponType;
import com.picto.entity.DiscountProduct;
import com.picto.service.CouponService;
import com.picto.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by BF100271 on 2016/5/24.
 */
@Service
public class CouponServiceImpl implements CouponService {
    public static final char[] CHAR_ARR = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @Autowired
    private CouponDao couponDao;
    @Autowired
    private CouponTypeDao couponTypeDao;

    public Coupon genCoupon(DiscountProduct discountProduct, String openid) {
        CouponType couponType = couponTypeDao.queryCouponTypeById(discountProduct.getCouponTypeId());
        Coupon coupon = new Coupon();
        coupon.setMerchantId(discountProduct.getMerchantId());
        coupon.setName(discountProduct.getName());
        coupon.setSerialNumber(genCouponSerialNumber(discountProduct.getMerchantId()));
        coupon.setDiscountProductId(discountProduct.getId());
        coupon.setExpiredTime(DateUtil.addHours(new Date(), discountProduct.getValidity()));
        coupon.setIcon(discountProduct.getIcon());
        coupon.setOpenid(openid);
        coupon.setIsImediate(couponType.getIsImmediate());
        coupon.setDiscount(discountProduct.getDiscount());
        coupon.setIsShared(discountProduct.getIsShared());
        coupon.setUseMsg(discountProduct.getUseMsg());
        coupon.setLimitMsg(discountProduct.getLimitMsg());
        coupon.setStoreName(discountProduct.getStoreName());
        coupon.setState(Constants.COUPON_STATE_EFFECTED);
        coupon.setCreateTime(new Date());

        couponDao.addCoupon(coupon);
        return coupon;
    }

    public List<Coupon> queryAllCouponsByOpenid(String openId) {
        return couponDao.queryAllCouponsByOpenid(openId);
    }

    public Coupon queryCouponById(Integer selectedCouponId) {
        return couponDao.queryCouponById(selectedCouponId);
    }


    /**
     * 生成不唯一的优惠券号
     * @param merchantId
     * @return
     */
    private String genCouponSerialNumber(Integer merchantId) {
        String merchantIdStr = String.valueOf(merchantId);
        int len = CHAR_ARR.length;

        String serialNumber = "";

        boolean isExist = true;
        while (isExist) {
            int index = 0;
            StringBuffer couponSerialNumber = new StringBuffer();
            couponSerialNumber.append(merchantIdStr);
            Random random = new Random();
            for (int i = 0; i < 8 - merchantIdStr.length(); i++) {
                index = random.nextInt(len);
                couponSerialNumber.append(String.valueOf(CHAR_ARR[index]));
            }

            Coupon coupon = couponDao.queryCouponBySerialNumber(couponSerialNumber.toString());
            if (coupon == null) {
                isExist = false;
                serialNumber = couponSerialNumber.toString();
            }
        }

        return serialNumber;
    }
}
