package com.ly.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Review {

  private long reviewId;
  private String reviewContent;
  private java.sql.Timestamp reviewCreatedate;
  private long reviewUserId;
  private long reviewProductId;
  private long reviewOrderItemId;


  @TableField(exist = false)
  private  User reviewUser;

}
