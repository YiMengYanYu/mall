package com.ly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.pojo.Property;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 18:27
 * @Description: TODO
 */
public interface PropertyMapper  extends BaseMapper<Property> {
   List<Property> getPropertyAndPropertyvalue(@Param("id") String id);
   List<Property> getPropertyAndPropertyvalue(@Param("id") String id,@Param("cid") String cid);

    Long  getProductCount();
}
