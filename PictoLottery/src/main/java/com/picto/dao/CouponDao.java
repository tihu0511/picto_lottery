package com.picto.dao;

import com.picto.entity.Coupon;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wujigang on 2016/5/24.
 */
@Repository
public interface CouponDao {
    Coupon queryCouponById(@Param("id") Integer id);

    List<Coupon> queryAllCouponsByOpenid(@Param("openid") String openId);

    Coupon queryCouponBySerialNumber(@Param("serialNumber") String serialNumber);

    void addCoupon(Coupon coupon);
}
