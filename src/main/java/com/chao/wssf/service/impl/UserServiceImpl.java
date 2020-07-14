package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.entity.User;
import com.chao.wssf.mapper.UserMapper;
import com.chao.wssf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User checkUser(String thirdId, String email) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("third_id", thirdId).eq("email", email).eq("del", "0");
        return userMapper.selectOne(userQueryWrapper);
    }
}
