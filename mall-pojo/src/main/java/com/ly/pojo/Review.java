package com.ly.pojo;


public class Review {

  private long reviewId;
  private String reviewContent;
  private java.sql.Timestamp reviewCreatedate;
  private long reviewUserId;
  private long reviewProductId;
  private long reviewOrderItemId;


  public long getReviewId() {
    return reviewId;
  }

  public void setReviewId(long reviewId) {
    this.reviewId = reviewId;
  }


  public String getReviewContent() {
    return reviewContent;
  }

  public void setReviewContent(String reviewContent) {
    this.reviewContent = reviewContent;
  }


  public java.sql.Timestamp getReviewCreatedate() {
    return reviewCreatedate;
  }

  public void setReviewCreatedate(java.sql.Timestamp reviewCreatedate) {
    this.reviewCreatedate = reviewCreatedate;
  }


  public long getReviewUserId() {
    return reviewUserId;
  }

  public void setReviewUserId(long reviewUserId) {
    this.reviewUserId = reviewUserId;
  }


  public long getReviewProductId() {
    return reviewProductId;
  }

  public void setReviewProductId(long reviewProductId) {
    this.reviewProductId = reviewProductId;
  }


  public long getReviewOrderItemId() {
    return reviewOrderItemId;
  }

  public void setReviewOrderItemId(long reviewOrderItemId) {
    this.reviewOrderItemId = reviewOrderItemId;
  }

}
