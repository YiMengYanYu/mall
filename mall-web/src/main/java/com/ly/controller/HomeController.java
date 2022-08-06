package com.ly.controller;

import com.ly.mapper.CategoryMapper;
import com.ly.pojo.Product;
import com.ly.service.CategoryService;
import com.ly.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/6 9:36
 * @Description: TODO
 */
@Controller
public class HomeController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private ProductService productService;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("categoryList",categoryService.getCategoryAll());
        List<Product> product = productService.getProductAll();
        System.out.println("_________________________________");
        System.out.println(product);
        model.addAttribute("specialProductList",product);
        return "/fore/homePage";
    }


}
