package com.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.mapper.*;
import com.ly.pojo.*;
import com.ly.service.CartService;
import com.ly.utils.RedisUtil;
import com.ly.vo.OrderVo;
import com.ly.vo.ShopCar;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author YiMeng
 * @DateTime: 2022/8/11 12:00
 * @Description: TODO
 */
@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ProductimageMapper productimageMapper;

    @Resource
    private ProductorderMapper productorderMapper;

    @Resource
    private ProductorderitemMapper productorderitemMapper;

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
        return getShopCars(cacheHash);
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

    @Override
    public List<ShopCar> getCartAllByMap(Map<String, Long> map) {
        return getShopCars(map);
    }

    @Override
    public boolean orderList(OrderVo orderVo, Long userId) {
        Productorder productorder = new Productorder();
        final String yyyyMMddHHmmSSSS = new SimpleDateFormat("yyyyMMddHHmmSSSS").format(new Date());
        productorder.setProductorderCode(yyyyMMddHHmmSSSS);
        productorder.setProductorderAddress(orderVo.getDistrictAddressId());
        productorder.setProductorderDetailAddress(orderVo.getProductOrderDetailAddress());
        productorder.setProductorderPost(orderVo.getProductOrderPost());
        productorder.setProductorderMobile(orderVo.getProductOrderMobile());
        productorder.setProductorderReceiver(orderVo.getProductOrderReceiver());
        productorder.setProductorderPayDate(new Timestamp(System.currentTimeMillis()));
        productorder.setProductorderDeliveryDate(new Timestamp(System.currentTimeMillis()));
        productorder.setProductorderConfirmDate(new Timestamp(System.currentTimeMillis()));
        productorder.setProductorderUserId(userId);
        productorderMapper.insert(productorder);

        Map map = null;
        try {
            map = objectMapper.readValue(orderVo.getOrderItemJSON(), Map.class);
        } catch (JsonProcessingException e) {
            return false;
  
        }
        final List<ShopCar> shopCars = getShopCars(map);
        for (ShopCar shopCar : shopCars) {
            Productorderitem productorderitem = new Productorderitem();
            productorderitem.setProductorderitemUserMessage(null);
            productorderitem.setProductorderitemNumber(shopCar.getNumber());

            productorderitem.setProductorderitemOrderId(productorder.getProductorderId());//ojbk

            productorderitem.setProductorderitemPrice(shopCar.getProduct().getProductPrice());
            productorderitem.setProductorderitemUserId(userId);
            productorderitem.setProductorderitemProductId(shopCar.getProduct().getProductId());//ojbk
            productorderitemMapper.insert(productorderitem);
        }
        redisUtil.deleteObject(userId+"");

        return true;
    }

    @NotNull
    private List<ShopCar> getShopCars(Map<String, Long> map) {
        List<ShopCar> list = new ArrayList<>();
        for (Map.Entry<String, Long> mn : map.entrySet()) {
            ShopCar shopCar = new ShopCar();
            QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
            productQueryWrapper.eq("productId", mn.getKey());
            final Product product = productMapper.selectOne(productQueryWrapper);

            QueryWrapper<Productimage> productimageQueryWrapper = new QueryWrapper<>();
            productimageQueryWrapper.eq("productimageProductId", product.getProductId());
            productimageQueryWrapper.eq("productimageType", 0);

            product.setSingleProductImageList(productimageMapper.selectList(productimageQueryWrapper));
            shopCar.setProduct(product);
            QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
            categoryQueryWrapper.eq("categoryId", product.getProductCategoryId());
            shopCar.setCategory(categoryMapper.selectOne(categoryQueryWrapper));

            shopCar.setNumber(Long.parseLong(String.valueOf(mn.getValue())));
            list.add(shopCar);

        }

        return list;
    }


}
