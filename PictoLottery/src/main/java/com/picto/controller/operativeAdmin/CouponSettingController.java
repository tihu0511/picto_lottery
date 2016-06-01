package com.picto.controller.operativeAdmin;

import com.picto.dao.CouponTypeDao;
import com.picto.dao.MerchantDao;
import com.picto.entity.CouponType;
import com.picto.entity.Merchant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * Created by BF100271 on 2016/5/24.
 */
@Controller
@RequestMapping("/admin")
public class CouponSettingController {
    private static final Logger logger = Logger.getLogger(CouponSettingController.class);

    @Autowired
    private CouponTypeDao couponTypeDao;
    @Autowired
    private MerchantDao merchantDao;

    @RequestMapping("getAllCouponTypes")
    public String getAllCouponTypes(@RequestParam(value = "merchantId", required = false) Integer merchantId, Model model) {
        List<Merchant> allMerchants = merchantDao.queryAllMerchants();
        //TODO 查询账户
        List<Merchant> merchants = null;
        Merchant merchant = null;
        if (null != merchantId && 0 != merchantId.intValue()) {
            merchant = merchantDao.queryMechantById(merchantId);
            merchants = new ArrayList<Merchant>(1);
            merchants.add(merchant);
            model.addAttribute("selectedMerchantId", merchant.getId());

        } else {
            merchants = allMerchants;
            model.addAttribute("selectedMerchantId", null);
        }
        model.addAttribute("allMerchants", allMerchants);
        model.addAttribute("merchants", merchants);

        List<CouponType> couponTypes = new ArrayList<CouponType>();
        if (null != merchant) {
            couponTypes.add(couponTypeDao.queryCouponTypeByMerchantId(merchant.getId()));
        } else {
            couponTypes.addAll(couponTypeDao.queryAllCouponTypes());
        }
        model.addAttribute("couponTypes", couponTypes);
        return "operativeAdmin/couponTypeList";
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
        couponType.setUpdateTime(new Date());
        couponTypeDao.updateCouponType(couponType);
        //TODO 编辑奖项后的保存跳转
    }


}
