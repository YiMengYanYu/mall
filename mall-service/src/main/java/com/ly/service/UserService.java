package com.ly.service;

import com.ly.pojo.User;

public interface UserService {
    boolean register(User user);

    User getUserByUserName(String userName);

    Boolean login(String username, String password);
    boolean update(User user);
    boolean updateImg(User user);

    User getUserById(Long userId);

    Long getUserCount();
}
