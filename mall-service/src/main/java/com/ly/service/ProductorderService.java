package com.ly.service;

import com.ly.pojo.Productorder;
import com.ly.utils.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductorderService {
    Long getProductorderCount();
    PageUtil<Productorder> getProductorder(List productorderStatusArray, String productorderPost, String productorderCode, String orderBy, Boolean isDesc,Integer starIndex,Integer pageSize);

}
