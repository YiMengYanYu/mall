package com.ly.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Property {
    @TableId
    private long propertyId;
    private String propertyName;
    private long propertyCategoryId;
    @TableField(exist = false)
    private Propertyvalue propertyValueList;


}
