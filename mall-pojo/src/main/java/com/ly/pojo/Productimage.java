package com.ly.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Productimage {

  @TableId
  private long productimageId;
  private long productimageType;
  private String productimageSrc;
  private long productimageProductId;




}
