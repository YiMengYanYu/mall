package com.ly.service;

import com.ly.pojo.Category;
import com.ly.utils.PageUtil;

import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:07
 * @Description: TODO
 */
public interface CategoryService {

    @Deprecated
    List<Category> getCategoryAll();

    List<Category> getCategory();

    List<Category> getCategoryAllImpl2();

    PageUtil<Category> getCategoryByName(String name, Integer startIndex, Integer endIndex);

    Long getCategoryCount();
}
