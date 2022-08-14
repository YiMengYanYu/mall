package com.ly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.pojo.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 18:26
 * @Description: TODO
 */
public interface AdminMapper extends BaseMapper<AdminMapper> {


    public Admin getAdminByUserName(@Param("userName") String userName);

}
