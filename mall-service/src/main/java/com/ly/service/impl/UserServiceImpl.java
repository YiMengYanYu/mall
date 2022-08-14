package com.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.mapper.AddressMapper;
import com.ly.mapper.UserMapper;
import com.ly.pojo.Address;
import com.ly.pojo.User;
import com.ly.service.UserService;
import com.ly.utils.CodecUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:16
 * @Description: TODO
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    AddressMapper addressMapper;

    @Override
    public boolean register(User user) {
        String salt = CodecUtil.generateSalt();
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("addressAreaId", user.getUserAddress());
        queryWrapper.last(" and  `addressAreaId` !=`addressRegionId`");
        String addressRegionId = addressMapper.selectOne(queryWrapper).getAddressRegionId();
        user.setUserHomePlace(addressRegionId);

        user.setSalt(salt);
        user.setUserPassword(CodecUtil.md5Hex(user.getUserPassword(), salt));
        if (ObjectUtils.isEmpty(getUserByUserName(user.getUserName()))) {
            int insert = userMapper.insert(user);
            if (insert > 0) {
                return true;
            }
            //User(userId=0, userName=603222569@qq.com, userNickName=小白, userPassword=123456a, userRealname=null, userGender=0, userBirthday=2022-09-11, userAddress=120101, userHomePlace=null, userProfilePictureSrc=null, salt=null)
        }

        return false;
    }

    @Override
    public User getUserByUserName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", userName);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public Boolean login(String username, String password) {
        User user = getUserByUserName(username);
        var encrypt = CodecUtil.md5Hex(password, user.getSalt());
        return encrypt.equals(user.getUserPassword());
    }

    @Override
    public boolean update(User user) {
        String salt = CodecUtil.generateSalt();
        user.setUserPassword(CodecUtil.md5Hex(user.getUserPassword(), salt));
        user.setSalt(salt);
        if (userMapper.updateById(user) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateImg(User user) {
        if (userMapper.updateById(user) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(Long userId) {

        return userMapper.selectById(userId);

    }

    @Override
    public Long getUserCount() {
        return userMapper.getUserCount();
    }
}
