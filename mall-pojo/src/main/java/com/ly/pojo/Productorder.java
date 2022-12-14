package com.ly.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Productorder {

  @TableId
  private long productorderId;
  private String productorderCode;
  private Long productorderAddress;
  private String productorderDetailAddress;
  private String productorderPost;
  private String productorderReceiver;
  private String productorderMobile;
  private java.sql.Timestamp productorderPayDate;
  private java.sql.Timestamp productorderDeliveryDate;
  private java.sql.Timestamp productorderConfirmDate;
  private long productorderStatus;
  private long productorderUserId;
  @TableField(exist = false)
  private User productorderUser;
  @TableField(exist = false)
  private List<Productorderitem> productorderitemList;

}
