package com.ly.service;

import com.ly.pojo.Property;
import com.ly.pojo.Propertyvalue;

import java.util.List;

public interface PropertyService {


    List<Property> getPropertyAndPropertyvalue(String id);

}
