package com.picto.controller;

import com.picto.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wujigang on 2016/6/1.
 */
@Controller
public class UploadFileController {
    private static final Logger logger = Logger.getLogger(UploadFileController.class);

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

            File directory = new File(path1);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File localFile = new File(path);
            try {
                file.transferTo(localFile);
                retMap.put("result", "success");

                String projectDir = request.getSession().getServletContext().getRealPath("/");
                retMap.put("filePath", path.replace(projectDir, File.separator));
            } catch (IOException e) {
                logger.error("保存上传的文件[" + fileName + "]失败");
                retMap.put("result", "failed");
            }
        }

        return retMap;
    }
}
