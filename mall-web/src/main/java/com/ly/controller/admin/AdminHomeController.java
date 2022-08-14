package com.ly.controller.admin;

import com.ly.mapper.ProductorderMapper;
import com.ly.service.ProductService;
import com.ly.service.ProductorderService;
import com.ly.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @author YiMeng
 * @DateTime: 2022/8/14 21:07
 * @Description: TODO
 */
@Controller
public class AdminHomeController {

    @Resource
    private ProductService productService;

    @Resource
    private UserService userService;
    @Resource
    private ProductorderService productorderService;
    /**
     * 跳转到主页
     *
     * @return
     */
    @GetMapping("/admin")
    public String homePage(Model model) {
        model.addAttribute("productTotal",productService.getProductCount());
        model.addAttribute("userTotal",userService.getUserCount());
        model.addAttribute("orderTotal",productorderService.getProductorderCount());


        return "/admin/homePage";
    }




}
