package com.ly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.pojo.Productorder;

/**
 * @author YiMeng
 * @DateTime: 2022/8/13 17:04
 * @Description: TODO
 */
public interface ProductorderMapper extends BaseMapper<Productorder> {

    Long getProductorderCount();
}
