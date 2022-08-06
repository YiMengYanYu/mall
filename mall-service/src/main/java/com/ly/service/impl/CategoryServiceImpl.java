package com.ly.service.impl;

import com.ly.mapper.CategoryMapper;
import com.ly.pojo.Category;
import com.ly.pojo.Product;
import com.ly.service.CategoryService;
import com.ly.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:09
 * @Description: TODO
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategoryAll() {
        List<Category> categories = redisUtil.getCacheList("categoryListAll", Category.class);
        if (categories == null) {
            categories = categoryMapper.getCategoryAll();
            redisUtil.setCacheObject("categoryListAll",categories,60, TimeUnit.SECONDS);
        }

        return categories;
    }
}
