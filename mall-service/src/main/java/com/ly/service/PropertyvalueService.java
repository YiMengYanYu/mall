package com.ly.service;

import com.ly.pojo.Propertyvalue;

public interface PropertyvalueService {
    Boolean insertPropertyvalue(Propertyvalue propertyvalue);
    Boolean updatePropertyvalue(Propertyvalue propertyvalue);

    /**
     * 验证是否存在 存在返回true
     */
    Propertyvalue isExist(Long id);
}
