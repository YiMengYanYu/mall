package com.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.mapper.PropertyvalueMapper;
import com.ly.pojo.Propertyvalue;
import com.ly.service.PropertyvalueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public Boolean updatePropertyvalue(Propertyvalue propertyvalue) {
        return propertyvalueMapper.updateById(propertyvalue) > 0;
    }

    @Override
    public Propertyvalue isExist(Long id) {
        QueryWrapper<Propertyvalue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("propertyValuePropertyId", id);

        Propertyvalue propertyvalues = propertyvalueMapper.selectOne(queryWrapper);

        return propertyvalues;
    }


}
