package com.picto.controller.operativeAdmin;

import com.picto.dao.MerchantDao;
import com.picto.entity.Merchant;
import com.picto.enums.CouponSaveTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BF100271 on 2016/5/24.
 */
@Controller
@RequestMapping("/admin")
public class MerchantMngController {
    @Autowired
    private MerchantDao merchantDao;

    @RequestMapping("getAllMerchant")
    public String getAllMerchant(@RequestParam(value = "merchantId", required = false) Integer merchantId, Model model) {
        List<Merchant> allMerchants = merchantDao.queryAllMerchants();

        List<Merchant> merchants = null;
        if (null != merchantId && 0 != merchantId.intValue()) {
            Merchant merchant = merchantDao.queryMechantById(merchantId);
            merchants = new ArrayList<Merchant>(1);
            merchants.add(merchant);
            model.addAttribute("selectedMerchantId", merchant.getId());

        } else {
            merchants = allMerchants;
            model.addAttribute("selectedMerchantId", null);
        }
        model.addAttribute("allMerchants", allMerchants);
        model.addAttribute("merchants", merchants);
        return "operativeAdmin/merchantMng";
    }

    @RequestMapping("editMerchant")
    public String editMerchant(@RequestParam("merchantId") Integer merchantId, Model model) {
        model.addAttribute("merchant", merchantDao.queryMechantById(merchantId));
        List list = CouponSaveTypeEnum.getCodeAndDesc();
        model.addAttribute("saveTypeCodes", (List<Integer>) list.get(0));
        model.addAttribute("saveTypeDescs", (List<String>) list.get(1));

        return "operativeAdmin/editMerchant";
    }

    @RequestMapping("updateMerchant")
    public void updateMerchant(Merchant merchant) {
        merchantDao.updateMerchant(merchant);
        //TODO 编辑商户信息后保存跳转流程
    }
}
