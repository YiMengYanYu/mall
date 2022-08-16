package com.ly.service;

import com.ly.pojo.Product;
import com.ly.utils.PageUtil;

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

    Product getProductByProductId(String productId);

    List<Product> getLoveProductList();

    List<Product> getProductSoft(String startIndex, String endIndex, String orderBy, String productName, boolean isDesc, String categoryId);

    Long getProductCount();

    PageUtil<Product> getProduct(String productName, Integer categoryId, Double productSalePrice, Double productPrice, Integer[] productIsEnabledArray, String orderBy, Boolean isDesc, Integer startIndex, Integer pageSize);

    Boolean putProduct(Product product);
}
