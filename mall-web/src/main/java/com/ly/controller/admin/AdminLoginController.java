package com.ly.controller.admin;

import com.ly.pojo.Admin;
import com.ly.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YiMeng
 * @DateTime: 2022/8/14 20:52
 * @Description: TODO
 */
@Controller
public class AdminLoginController {


    @Resource
    private AdminService adminService;

    /**
     * 跳转到login页面
     *
     * @return
     */
    @GetMapping("/admin/login")
    public String loginPage() {
        return "/admin/loginPage";
    }

    /**
     * 注销功能
     *
     * @param httpSession
     * @return
     */
    @GetMapping("/admin/account/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return loginPage();
    }

    @ResponseBody
    @PostMapping("/admin/login/doLogin")
    public Map<String, Object> doLogin(String username, @RequestParam(defaultValue = "", required = false) String password, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<>(2);
        Admin admin = adminService.getAdminByUserName(username);
        if (password.equals(admin.getAdminPassword())) {
            httpSession.setAttribute("admin", admin);
            map.put("success", true);
            return map;
        }
        map.put("success", false);
        return map;
    }


}
