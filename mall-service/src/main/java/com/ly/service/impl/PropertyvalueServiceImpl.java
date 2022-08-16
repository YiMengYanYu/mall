package com.ly.service.impl;

import com.ly.mapper.PropertyvalueMapper;
import com.ly.pojo.Propertyvalue;
import com.ly.service.PropertyvalueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:16
 * @Description: TODO
 */
@Service
public class PropertyvalueServiceImpl implements PropertyvalueService {
    @Resource
    private PropertyvalueMapper propertyvalueMapper;

    @Override
    public Boolean insertPropertyvalue(Propertyvalue propertyvalue) {
        return propertyvalueMapper.insert(propertyvalue) > 0;
    }
}
