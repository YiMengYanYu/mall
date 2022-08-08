package com.ly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.pojo.Productorderitem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author YiMeng
 * @DateTime: 2022/8/8 15:46
 * @Description: TODO
 */
public interface ProductorderitemMapper extends BaseMapper<Productorderitem> {
    @Select(" SELECT(SUM(`productorderitemNumber`))FROM`productorderitem`WHERE`productorderitemProductId`=#{id}")
    Long getProductorderitemSumBypProductorderitemProductId(@Param("id") Long id);
}
