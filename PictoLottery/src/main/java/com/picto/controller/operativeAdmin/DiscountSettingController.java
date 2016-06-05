package com.picto.controller.operativeAdmin;

import com.picto.dao.CouponTypeDao;
import com.picto.dao.DiscountProductDao;
import com.picto.dao.MerchantDao;
import com.picto.entity.CouponType;
import com.picto.entity.DiscountProduct;
import com.picto.entity.Merchant;
import com.picto.service.CouponSettingService;
import com.picto.util.CouponUtil;
import com.picto.util.ListUtil;
import com.picto.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wujigang on 2016/6/4.
 */
@Controller
@RequestMapping("/admin")
public class DiscountSettingController {
    @Autowired
    private MerchantDao merchantDao;
    @Autowired
    private DiscountProductDao discountProductDao;
    @Autowired
    private CouponSettingService couponSettingService;
    @Autowired
    private CouponTypeDao couponTypeDao;

    @RequestMapping("getAllDiscounts")
    public String getAllDiscounts(@RequestParam(value = "merchantId", required = false) Integer merchantId, Model model) {
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

        List<DiscountProduct> discountProducts = new ArrayList<DiscountProduct>();
        if (merchant != null) {
            discountProducts = discountProductDao.queryDiscountByMerchantId(merchant.getId());
        } else {
            discountProducts = discountProductDao.queryAllDiscounts();
        }

        model.addAttribute("discountProducts", discountProducts);

        return "operativeAdmin/discountProductList";
    }

    @RequestMapping("toAddDiscountProduct")
    public String toAddDiscountProduct(@RequestParam("merchantId") Integer merchantId, Model model){
        Merchant merchant = merchantDao.queryMechantById(merchantId);
        model.addAttribute("merchant", merchant);

        return "operativeAdmin/addDiscountProduct";
    }

    @RequestMapping("addDiscountProduct")
    public String addDiscountProduct(DiscountProduct discountProduct) {
        discountProduct.setState(1);
        discountProduct.setCreateTime(new Date());
        discountProductDao.add(discountProduct);
        return "redirect:/admin/getAllDiscounts.do?merchantId=" + discountProduct.getMerchantId();
    }

    @RequestMapping("deleteDiscount")
    public String deleteDiscount(@RequestParam("discountProductId") Integer discountProductId,
                                 @RequestParam(value = "merchantId",required = false) Integer merchantId) {
        couponSettingService.deleteDiscountProduct(discountProductId);

        if (null != merchantId) {
            return "redirect:/admin/getAllDiscounts.do?merchantId=" + merchantId;
        } else {
            return "redirect:/admin/getAllDiscounts.do";
        }
    }

    @RequestMapping("editDiscountProduct")
    public String editDiscountProduct(@RequestParam("discountProductId") Integer discountProductId, Model model) {
        DiscountProduct discountProduct = discountProductDao.queryDiscountById(discountProductId);
        model.addAttribute("discountProduct", discountProduct);

        return "operativeAdmin/editDiscountProduct";
    }


    @RequestMapping("updateDiscountProduct")
    public String updateDiscountProduct(DiscountProduct discountProduct){
        DiscountProduct newDiscount = discountProductDao.queryDiscountById(discountProduct.getId());
        newDiscount.setName(discountProduct.getName());
        newDiscount.setDiscount(discountProduct.getDiscount());
        newDiscount.setIcon(discountProduct.getIcon());
        newDiscount.setUseMsg(discountProduct.getUseMsg());
        newDiscount.setLimitMsg(discountProduct.getLimitMsg());
        newDiscount.setValidity(discountProduct.getValidity());
        newDiscount.setIsShared(CouponUtil.getBooleanValue(discountProduct.getIsShared()));
        newDiscount.setIsSendout(CouponUtil.getBooleanValue(discountProduct.getIsSendout()));
        newDiscount.setUpdateTime(new Date());
        discountProductDao.updateDiscount(newDiscount);

        return "redirect:/admin/getAllDiscounts.do?merchantId=" + newDiscount.getMerchantId();
    }

    @RequestMapping("toBindDiscount")
    public String toBindDiscount(@RequestParam("couponTypeId") Integer couponTypeId, @RequestParam("isSelfMerchant") Boolean isSelfMerchant, Model model) {
        CouponType couponType = couponTypeDao.queryCouponTypeById(couponTypeId);
        Integer merchantId = couponType.getMerchantId();

        List<DiscountProduct> hadDiscounts = discountProductDao.queryDiscountByCouponTypeId(couponTypeId);
        model.addAttribute("hadDiscounts", hadDiscounts);

        List<DiscountProduct> choiceDiscounts = new ArrayList<DiscountProduct>();
        if (isSelfMerchant) {
            choiceDiscounts.addAll(discountProductDao.queryDiscountByMerchantId(merchantId));
        } else {
            List<DiscountProduct> otherMerDiscounts = discountProductDao.querySendoutDisOtherMerchant(merchantId);
            if (!ListUtil.isEmptyList(hadDiscounts) && !ListUtil.isEmptyList(otherMerDiscounts)) {
                for (DiscountProduct dis : hadDiscounts) {
                    if (otherMerDiscounts.contains(dis)) {
                        otherMerDiscounts.remove(dis);
                    }
                }
            }
            choiceDiscounts.addAll(otherMerDiscounts);
        }
        model.addAttribute("choiceDiscounts", choiceDiscounts);
        return "operativeAdmin/bindDiscount";
    }

}
