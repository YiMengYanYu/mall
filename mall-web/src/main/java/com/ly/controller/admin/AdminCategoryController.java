package com.ly.controller.admin;

import com.ly.pojo.Category;
import com.ly.service.CategoryService;
import com.ly.utils.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YiMeng
 * @DateTime: 2022/8/17 16:13
 * @Description: TODO
 */
@Controller
@RequestMapping("/admin")
public class AdminCategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/category")
    public String toCategroy(Model model) {
        PageUtil<Category> category = categoryService.getCategoryByName(null, 0, 10);

        model.addAttribute("categoryCount", categoryService.getCategoryCount());
        model.addAttribute("categoryList", category.getList());
        model.addAttribute("pageUtil", category);
        return "/admin/categoryManagePage";
    }
 @GetMapping("/category/{id}")
    public String toCategroyInfo(Model model,@PathVariable("id") Long id) {
        PageUtil<Category> category = categoryService.getCategoryByName(null, 0, 10);

        model.addAttribute("categoryCount", categoryService.getCategoryCount());
        model.addAttribute("categoryList", category.getList());
        model.addAttribute("pageUtil", category);
        return "/admin/include/categoryDetails";
    }

    @ResponseBody
    @GetMapping("/category/{startIndex}/{endIndex}")
    public Map<String, Object> toCategroy( String categoryName, @PathVariable("startIndex") Integer startIndex,@PathVariable("endIndex") Integer endIndex) {
        String decode = categoryName;
        try {
            decode = URLDecoder.decode(categoryName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        PageUtil<Category> category = categoryService.getCategoryByName(decode, startIndex, endIndex);
        Map<String, Object> map = new HashMap<>(2);
        map.put("categoryCount", categoryService.getCategoryCount());

        map.put("categoryCount", categoryService.getCategoryCount());
        map.put("pageUtil", category);
        map.put("categoryList", category.getList());


        return map;
    }

}
