package com.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.mapper.ProductorderitemMapper;
import com.ly.pojo.Product;
import com.ly.pojo.Productorderitem;
import com.ly.service.ProductService;
import com.ly.service.ProductorderitemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:10
 * @Description: TODO
 */
@Service
public class ProductorderitemServiceImpl implements ProductorderitemService {

    @Resource
    private ProductorderitemMapper productorderitemMapper;

    @Resource
    private ProductService productService;

    @Override
    public List<Productorderitem> getProductorderitemByOrderId(Long productorderitemOrderId) {
        QueryWrapper<Productorderitem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productorderitemOrderId", productorderitemOrderId);
        List<Productorderitem> productorderitems = productorderitemMapper.selectList(queryWrapper);
        for (Productorderitem productorderitem : productorderitems) {
            Product productByProductId = productService.getProductByProductId("" + productorderitem.getProductorderitemProductId());
            productorderitem.setProductorderitemProduct(productByProductId);
        }

        return productorderitems;
    }
}
