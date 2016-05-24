package com.picto.dao;

import com.picto.entity.CouponType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wujigang on 2016/5/23.
 */
@Repository
public interface CouponTypeDao {
    List<CouponType> queryAllCouponTypes();

    void updateCouponTypeRestNum(CouponType luckyCouponType);
}
