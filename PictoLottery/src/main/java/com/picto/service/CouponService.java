package com.picto.service;

import com.picto.entity.Coupon;
import com.picto.entity.DiscountProduct;

import java.util.List;

/**
 * Created by BF100271 on 2016/5/24.
 */
public interface CouponService {
    /**
     * 生成优惠券
     * @param discountProduct
     * @return
     */
    Coupon genCoupon(DiscountProduct discountProduct);

    /**
     * 查询openid下的所有优惠券，并按失效时间倒序排序
     * @param openId
     * @return
     */
    List<Coupon> queryAllCouponsByOpenid(String openId);

    /**
     * 查询优惠券
     * @param selectedCouponId
     * @return
     */
    Coupon queryCouponById(Integer selectedCouponId);
}
