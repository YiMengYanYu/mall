package com.ly.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Product {
    @TableId
    private long productId;
    private String productName;
    private String productTitle;
    private double productPrice;
    private double productSalePrice;
    private java.sql.Timestamp productCreateDate;
    private long productCategoryId;
    private long productIsEnabled;
    /**
     * 表示该属性不为数据库表字段，但又是必须使用的。
     */
    @TableField(exist = false)
    private List<Productimage> singleProductImageList;
    /**
     * 表示该属性不为数据库表字段，但又是必须使用的。
     */
    @TableField(exist = false)
    private List<Productimage> detailProductImageList;


    /**
     * 表示该属性不为数据库表字段，但又是必须使用的。
     */
    @TableField(exist = false)
    private Productimage productimage;
    /**
     * 表示该属性不为数据库表字段，但又是必须使用的。
     */
    @TableField(exist = false)
    private Category productCategory;
    /**
     * 表示该属性不为数据库表字段，但又是必须使用的。
     * 产品销售数量
     */
    @TableField(exist = false)
    private Long productSaleCount;
    /**
     * 表示该属性不为数据库表字段，但又是必须使用的。
     * 评价数量
     */
    @TableField(exist = false)
    private Long productReviewCount;
    /**
     * 表示该属性不为数据库表字段，但又是必须使用的。
     */
    @TableField(exist = false)
    private List<Review> reviewList;
   
}
