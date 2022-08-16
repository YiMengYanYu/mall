package com.ly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 18:26
 * @Description: TODO
 */
public interface ProductMapper extends BaseMapper<Product> {


    Long getProductCount();

    List<Product> getProduct(@Param("productName") String productName,@Param("categoryId") Integer categoryId, @Param("productSalePrice") Double productSalePrice, @Param("productPrice") Double productPrice,@Param("productIsEnabledArray") Integer[] productIsEnabledArray,@Param("orderBy") String orderBy,@Param("isDesc") Boolean isDesc);


}
