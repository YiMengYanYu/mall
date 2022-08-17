package com.ly.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.pojo.*;
import com.ly.service.*;
import com.ly.utils.PageUtil;
import com.ly.vo.DataList;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;
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

        String name = null;
        try {
            name = URLDecoder.decode(productName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (productIsEnabledArray == null || productIsEnabledArray.length == 0) {
            productIsEnabledArray = null;
        }
        PageUtil<Product> pageUtil = productService.getProduct(name, categoryId, productSalePrice, productPrice, productIsEnabledArray, orderBy, isDesc, startIndex, pageSize);

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
    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/product/{id}")
    public Map<String, Object> putProduct(@PathVariable("id") Long id, DataList dataList) {
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
        insetDetailsImg(dataList, id);
        insetSingleImg(dataList, id);
        try {
            map = objectMapper.readValue(dataList.getPropertyAddJson(), Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        insertProperty(map, id);
        Map<String, Object> som = new HashMap<>(2);
        som.put("success", true);
        som.put("productId", id);
        return som;
    }

    /**
     * 添加SingleImg
     *
     * @param dataList
     * @param productId
     */
    public void insetSingleImg(DataList dataList, Long productId) {
        if (dataList.getProductSingleImageList() != null) {
            for (String s : dataList.getProductSingleImageList()) {
                Productimage productimage = new Productimage();
                productimage.setProductimageProductId(productId);
                productimage.setProductimageSrc(s);
                productimage.setProductimageType(0);
                productimageService.insertProductimage(productimage);
            }
        }

    }

    /**
     * 添加Detail图片
     *
     * @param dataList
     * @param productId
     */
    public void insetDetailsImg(DataList dataList, Long productId) {
        if (dataList.getProductDetailsImageList() != null) {
            for (String o : dataList.getProductDetailsImageList()) {
                Productimage productimage = new Productimage();
                productimage.setProductimageProductId(productId);
                productimage.setProductimageSrc(o);
                productimage.setProductimageType(1);
                productimageService.insertProductimage(productimage);
            }
        }
    }

    @GetMapping("/product/new")
    public String newProduct(Model model) {
        List<Category> category = categoryService.getCategory();
        model.addAttribute("categoryList", category);
        List<Property> property = propertyService.getProperty(String.valueOf(category.get(0).getCategoryId()));
        model.addAttribute("propertyList", property);
        return "admin/include/productDetails";
    }

    public void insertProperty(Map<Integer, String> map, Long productId) {

        for (Map.Entry<Integer, String> ism : map.entrySet()) {
            Propertyvalue propertyvalue = new Propertyvalue();
            propertyvalue.setPropertyValueProductId(productId);
            long valuePropertyId = Long.parseLong(String.valueOf(ism.getKey()));
            propertyvalue.setPropertyValuePropertyId(valuePropertyId);
            propertyvalue.setPropertyValueValue(ism.getValue());
            propertyvalueService.insertPropertyvalue(propertyvalue);

        }
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @PostMapping("/product")
    public Map<String, Object> insertProduct(DataList dataList) {
        Product product = new Product();
        product.setProductCreateDate(new Timestamp(System.currentTimeMillis()));
        BeanUtils.copyProperties(dataList, product);
        Map<Integer, String> map = null;
        try {
            map = objectMapper.readValue(dataList.getPropertyJson(), Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        productService.insertProduct(product);
        long productId = product.getProductId();

        insetSingleImg(dataList, productId);
        insetDetailsImg(dataList, productId);
        insertProperty(map, productId);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("success", true);
        objectMap.put("productId", productId);
        return objectMap;
    }
}
