package com.ly.pojo;


public class Category {

  private long categoryId;
  private String categoryName;
  private String categoryImageSrc;


  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }


  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }


  public String getCategoryImageSrc() {
    return categoryImageSrc;
  }

  public void setCategoryImageSrc(String categoryImageSrc) {
    this.categoryImageSrc = categoryImageSrc;
  }

}
