package com.ly.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Category {

    @TableId
    private long categoryId;
    private String categoryName;
    private String categoryImageSrc;

    @TableField(exist = false)
    private List<Product> productList;
    @TableField(exist = false)
    private List<Property> propertyList;


}
