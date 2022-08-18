package com.ly.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Productorderitem {
  @TableId
  private long productorderitemId;
  private long productorderitemNumber;
  private double productorderitemPrice;
  private long productorderitemProductId;
  private long productorderitemOrderId;
  private long productorderitemUserId;
  private String productorderitemUserMessage;

  @TableField(exist = false)
  private  Product productorderitemProduct;



}
