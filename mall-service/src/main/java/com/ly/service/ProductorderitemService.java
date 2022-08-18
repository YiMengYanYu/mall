package com.ly.service;

import com.ly.pojo.Productorderitem;

import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:08
 * @Description: TODO
 */
public interface ProductorderitemService {

    List<Productorderitem> getProductorderitemByOrderId(Long productorderitemOrderId);
}
