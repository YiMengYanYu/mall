package com.ly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.pojo.Product;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 18:26
 * @Description: TODO
 */
public interface ProductMapper extends BaseMapper<Product> {


    Long getProductCount();
}
