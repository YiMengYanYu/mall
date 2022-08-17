package com.ly.controller.admin;

import com.ly.pojo.Category;
import com.ly.pojo.Property;
import com.ly.service.CategoryService;
import com.ly.service.PropertyService;
import com.ly.utils.FileUpload;
import com.ly.utils.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
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
    @Resource
    private PropertyService propertyService;


    @ResponseBody
    @PostMapping("/uploadCategoryImage")
    public Map<String, Object> uploadCategoryImage(MultipartFile file, HttpSession httpSessionp) {
        List<String> list = FileUpload.saveImgUpload(httpSessionp, "/categoryPicture", file);
        final String name = list.get(0);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("fileName", name);
        return map;

    }

    @GetMapping("/category")
    public String toCategroy(Model model) {
        PageUtil<Category> category = categoryService.getCategoryByName(null, 0, 10);
        model.addAttribute("categoryCount", categoryService.getCategoryCount());
        model.addAttribute("categoryList", category.getList());
        model.addAttribute("pageUtil", category);
        return "/admin/categoryManagePage";
    }

    @ResponseBody
    @PutMapping("/category/{categoryId}")
    public Map<String, Object> putcategory(@PathVariable("categoryId") Long id, Category dataList) {
        Map<String, Object> map = new HashMap<>(2);
        dataList.setCategoryId(id);
        map.put("success", categoryService.updateCategory(dataList));
        map.put("categoryId", id);
        return map;
    }


    @GetMapping("/category/new")
    public String putcategory() {
        return "/admin/include/categoryDetails";
    }

    @ResponseBody
    @PostMapping("/category")
    public Map<String, Object> category(Category dataList) {
        Map<String, Object> map = new HashMap(2);
        map.put("success", categoryService.insertCategory(dataList));
        map.put("categoryId", dataList.getCategoryId());
        return map;
    }

    @GetMapping("/category/{id}")
    public String toCategroyInfo(Model model, @PathVariable("id") Long id) {
        List<Property> propertyList = propertyService.getPropertBypropertyCategoryId(id);
        Category category = categoryService.getCategoryById(id);
        category.setPropertyList(propertyList);
        model.addAttribute("category", category);
        return "/admin/include/categoryDetails";
    }

    @ResponseBody
    @GetMapping("/category/{startIndex}/{endIndex}")
    public Map<String, Object> toCategroy(String categoryName, @PathVariable("startIndex") Integer startIndex, @PathVariable("endIndex") Integer endIndex) {
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
