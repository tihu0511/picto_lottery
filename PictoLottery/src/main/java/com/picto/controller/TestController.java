package com.picto.controller;

import com.picto.vo.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BF100271 on 2016/5/21.
 */
@Controller
public class TestController {
    @RequestMapping("/test.do")
    public ModelAndView test(Test test) {
        System.out.println(test);
        test.setName("Huozhanbai");

        ModelAndView view = new ModelAndView("/jsp/test");
        view.addObject("test", test);
        return view;
    }

    @RequestMapping("/testIn.do")
    public ModelAndView testIn() {
        return new ModelAndView("/jsp/testIn");
    }
}
