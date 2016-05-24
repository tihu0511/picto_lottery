package com.picto.dao;

import com.picto.entity.DiscountProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BF100271 on 2016/5/24.
 */
@Repository
public interface DiscountProductDao {
    List<DiscountProduct> queryDiscountByCouponTypeId(@Param("couponTypeId") Integer couponTypeId,
        @Param("merchantId") Integer merchantId);

    DiscountProduct queryDiscountById(@Param("id") Integer id);
}
