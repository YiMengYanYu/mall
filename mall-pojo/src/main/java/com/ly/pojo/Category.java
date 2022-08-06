package com.ly.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Category {

    private long categoryId;
    private String categoryName;
    private String categoryImageSrc;

    private List<Product> productList;


}
