package com.ly.pojo;

import lombok.Data;

/**
 * @author YiMeng
 * @DateTime: 2022/8/9 0:23
 * @Description: TODO
 */
@Data
public class Propertyvalue {
    private long propertyValueId;
    private String propertyValueValue;
    private long propertyValuePropertyId;
    private long propertyValueProductId;

}
