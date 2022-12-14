package com.ly.service;

import com.ly.pojo.Property;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PropertyService {

    List<Property> getPropertyAndPropertyvalue(String id,String cid);
    List<Property> getPropertyAndPropertyvalue(String id);
    List<Property> getProperty(String id);

    List<Property>getPropertBypropertyCategoryId( Long categoryId);
}
