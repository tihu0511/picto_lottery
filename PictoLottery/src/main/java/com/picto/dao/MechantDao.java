package com.picto.dao;

import com.picto.entity.Merchant;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wujigang on 2016/5/22.
 */
public interface MechantDao {
    Merchant queryMechantById(@Param("id") Integer id);
}
