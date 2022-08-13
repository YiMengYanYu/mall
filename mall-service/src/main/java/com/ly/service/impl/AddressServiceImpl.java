package com.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.mapper.AddressMapper;
import com.ly.pojo.Address;
import com.ly.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:09
 * @Description: TODO
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public List<Address> getAddressByaddressRegionId(String id) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();

        List<Address> addresses;
        if (ObjectUtils.isEmpty(id)) {
            queryWrapper.last("where `addressAreaId`=`addressRegionId` ");
        } else {
            queryWrapper.eq("addressRegionId", id);
            queryWrapper.last(" and  `addressAreaId` !=`addressRegionId`");
        }

        addresses = addressMapper.selectList(queryWrapper);

        return addresses;
    }

    @Override
    public String getParentByaddressAreaId(String addressAreaId) {
        Address address = addressMapper.selectById(addressAreaId);
        String addressRegionId = address.getAddressRegionId();
        return addressRegionId;
    }
}
