package com.ly.service;

import com.ly.vo.ShopCar;

import java.util.List;

public interface CartService {

    boolean create(String userId, String productId, Long number);


    List<ShopCar> cart(Long userId);

    boolean delCart(Long id,Long userId);
}

