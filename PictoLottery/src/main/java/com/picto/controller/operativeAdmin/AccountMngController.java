package com.picto.controller.operativeAdmin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.picto.constants.Constants;
import com.picto.dao.CouponTypeDao;
import com.picto.dao.MerchantDao;
import com.picto.dao.OperationRecordDao;
import com.picto.entity.Account;
import com.picto.entity.CouponType;
import com.picto.entity.OperationRecord;
import com.picto.service.AccountService;
import com.picto.util.DateUtil;

@Controller
public class AccountMngController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CouponTypeDao couponTypeDao;
	
	@Autowired
	private MerchantDao merchantDao;

    @Autowired
    private OperationRecordDao operationRecordDao;
    
	/**
	 * 商户注册（页面）
	 * @return
	 */
	@RequestMapping("merchantRegister")
	public String merchantRegister(){
		return "operativeAdmin/merchantRegister";
	}
	
	/**
	 * 商户注册
	 * @return
	 */
	@RequestMapping("doMerchantRegister")
	public String doMerchantRegister(HttpServletRequest request){
		accountService.merchantRegister(request);
		return "redirect:/admin/getAllCouponTypes.do";
	}
	
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
		return "redirect:merchantDetail.do";
	}
	
	/**
	 * 修改密码（页面）
	 * @return
	 */
	@RequestMapping("modifyPwd")
	public String modifyPwd(HttpServletRequest request){
		Account account = getSessionAccount(request);
		if(null == account)
			return "redirect:login.do";
		return "account/modifyPwd";
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping("doModifyPwd")
	public String doModifyPwd(HttpServletRequest request){
		Account account = getSessionAccount(request);
		if(null == account)
			return "redirect:login.do";
		accountService.modifyPwd(request);
		return "redirect:merchantDetail.do";
	}
	
	/**
	 * 商戶詳情
	 * @return
	 */
	@RequestMapping("merchantDetail")
	public String merchantDetail(HttpServletRequest request){
		Account account = getSessionAccount(request);
		if(null == account)
			return "redirect:login.do";
		System.out.println(account.getId());
		// 商户
		Integer merchantId = account.getMerchantId();
		System.out.println(merchantId);
		request.setAttribute("merchant", merchantDao.queryMechantById(merchantId));
		// 商户奖品
		List<CouponType> couponTypes = couponTypeDao.queryAllCouponTypesByMerchantId(merchantId);
		request.setAttribute("couponTypes", couponTypes);
		
		// 当日数量
        Date current = new Date();
        Date currMonthFirstTime = DateUtil.getMonthFirstTime(current, 0);
        Date currMonthEndTime = DateUtil.getMonthLastTime(current, 0);
        List<OperationRecord> operationToday = operationRecordDao.queryAllOpersByTime(
                Constants.OPERATION_TYPE_LOTTERY, merchantId, currMonthFirstTime, currMonthEndTime);
        if(null == operationToday || operationToday.isEmpty())
        	request.setAttribute("todayNum", operationToday.size());
        
        // 当月数量
        String today = DateUtil.formatDate(current, "yyyyMMdd");
        Date begin = DateUtil.parseDate(today, "yyyyMMdd");
        Date end = DateUtil.addDays(begin, 1);
        List<OperationRecord> operationMonth = operationRecordDao.queryAllOpersByTime(
                Constants.OPERATION_TYPE_LOTTERY, merchantId, begin, end);
        if(null == operationToday || operationMonth.isEmpty())
        	request.setAttribute("monthNum", operationMonth.size());
        
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
