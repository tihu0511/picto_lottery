package com.picto.dao;

import com.picto.entity.Merchant;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wujigang on 2016/5/22.
 */
@Repository
public interface MerchantDao {
    Merchant queryMechantById(@Param("id") Integer id);

    List<Merchant> queryAllMerchants();//TODO 查询所有商户信息

    void updateMerchant(Merchant merchant);// TODO 更新商户信息
}
