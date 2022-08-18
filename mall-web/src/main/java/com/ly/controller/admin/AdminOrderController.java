package com.ly.controller.admin;

import com.ly.pojo.Productorder;
import com.ly.service.ProductorderService;
import com.ly.utils.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YiMeng
 * @DateTime: 2022/8/17 23:37
 * @Description: TODO
 */
@Controller
@RequestMapping("/admin")
public class AdminOrderController {
    @Resource
    private ProductorderService productorderService;

    @GetMapping("/order")
    public String doOrder(Model model) {
        final PageUtil<Productorder> productorder = productorderService.getProductorder(null, "", "", null, null, 0, 10);
        model.addAttribute("productOrderList",productorder.getList());
        model.addAttribute("pageUtil",productorder);
        return "/admin/orderManagePage";
    }

    @ResponseBody
    @GetMapping("/order/{starIndex}/{pageSize}")
    public Map<String,Object> toOrder(Integer[] productorderStatusArray, String productorderPost, String productorderCode, String orderBy, Boolean isDesc, @PathVariable("starIndex") Integer starIndex, @PathVariable("pageSize") Integer pageSize){
        Map<String,Object> map = new HashMap<>(2);
        PageUtil<Productorder> productorder = productorderService.getProductorder(productorderStatusArray == null ? null : Arrays.asList(productorderStatusArray), productorderPost, productorderCode, orderBy, isDesc, starIndex, pageSize);
        map.put("productOrderList",productorder.getList());
        map.put("pageUtil",productorder);
        return map;
    }
}
