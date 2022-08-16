package com.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.mapper.PropertyMapper;
import com.ly.pojo.Property;
import com.ly.service.PropertyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:15
 * @Description: TODO
 */
@Service
public class PropertyServiceImpl implements PropertyService {
    @Resource
    private PropertyMapper propertyMapper;

    @Override
    public List<Property> getPropertyAndPropertyvalue(String id) {
        return propertyMapper.getPropertyAndPropertyvalue(id);
    }

    @Override
    public List<Property> getProperty(String id) {
        QueryWrapper<Property> propertyQueryWrapper= new QueryWrapper<>();
        propertyQueryWrapper.eq("propertyCategoryId",id);
        return propertyMapper.selectList(propertyQueryWrapper);
    }
}
