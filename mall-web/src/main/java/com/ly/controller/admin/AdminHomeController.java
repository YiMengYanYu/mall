package com.ly.controller.admin;

import com.ly.pojo.Product;
import com.ly.service.CategoryService;
import com.ly.service.ProductService;
import com.ly.service.ProductorderService;
import com.ly.service.UserService;
import com.ly.utils.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YiMeng
 * @DateTime: 2022/8/14 21:07
 * @Description: TODO
 */
@Controller
public class AdminHomeController {
    @Resource
    private CategoryService categoryService;
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
    @GetMapping(value = {"/admin", "/admin/home"})
    public String homePage(Model model, HttpServletRequest httpRequest) {
        model.addAttribute("productTotal", productService.getProductCount());
        model.addAttribute("userTotal", userService.getUserCount());
        model.addAttribute("orderTotal", productorderService.getProductorderCount());
        httpRequest.getServletContext().setAttribute("categoryList", categoryService.getCategoryAllImpl2());
        return "/admin/homePage";
    }



}
