package com.picto.controller;

import com.picto.dao.DiscountProductDao;
import com.picto.entity.Coupon;
import com.picto.entity.DiscountProduct;
import com.picto.entity.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by BF100271 on 2016/5/24.
 */
@Controller
public class ChoiceDiscountController {
    @Autowired
    private DiscountProductDao discountProductDao;

    @RequestMapping("/choiceDiscount")
    public String choiceDiscount(Model model, HttpServletRequest request) {
        Merchant merchant = (Merchant) request.getSession().getAttribute("merchant");
        String discountProductId = request.getParameter("discountProductId");

        //TODO 根据选择的优惠产品生成优惠券并跳转到优惠券信息页
        Coupon coupon = new Coupon();

        model.addAttribute("coupon", coupon);
        return "choiceDiscount";
    }
}
