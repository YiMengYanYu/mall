package com.ly.controller;

import com.ly.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YiMeng
 * @DateTime: 2022/8/6 8:51
 * @Description: TODO
 */
@Controller
public class LoginController {

    @Resource
    UserService userService;

    @GetMapping("/login")
    public String login() {

        return "/fore/loginPage";
    }



    @ResponseBody
    @PostMapping("/login/doLogin")
    public Map<String, Boolean> doLogin(String username, String password, HttpSession httpSession) {
        Map<String, Boolean> map = new HashMap<>(2);
        System.out.println("-------------------------------------"+username+password);
        if (userService.login(username, password)) {
            httpSession.setAttribute("user", userService.getUserByUserName(username));
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }


}
