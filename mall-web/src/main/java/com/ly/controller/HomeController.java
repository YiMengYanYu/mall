package com.ly.controller;

import com.ly.pojo.Category;
import com.ly.pojo.Product;
import com.ly.pojo.Property;
import com.ly.service.CategoryService;
import com.ly.service.ProductService;
import com.ly.service.PropertyService;
import com.ly.service.PropertyvalueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    PropertyvalueService propertyvalueService;
    @Resource
    PropertyService propertyService;
     @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categoryList", categoryService.getCategoryAllImpl2());
        List<Product> product = productService.getProductIsEnabledEq2();
        model.addAttribute("specialProductList", product);
        return "/fore/homePage";
    }

    @ResponseBody
    @GetMapping("/testHomeJson1")
    public List<Category> testHomeJson1() {
        return categoryService.getCategoryAll();
    }


    @ResponseBody
    @GetMapping("/testHomeJson2")
    public List<Category> testHomeJson2() {
        List<Category> categoryAll = categoryService.getCategoryAllImpl2();
        return categoryAll;
    }

    @GetMapping("/product")
    public String product(String productName,String  categoryId, Model model) {
        model.addAttribute("searchValue", productName);
        model.addAttribute("searchType",categoryId);
        if (ObjectUtils.isEmpty(categoryId)){
            model.addAttribute("productList", productService.getProductByProductName(productName));
        }else {
            List<Product> productSoft = productService.getProductSoft(null, null, null, null, true, categoryId);
            model.addAttribute("productList",productSoft);
        }
        return "/fore/productListPage";
    }

    @GetMapping("/product/{startIndex}/{endIndex}")
    public String product(@PathVariable String startIndex, @PathVariable String endIndex, String orderBy, String productName, boolean isDesc,Model model,String categoryId) {
        List<Product> productSoft;
        if (!ObjectUtils.isEmpty(categoryId)){
            productSoft  = productService.getProductSoft(startIndex, endIndex, orderBy, null, isDesc, categoryId);

        }else {
            productSoft = productService.getProductSoft(startIndex, endIndex, orderBy, productName, isDesc, null);
        }

        model.addAttribute("productList",productSoft);
        model.addAttribute("startIndex",startIndex);
        model.addAttribute("endIndex",endIndex);
        model.addAttribute("orderBy",orderBy);
        model.addAttribute("searchType",categoryId);
        model.addAttribute("productName",productName);
        model.addAttribute("searchValue",productName);
        model.addAttribute("isDesc",isDesc);
        return "/fore/productListPage";

    }



    @GetMapping("/product/{id}")
    public String productDetails(@PathVariable("id") String id, Model model) {
        List<Product> loveProductList = productService.getLoveProductList();
        var productByProductId = productService.getProductByProductId(id);
        final List<Property> propertyAndPropertyvalue = propertyService.getPropertyAndPropertyvalue(id);
        model.addAttribute("propertyList", propertyAndPropertyvalue);

        model.addAttribute("product", productService.getProductByProductId(id));
        model.addAttribute("loveProductList", loveProductList);
        return "/fore/productDetailsPage";
    }

    @ResponseBody
    @GetMapping("/product/nav/{id}")
    public Map<String, Object> testHomeJson2(@PathVariable String id) {
        var plist = productService.getProductByProductCategoryId(id);
        Map<String, Object> map = new HashMap<>(2);
        map.put("success", true);
        map.put("category", plist);
        return map;

    }

    @ResponseBody
    @GetMapping("/guess/{id}")
    public Map<String, Object> guess(@PathVariable String id) {
        List<Product> loveProductList = productService.getLoveProductList();
        Map<String, Object> map = new HashMap<>(2);
        map.put("success", true);
        map.put("loveProductList", loveProductList);
        return map;

    }





}
