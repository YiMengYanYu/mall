package com.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mapper.CategoryMapper;
import com.ly.mapper.ProductMapper;
import com.ly.mapper.ProductimageMapper;
import com.ly.pojo.Category;
import com.ly.pojo.Product;
import com.ly.pojo.Productimage;
import com.ly.service.CategoryService;
import com.ly.utils.PageUtil;
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

    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductimageMapper productimageMapper;


    @Override
    public List<Category> getCategoryAll() {
        List<Category> categories = redisUtil.getCacheList("categoryListAll", Category.class);
        if (categories == null) {
            categories = categoryMapper.getCategoryAll();
            redisUtil.setCacheObject("categoryListAll", categories, 60, TimeUnit.SECONDS);
        }
        return categories;
    }

    @Override
    public List<Category> getCategory() {

        return categoryMapper.selectList(null);
    }

    /**
     * 使用mybatis-plus实现
     *
     * @return List<Category>
     */
    @Override
    public List<Category> getCategoryAllImpl2() {
        List<Category> categories = redisUtil.getCacheList("getCategoryAllImpl2", Category.class);
        if (categories == null) {
            categories = categoryMapper.selectList(null);
            for (Category category : categories) {
                QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("productCategoryId", category.getCategoryId());
                queryWrapper.ne("productIsEnabled", 1);
                List<Product> products = productMapper.selectList(queryWrapper);
                for (Product product : products) {
                    QueryWrapper<Productimage> qw = new QueryWrapper<>();
                    qw.eq("productimageType", 0);
                    qw.eq("productimageProductId", product.getProductId());
                    List<Productimage> productimages = productimageMapper.selectList(qw);
                    product.setSingleProductImageList(productimages);
                }
                category.setProductList(products);
            }
            redisUtil.setCacheObject("getCategoryAllImpl2", categories, 60, TimeUnit.SECONDS);
        }

        return categories;
    }

    @Override
    public PageUtil<Category> getCategoryByName(String name, Integer startIndex, Integer endIndex) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        if (name != null) {
            queryWrapper.like("categoryName", name);
        } else {
            queryWrapper.like("categoryName", "");
        }
        PageHelper.startPage(startIndex + 1, endIndex);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        PageInfo pageInfo = new PageInfo(categories);
        PageUtil<Category> pageUtil = new PageUtil<>();

        pageUtil.setList(categories);
        pageUtil.setIndex(startIndex);
        pageUtil.setTotalPage(pageInfo.getPages());
        pageUtil.setHasPrev(pageInfo.isHasPreviousPage());
        pageUtil.setHasNext(pageInfo.isHasNextPage());
        return pageUtil;

    }

    @Override
    public Long getCategoryCount() {
        return categoryMapper.selectCount(null);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public Boolean updateCategory(Category category) {
        return categoryMapper.updateById(category) > 0;
    }

    @Override
    public Boolean insertCategory(Category category) {
        return categoryMapper.insert(category) > 0;
    }


}
