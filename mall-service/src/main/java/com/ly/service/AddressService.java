package com.ly.service;

import com.ly.pojo.Address;

import java.util.List;

/**
 * @author YiMeng
 * @Date 2022年8月5日 19:17:19
 */
public interface AddressService {

    List<Address> getAddressByaddressRegionId(String id);

    String getParentByaddressAreaId(String addressAreaId);
}
