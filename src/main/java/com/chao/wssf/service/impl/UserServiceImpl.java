package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.entity.User;
import com.chao.wssf.mapper.UserMapper;
import com.chao.wssf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //==========================================================================================================

    /**
     * 获取所有用户（包括被禁用的）
     *
     * @return
     */
    @Override
    public int getAllUserSize() {
        return userMapper.selectCount(new QueryWrapper<>());
    }

    /**
     * 根据id查找用户
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectById(userId);
    }

    /**
     * 根据名称查询用户
     *
     * @param username
     * @return
     */
    @Override
    public List<User> getUserByUsername(String username) {
        return userMapper.selectList(new QueryWrapper<User>().like("name", username));
    }
}
