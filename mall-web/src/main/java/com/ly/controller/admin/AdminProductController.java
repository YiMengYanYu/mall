package com.ly.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.pojo.Product;
import com.ly.pojo.Productimage;
import com.ly.pojo.Property;
import com.ly.pojo.Propertyvalue;
import com.ly.service.*;
import com.ly.utils.PageUtil;
import com.ly.vo.DataList;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
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
    private ObjectMapper objectMapper;

    @Resource
    private CategoryService categoryService;
    @Resource
    private PropertyService propertyService;
    @Resource
    private ProductService productService;
    @Resource
    private PropertyvalueService propertyvalueService;
    @Resource
    private ProductimageService productimageService;

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
    public Map<String, Object> productPage(@RequestParam(defaultValue = "") String productName, Integer categoryId, Double productSalePrice, Double productPrice, Integer[] productIsEnabledArray, String orderBy, Boolean isDesc, @PathVariable("startIndex") Integer startIndex, @PathVariable("pageSize") Integer pageSize) {
        System.out.println();
        String name = null;
        try {
            name = URLDecoder.decode(productName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if ( productIsEnabledArray==null ||productIsEnabledArray.length == 0) {
            productIsEnabledArray = null;
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
    public String getProductInfo(@PathVariable("id") String id, Model model, HttpServletRequest httpServletRequest) {
        ServletContext servletContext = httpServletRequest.getServletContext();

        model.addAttribute("categoryList", categoryService.getCategory());
        Product product = productService.getProductByProductId(id);
        List<Property> propertyList = propertyService.getPropertyAndPropertyvalue(id, "" + product.getProductCategoryId());
        model.addAttribute("propertyList", propertyList);
        model.addAttribute("product", product);
        return "admin/include/productDetails";
    }

    @ResponseBody
    @Transactional
    @PutMapping("/product/{id}")
    public Map<String, Object> putProduct(@PathVariable("id") Long id, DataList dataList) {
        DataList dataList1 = dataList;
        Map<Integer, String> map = null;

        Product product = new Product();
        product.setProductId(id);
        product.setProductName(dataList.getProductName());
        product.setProductTitle(dataList.getProductTitle());
        product.setProductPrice(dataList.getProductPrice());
        product.setProductSalePrice(dataList.getProductSalePrice());
        product.setProductCategoryId(dataList.getProductCategoryId());
        product.setProductIsEnabled(dataList.getProductIsEnabled());
        productService.putProduct(product);
        if (dataList.getProductDetailsImageList() != null) {
            for (String o : dataList.getProductDetailsImageList()) {
                Productimage productimage = new Productimage();
                productimage.setProductimageProductId(id);
                productimage.setProductimageSrc(o);
                productimage.setProductimageType(1);
                productimageService.insertProductimage(productimage);
            }
        }
        if (dataList.getProductSingleImageList() != null) {
            for (String s : dataList.getProductSingleImageList()) {
                Productimage productimage = new Productimage();
                productimage.setProductimageProductId(id);
                productimage.setProductimageSrc(s);
                productimage.setProductimageType(0);
                productimageService.insertProductimage(productimage);
            }
        }


        try {
            map = objectMapper.readValue(dataList.getPropertyAddJson(), Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        for (Map.Entry<Integer, String> ism : map.entrySet()) {
            Propertyvalue propertyvalue = new Propertyvalue();
            propertyvalue.setPropertyValueProductId(id);
            propertyvalue.setPropertyValuePropertyId(Long.parseLong(String.valueOf(ism.getKey())));
            propertyvalue.setPropertyValueValue(ism.getValue());
            propertyvalueService.insertPropertyvalue(propertyvalue);
        }
        Map<String, Object> som = new HashMap<>(2);
        som.put("success", true);
        som.put("productId", id);
        return som;
    }


}
