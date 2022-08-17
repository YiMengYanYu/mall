package com.ly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 18:27
 * @Description: TODO
 */
public interface UserMapper extends BaseMapper<User> {

    Long getUserCount();

    List<User> getUser(@Param("list") List user_gender_array, @Param("userName") String userName,@Param("isDesc")Boolean isDesc,@Param("orderBy") String orderBy);
}
