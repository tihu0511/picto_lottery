package com.picto.dao;

import com.picto.entity.CouponType;

import java.util.List;

/**
 * Created by wujigang on 2016/5/23.
 */
public interface CouponTypeDao {
    List<CouponType> queryAllCouponTypes();

    void updateCouponTypeRestNum(CouponType luckyCouponType);
}
