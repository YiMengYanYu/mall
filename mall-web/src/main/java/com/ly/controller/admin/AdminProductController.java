package com.ly.controller.admin;

import com.ly.pojo.Product;
import com.ly.service.ProductService;
import com.ly.utils.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YiMeng
 * @DateTime: 2022/8/16 13:57
 * @Description: TODO
 */
@RequestMapping("/admin")
@Controller
public class AdminProductController {


    @Resource
    private ProductService productService;


    /**
     * @param productName
     * @param categoryId
     * @param productSalePrice
     * @param productPrice
     * @param productIsEnabledArray
     * @param orderBy
     * @param isDesc
     * @param startIndex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @GetMapping("/product/{startIndex}/{pageSize}")
    public Map<String, Object> productPage(String productName, Integer categoryId, Double productSalePrice, Double productPrice, Integer[] productIsEnabledArray, String orderBy, Boolean isDesc, @PathVariable("startIndex") Integer startIndex, @PathVariable("pageSize") Integer pageSize) {
        System.out.println();
        String name = null;
        try {
            name = URLDecoder.decode(productName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        PageUtil<Product> pageUtil = productService.getProduct(name, categoryId, productSalePrice, productPrice, productIsEnabledArray, orderBy, isDesc, startIndex, pageSize);
        System.err.println(pageUtil.getTotalPage());
        Map<String, Object> map = new HashMap<>(2);
        map.put("pageUtil", pageUtil);
        return map;
    }

    @GetMapping("/product")
    public String product(Model model) {
        Integer[] integers = {0, 1, 2};
        PageUtil<Product> page = productService.getProduct(null, null, null, null, integers, null, null, 0, 10);
        model.addAttribute("productList", page.getList());
        return "/admin/productManagePage";
    }

    @GetMapping("/product/{id}")
    public String getProductInfo(@PathVariable("id") String id, Model model) {
        Product product = productService.getProductByProductId(id);
        model.addAttribute("product", product);
        return "admin/include/productDetails";
    }


}
