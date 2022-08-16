package com.ly.service.impl;

import com.ly.mapper.ProductimageMapper;
import com.ly.service.ProductimageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:10
 * @Description: TODO
 */
@Service
public class ProductimageServiceImpl implements ProductimageService {
    @Resource
    private ProductimageMapper productimageMapper;

    @Override
    public Boolean delImgById(Integer id) {
        return productimageMapper.deleteById(id) > 0;
 
    }
}
