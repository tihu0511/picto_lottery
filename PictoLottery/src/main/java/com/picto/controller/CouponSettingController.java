package com.picto.controller;

import com.picto.dao.CouponTypeDao;
import com.picto.entity.CouponType;
import com.picto.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by BF100271 on 2016/5/24.
 */
@Controller
public class CouponSettingController {
    private static final Logger logger = Logger.getLogger(CouponSettingController.class);

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
        couponType.setUpdateTime(new Date());
        couponTypeDao.updateCouponType(couponType);
        //TODO 编辑奖项后的保存跳转
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String merchantIdStr = "1";

        Map<String, Object> retMap = new HashMap<String, Object>();

        if (file != null) {
            String fileName = file.getOriginalFilename();
            String path1 = request.getSession().getServletContext().getRealPath("uploads")
                    + File.separator + merchantIdStr + File.separator;

            //下面的加的日期是为了防止上传的名字一样
            String fName = fileName.substring(0, fileName.lastIndexOf("."));
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String path = path1 + fName + "_" + DateUtil.formatDate(new Date(), "yyyyMMddHHmmss") + suffix;

            File localFile = new File(path);
            if (!localFile.exists()) {
                try {
                    localFile.createNewFile();
                } catch (IOException e) {
                    logger.error("创建目录" + path + "失败");
                    retMap.put("result", "failed");
                    return retMap;
                }
            }

            try {
                file.transferTo(localFile);
                retMap.put("result", "success");
                retMap.put("filePath", path);
            } catch (IOException e) {
                logger.error("保存上传的文件[" + fileName + "]失败");
                retMap.put("result", "failed");
            }
        }

        return retMap;
    }
}
