package com.picto.dao;

import com.picto.entity.DiscountProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by BF100271 on 2016/5/24.
 */
public interface DiscountProductDao {
    List<DiscountProduct> queryDiscountByCouponTypeId(@Param("couponTypeId") Integer couponTypeId, @Param("merchantId") Integer merchantId); //TODO 添加mapper文件
}
