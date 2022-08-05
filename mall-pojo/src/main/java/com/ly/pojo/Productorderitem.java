package com.ly.pojo;


import lombok.Data;

@Data
public class Productorderitem {

  private long productorderitemId;
  private long productorderitemNumber;
  private double productorderitemPrice;
  private long productorderitemProductId;
  private long productorderitemOrderId;
  private long productorderitemUserId;
  private String productorderitemUserMessage;



}
