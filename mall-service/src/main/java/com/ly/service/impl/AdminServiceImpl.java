package com.ly.service.impl;

import com.ly.mapper.AdminMapper;
import com.ly.pojo.Admin;
import com.ly.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:09
 * @Description: TODO
 */
@Service
public class AdminServiceImpl implements AdminService {


    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin getAdminByUserName(String userName) {

        return adminMapper.getAdminByUserName(userName);

    }

    @Override
    public Boolean updateAdmin(Admin admin) {
        return adminMapper.updateById(admin)>0;
    }
}
