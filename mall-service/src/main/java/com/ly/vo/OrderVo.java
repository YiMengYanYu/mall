package com.ly.vo;

import lombok.Data;

/**
 * @author YiMeng
 * @DateTime: 2022/8/13 15:21
 * @Description: TODO
 */
@Data
public class OrderVo {
    /**
     * 省id
     */
    Long addressId;

    /**
     * 市id
     */
    Long cityAddressId;

    /**
     * 区id
     */
    Long districtAddressId;

    /**
     * 产品订单详细地址
     */
    String productOrderDetailAddress;

    /**
     * 邮政编码
     */
    String productOrderPost;

    /**
     * 收货人姓名
     */
    String productOrderReceiver;

    /**
     * 手机号码
     */
    String productOrderMobile;

    /**
     * json
     */
    String orderItemJSON;


}
