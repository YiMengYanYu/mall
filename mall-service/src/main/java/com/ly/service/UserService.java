package com.ly.service;

import com.ly.pojo.User;
import com.ly.utils.PageUtil;

import java.util.List;

public interface UserService {
    boolean register(User user);

    User getUserByUserName(String userName);

    Boolean login(String username, String password);

    boolean update(User user);

    boolean updateImg(User user);

    User getUserById(Long userId);

    Long getUserCount();

    PageUtil<User> getUserPage(Integer startIndex, Integer endIndex, String userName, List user_gender_array,Boolean isDesc, String orderBy);
}
