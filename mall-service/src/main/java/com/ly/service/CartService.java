package com.ly.service;

import com.ly.vo.OrderVo;
import com.ly.vo.ShopCar;

import java.util.List;
import java.util.Map;

public interface CartService {

    boolean create(String userId, String productId, Long number);


    List<ShopCar> cart(Long userId);

    boolean delCart(Long id, Long userId);

    List<ShopCar> getCartAllByMap(Map<String, Long> map);

    boolean orderList(OrderVo orderVo,Long userId);
}

