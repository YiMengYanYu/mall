package com.ly.pojo;

import lombok.Data;

@Data
public class Product {

  private long productId;
  private String productName;
  private String productTitle;
  private double productPrice;
  private double productSalePrice;
  private java.sql.Timestamp productCreateDate;
  private long productCategoryId;
  private long productIsEnabled;



}
