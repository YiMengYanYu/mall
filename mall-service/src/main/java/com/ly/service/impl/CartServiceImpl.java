package com.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.mapper.CategoryMapper;
import com.ly.mapper.ProductMapper;
import com.ly.mapper.ProductimageMapper;
import com.ly.pojo.Category;
import com.ly.pojo.Product;
import com.ly.pojo.Productimage;
import com.ly.service.CartService;
import com.ly.utils.RedisUtil;
import com.ly.vo.ShopCar;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author YiMeng
 * @DateTime: 2022/8/11 12:00
 * @Description: TODO
 */
@Service
public class CartServiceImpl implements CartService {


    @Resource
    private RedisUtil redisUtil;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ProductimageMapper productimageMapper;

    @Override
    public boolean create(String userId, String productId, Long number) {
        Object cacheMapValue = redisUtil.getCacheMapValue(userId, productId);
        if (!ObjectUtils.isEmpty(cacheMapValue)) {

            Long num = ((Long) cacheMapValue) + number;

            redisUtil.setCacheMapValue(userId, productId, num);
            return true;
        }
        redisUtil.setCacheMapValue(userId, productId, number);
        return true;

    }


    /**
     * 从redis中获取缓存的购物车信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<ShopCar> cart(Long userId) {
        Map<String, Long> cacheHash = redisUtil.getCacheHash(userId + "");

        List<ShopCar> list = new ArrayList<>();
        for (Map.Entry<String, Long> stringObjectEntry : cacheHash.entrySet()) {
            ShopCar shopCar = new ShopCar();
            QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
            productQueryWrapper.eq("productId", stringObjectEntry.getKey());
            final Product product = productMapper.selectOne(productQueryWrapper);

            QueryWrapper<Productimage> productimageQueryWrapper = new QueryWrapper<>();
            productimageQueryWrapper.eq("productimageProductId", product.getProductId());

            product.setSingleProductImageList(productimageMapper.selectList(productimageQueryWrapper));
            shopCar.setProduct(product);
            QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
            categoryQueryWrapper.eq("categoryId", product.getProductCategoryId());
            shopCar.setCategory(categoryMapper.selectOne(categoryQueryWrapper));
            shopCar.setNumber(stringObjectEntry.getValue());
            list.add(shopCar);
        }
        return list;
    }

    @Override
    public boolean delCart(Long id, Long userId) {
        try {
            redisUtil.delCacheMapValue(userId + "", id + "");
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
