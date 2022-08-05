package com.ly.pojo;


public class Product {

  private long productId;
  private String productName;
  private String productTitle;
  private double productPrice;
  private double productSalePrice;
  private java.sql.Timestamp productCreateDate;
  private long productCategoryId;
  private long productIsEnabled;


  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }


  public String getProductTitle() {
    return productTitle;
  }

  public void setProductTitle(String productTitle) {
    this.productTitle = productTitle;
  }


  public double getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(double productPrice) {
    this.productPrice = productPrice;
  }


  public double getProductSalePrice() {
    return productSalePrice;
  }

  public void setProductSalePrice(double productSalePrice) {
    this.productSalePrice = productSalePrice;
  }


  public java.sql.Timestamp getProductCreateDate() {
    return productCreateDate;
  }

  public void setProductCreateDate(java.sql.Timestamp productCreateDate) {
    this.productCreateDate = productCreateDate;
  }


  public long getProductCategoryId() {
    return productCategoryId;
  }

  public void setProductCategoryId(long productCategoryId) {
    this.productCategoryId = productCategoryId;
  }


  public long getProductIsEnabled() {
    return productIsEnabled;
  }

  public void setProductIsEnabled(long productIsEnabled) {
    this.productIsEnabled = productIsEnabled;
  }

}
