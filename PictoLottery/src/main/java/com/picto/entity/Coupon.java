package com.picto.entity;

/**
 * Created by BF100271 on 2016/5/24.
 */
public class Coupon extends BaseEntity {
    //TODO 优惠券

    private Integer merchantId;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }
}
