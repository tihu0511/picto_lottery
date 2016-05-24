package com.picto.controller;

import com.picto.dao.CouponTypeDao;
import com.picto.entity.CouponType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by BF100271 on 2016/5/24.
 */
@Controller
public class CouponSettingController {
    @Autowired
    private CouponTypeDao couponTypeDao;

    @RequestMapping("getAllCouponTypes")
    public String getAllCouponTypes(Model model) {
        List<CouponType> couponTypes = couponTypeDao.queryAllCouponTypes();
        model.addAttribute("couponTypes", couponTypes);
        return "couponTypeList";
    }

    @RequestMapping("editCouponType")
    public String editCouponType(@RequestParam("couponTypeId") Integer couponTypeId, Model model) {
        CouponType couponType = couponTypeDao.queryCouponTypeById(couponTypeId);
        model.addAttribute("couponType", couponType);
        return "editCouponType";
    }

    @RequestMapping("updateCouponType")
    public void updateCouponType(CouponType couponType) {
        couponType.setRestNum(couponType.getTotalNum());
        couponType.setVersion(couponType.getVersion() + 1);
        couponType.setUpdateTime(new Date());
        couponTypeDao.updateCouponType(couponType);
        //TODO 编辑奖项后的保存跳转
    }
}
