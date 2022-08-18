package com.ly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.pojo.Productorder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/13 17:04
 * @Description: TODO
 */
public interface ProductorderMapper extends BaseMapper<Productorder> {

    Long getProductorderCount();

    List<Productorder> getProductorder(@Param("productorderStatusArray") List productorderStatusArray, @Param("productorderPost") String productorderPost, @Param("productorderCode") String productorderCode, @Param("orderBy") String orderBy, @Param("isDesc") Boolean isDesc);


}
