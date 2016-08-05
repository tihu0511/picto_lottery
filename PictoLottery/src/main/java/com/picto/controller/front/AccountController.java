package com.picto.controller.front;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.picto.constants.Constants;
import com.picto.dao.CouponDao;
import com.picto.dao.CouponTypeDao;
import com.picto.dao.DiscountProductDao;
import com.picto.dao.MerchantDao;
import com.picto.dao.OperationRecordDao;
import com.picto.entity.Account;
import com.picto.entity.Coupon;
import com.picto.entity.CouponType;
import com.picto.entity.DiscountProduct;
import com.picto.entity.OperationRecord;
import com.picto.service.AccountService;
import com.picto.util.DateUtil;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CouponDao couponDao;
	
	@Autowired
	private CouponTypeDao couponTypeDao;
	
	@Autowired
	private MerchantDao merchantDao;

    @Autowired
    private OperationRecordDao operationRecordDao;
    
    @Autowired
    private DiscountProductDao discountProductDao;
    
	/**
	 * 登录（页面）
	 * @return
	 */
	@RequestMapping("login")
	public String login(){
		return "account/login";
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping("doLogin")
	public String doLogin(HttpServletRequest request){
		accountService.login(request);
		return "redirect:account/merchantDetail.do";
	}
	
	/**
	 * 修改密码（页面）
	 * @return
	 */
	@RequestMapping("account/modifyPwd")
	public String modifyPwd(HttpServletRequest request){
		Account account = getSessionAccount(request);
		if(null == account)
			return "redirect:/login.do";
		return "account/modifyPwd";
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping("account/doModifyPwd")
	public String doModifyPwd(HttpServletRequest request){
		Account account = getSessionAccount(request);
		if(null == account)
			return "redirect:/login.do";
		accountService.modifyPwd(request);
		return "redirect:account/merchantDetail.do";
	}
	
	/**
	 * 商戶詳情
	 * @return
	 */
	@RequestMapping("account/merchantDetail")
	public String merchantDetail(HttpServletRequest request){
		Account account = getSessionAccount(request);
		if(null == account)
			return "redirect:/login.do";
		// 商户
		Integer merchantId = account.getMerchantId();
		request.setAttribute("merchant", merchantDao.queryMechantById(merchantId));

		// 商户奖品
        Date current = new Date();
        Date currMonthFirstTime = DateUtil.getMonthFirstTime(current, 0);
        System.out.println(currMonthFirstTime);
        Date currMonthEndTime = DateUtil.getMonthLastTime(current, 0);
        System.out.println(currMonthEndTime);
        List<DiscountProduct> discountProducts = discountProductDao.queryDiscountByMerchantId(merchantId);
        
        List<Object []> results = new ArrayList<Object []>();
		for(DiscountProduct dp : discountProducts){
			int couponsPresented = 0;// 派发数
			int couponsExchanged = 0;// 兑换数
			List<Coupon> couponsp = couponDao.queryExchangedCouponsByDiscountProductIdAndTime(merchantId, dp.getId(), null, currMonthFirstTime, currMonthEndTime);
			if(null != couponsp && !couponsp.isEmpty())
				couponsPresented = couponsp.size();

	        System.out.println(couponsPresented);
	        
			List<Coupon> couponse = couponDao.queryExchangedCouponsByDiscountProductIdAndTime(merchantId, dp.getId(), Constants.COUPON_STATE_EXCHANGED, currMonthFirstTime, currMonthEndTime);
			if(null != couponse && !couponse.isEmpty())
				couponsExchanged = couponse.size();
			
	        System.out.println(couponsExchanged);
	        
			results.add(new Object[]{dp, couponsPresented, couponsExchanged});
		}
		request.setAttribute("results", results);
//		// 商户奖品
//		List<CouponType> couponTypes = couponTypeDao.queryAllCouponTypesByMerchantId(merchantId);
//		request.setAttribute("couponTypes", couponTypes);
//		
//		// 当日抽数量
//        Date current = new Date();
//        String today = DateUtil.formatDate(current, "yyyyMMdd");
//        Date begin = DateUtil.parseDate(today, "yyyyMMdd");
//        Date end = DateUtil.addDays(begin, 1);
//        List<OperationRecord> operationToday = operationRecordDao.queryAllOpersByTime(
//                Constants.OPERATION_TYPE_LOTTERY, merchantId, begin, end);
//        if(null != operationToday && !operationToday.isEmpty())
//        	request.setAttribute("todayNum", operationToday.size());
//        // 当日兑数量
//        List<OperationRecord> operationTodayC = operationRecordDao.queryAllOpersByTime(
//                Constants.OPERATION_TYPE_EXCHANGED, merchantId, begin, end);
//        if(null != operationTodayC && !operationTodayC.isEmpty())
//        	request.setAttribute("todayCNum", operationTodayC.size());
//        
//        // 当月抽数量
//        Date currMonthFirstTime = DateUtil.getMonthFirstTime(current, 0);
//        Date currMonthEndTime = DateUtil.getMonthLastTime(current, 0);
//        List<OperationRecord> operationMonth = operationRecordDao.queryAllOpersByTime(
//                Constants.OPERATION_TYPE_LOTTERY, merchantId, currMonthFirstTime, currMonthEndTime);
//        if(null != operationMonth && !operationMonth.isEmpty())
//        	request.setAttribute("monthNum", operationMonth.size());
//        // 当月兑数量
//        List<OperationRecord> operationMonthC = operationRecordDao.queryAllOpersByTime(
//                Constants.OPERATION_TYPE_EXCHANGED, merchantId, currMonthFirstTime, currMonthEndTime);
//        if(null != operationMonthC && !operationMonthC.isEmpty())
//        	request.setAttribute("monthCNum", operationMonthC.size());
        
		return "account/merchantDetail";
	}

	/**
	 * 获取登录的用户
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	private Account getSessionAccount(HttpServletRequest request){
		Account account = (Account) request.getSession().getAttribute(Constants.SESSION_ACCOUNT);
		return account;
	}
}
