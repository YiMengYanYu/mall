package com.ly.vo;

import com.ly.pojo.Category;
import com.ly.pojo.Product;
import lombok.Data;

/**
 * @author YiMeng
 * @DateTime: 2022/8/11 12:02
 * @Description: TODO
 */

@Data
public class ShopCar {


    private Product product;
    private Category category;
    private Long number;

}
