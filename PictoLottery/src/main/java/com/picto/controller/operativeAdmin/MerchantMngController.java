package com.picto.controller.operativeAdmin;

import com.picto.dao.MerchantDao;
import com.picto.entity.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getAllMerchant(Model model) {
        List<Merchant> merchants = merchantDao.queryAllMerchants();
        model.addAttribute("merchants", merchants);
        return "operativeAdmin/merchantMng";
    }

    @RequestMapping("editMerchant")
    public String editMerchant(@RequestParam("merchantId") Integer merchantId, Model model) {
        model.addAttribute("merchant", merchantDao.queryMechantById(merchantId));
        return "editMerchant";
    }

    @RequestMapping("updateMerchant")
    public void updateMerchant(Merchant merchant) {
        merchantDao.updateMerchant(merchant);
        //TODO 编辑商户信息后保存跳转流程
    }
}
