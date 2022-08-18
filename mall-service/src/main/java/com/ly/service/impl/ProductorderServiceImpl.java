package com.ly.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mapper.ProductorderMapper;
import com.ly.pojo.Productorder;
import com.ly.service.ProductorderService;
import com.ly.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:15
 * @Description: TODO
 */
@Service
public class ProductorderServiceImpl implements ProductorderService {

    @Resource
    private ProductorderMapper productorderMapper;

    @Override
    public Long getProductorderCount() {
        return productorderMapper.getProductorderCount();
    }

    @Override
    public PageUtil<Productorder> getProductorder(List productorderStatusArray, String productorderPost, String productorderCode, String orderBy, Boolean isDesc, Integer starIndex, Integer pageSize) {
        PageUtil<Productorder> pageUtil = new PageUtil<>();
        PageHelper.startPage(starIndex + 1, pageSize);
        List<Productorder> productorder = productorderMapper.getProductorder(productorderStatusArray, productorderPost, productorderCode, orderBy, isDesc);
        PageInfo pageInfo = new PageInfo(productorder);
        pageUtil.setList(productorder);
        pageUtil.setIndex(starIndex);
        pageUtil.setTotalPage(pageInfo.getPages());
        pageUtil.setHasPrev(pageInfo.isHasPreviousPage());
        pageUtil.setHasNext(pageInfo.isHasNextPage());
        return pageUtil;
    }

    @Override
    public Productorder getProductorderById(Long id) {
        return productorderMapper.selectById(id);
    }


}
