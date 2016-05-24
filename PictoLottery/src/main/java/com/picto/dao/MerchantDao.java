package com.picto.dao;

import com.picto.entity.Merchant;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by wujigang on 2016/5/22.
 */
@Repository
public interface MerchantDao {
    Merchant queryMechantById(@Param("id") Integer id);
}
