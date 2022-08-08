package com.ly.service;

import com.ly.pojo.Product;

import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:07
 * @Description: TODO
 */
public interface ProductService {
    List<Product> getProductAll();
    List<Product> getProductByProductCategoryId(String id);
    List<Product> getProductIsEnabledEq2();

    List<Product> getProductByProductName(String productName);

}
