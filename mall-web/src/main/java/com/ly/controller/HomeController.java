package com.ly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author YiMeng
 * @DateTime: 2022/8/6 9:36
 * @Description: TODO
 */
@Controller
public class HomeController {


    @GetMapping("/")
    public String home() {
        return "/fore/homePage";
    }


}
