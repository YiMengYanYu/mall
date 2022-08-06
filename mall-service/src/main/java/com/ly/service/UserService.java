package com.ly.service;

import com.ly.pojo.User;

public interface UserService {
    boolean register(User user);

    User getUserByUserName(String userName);

    Boolean login(String username, String password);


}
