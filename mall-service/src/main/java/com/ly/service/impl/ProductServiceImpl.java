package com.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.mapper.ProductMapper;
import com.ly.pojo.Product;
import com.ly.service.ProductService;
import com.ly.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:15
 * @Description: TODO
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> getProductAll() {
        List<Product> getProductAll = redisUtil.getCacheList("getProductAll", Product.class);
        if (getProductAll == null) {
            getProductAll = productMapper.selectList(null);
            redisUtil.setCacheObject("getProductAll", getProductAll, 60, TimeUnit.SECONDS);
        }
        return getProductAll;
    }

    @Override
    public List<Product> getProductByProductCategoryId(String id) {
        List<Product> productList = redisUtil.getCacheList("getProductByProductCategoryId"+id, Product.class);
        if (productList == null) {
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("productCategoryId", id);
            productList = productMapper.selectList(queryWrapper);
            redisUtil.setCacheObject("getProductByProductCategoryId"+id, productList, 60, TimeUnit.SECONDS);
        }

        return productList;
    }

    @Override
    public List<Product> getProductIsEnabledEq2() {
        List<Product> getProductAll = redisUtil.getCacheList("getProductAll", Product.class);
        if (getProductAll == null) {
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("productIsEnabled", 2);
            getProductAll = productMapper.selectList(queryWrapper);
            redisUtil.setCacheObject("getProductAll", getProductAll, 60, TimeUnit.SECONDS);
        }
        return getProductAll;
    }
}
