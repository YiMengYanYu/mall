package com.ly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.pojo.User;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 18:27
 * @Description: TODO
 */
public interface UserMapper extends BaseMapper<User> {

    Long getUserCount();
}
