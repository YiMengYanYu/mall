package com.ly.service.impl;

import com.ly.mapper.ProductorderMapper;
import com.ly.mapper.PropertyMapper;
import com.ly.service.ProductorderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:15
 * @Description: TODO
 */
@Service
public class ProductorderServiceImpl implements ProductorderService {

    @Resource
    private ProductorderMapper productorderMapper;

    @Override
    public Long getProductorderCount() {
        return productorderMapper.getProductorderCount();
    }
}
