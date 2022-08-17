package com.ly.controller.admin;

import com.ly.pojo.Product;
import com.ly.pojo.Property;
import com.ly.service.ProductService;
import com.ly.service.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YiMeng
 * @DateTime: 2022/8/17 2:22
 * @Description: TODO
 */
@Controller
@RequestMapping("/admin")
public class AdminPropertyController {

    @Resource
    private PropertyService propertyService;

    @Resource
    private ProductService productService;

    @ResponseBody
    @GetMapping("/property/type/{id}")
    public Map<String, Object> propertyType(@PathVariable("id") String id) {
        Map<String, Object> map = new HashMap<>(2);
//        Product productByProductId = productService.getProductByProductId(id);
        List<Property> property = propertyService.getProperty(id);
        map.put("propertyList", property);
        return map;
    }


}
