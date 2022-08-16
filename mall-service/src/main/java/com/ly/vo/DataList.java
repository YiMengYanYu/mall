package com.ly.vo;

import lombok.Data;

import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/17 3:58
 * @Description: TODO
 */
@Data
public class DataList {

    Long productCategoryId;
    Integer productIsEnabled;
    String productName;
    String productTitle;
    Double productPrice;
    Double productSalePrice;
    String propertyAddJson;
    String propertyUpdateJson;
    List propertyDeleteList;
    List<String> productSingleImageList;
    List<String> productDetailsImageList;


}
