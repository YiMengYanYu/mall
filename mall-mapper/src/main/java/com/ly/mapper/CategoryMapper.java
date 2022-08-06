package com.ly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.pojo.Category;

import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 18:26
 * @Description: TODO
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> getCategoryAll();
}
