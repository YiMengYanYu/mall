package com.ly.service.impl;

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
            redisUtil.setCacheObject("getProductAll",getProductAll,60, TimeUnit.SECONDS);
        }
        return getProductAll;
    }
}
