package com.picto.service.impl;

import com.picto.entity.Coupon;
import com.picto.entity.DiscountProduct;
import com.picto.service.CouponService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BF100271 on 2016/5/24.
 */
@Service
public class CouponServiceImpl implements CouponService {
    public Coupon genCoupon(DiscountProduct discountProduct) {
        //TODO 生成优惠券
        return null;
    }

    public List<Coupon> queryAllCouponsByOpenid(String openId) {
        //TODO 查询openid下的所有优惠券，并按失效时间倒序排序
        return null;
    }

    public Coupon queryCouponById(Integer selectedCouponId) {
        //TODO 根据id查询优惠券
        return null;
    }
}
