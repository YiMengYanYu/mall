package com.ly.service;

import com.ly.pojo.Admin;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:06
 * @Description: TODO
 */
public interface AdminService {


    Admin getAdminByUserName(String userName);

    Boolean updateAdmin(Admin admin);
}
