package com.ly.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Address {

@TableId("addressAreaId")
  private String addressAreaId;
  private String addressName;
  private String addressRegionId;



}
