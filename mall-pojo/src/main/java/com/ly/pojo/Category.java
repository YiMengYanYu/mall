package com.ly.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class Category {

    private long categoryId;
    private String categoryName;
    private String categoryImageSrc;
    /**
     * 表示该属性不为数据库表字段，但又是必须使用的。
     */
    @TableField(exist = false)
    private List<Product> productList;


}
