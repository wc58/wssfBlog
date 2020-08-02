package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.User;
import com.chao.wssf.mapper.UserMapper;
import com.chao.wssf.query.UserQuery;
import com.chao.wssf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String thirdId, String email) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("third_id", thirdId).eq("email", email);
        return userMapper.selectOne(userQueryWrapper);
    }

    public void addUser(User user) {
        Date date = new Date();
        user.setBanned("0");
        user.setUpdateTime(date);
        user.setCreateTime(date);
        userMapper.insert(user);
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

    /**
     * 查询所有用户信息
     */
    @Override
    public Page getUsers(UserQuery userQuery) {
        QueryWrapper<User> linkQueryWrapper = new QueryWrapper<>();
        String username = userQuery.getUsername();
        Date startTime = userQuery.getStartTime();
        Date endTime = userQuery.getEndTime();
        if (!StringUtils.isEmpty(username)) {
            linkQueryWrapper.like("name", username);
        }
        if (!StringUtils.isEmpty(startTime)) {
            linkQueryWrapper.ge("create_time", startTime);
        }
        if (!StringUtils.isEmpty(endTime)) {
            linkQueryWrapper.le("create_time", endTime);
        }

        Page<User> linkPage = new Page<>(userQuery.getPage(), userQuery.getLimit());
        return userMapper.selectPage(linkPage, linkQueryWrapper);
    }

    @Override
    public void updateUserById(Integer id, String name, String icon, String thirdId, String email, String banned) {
        User user = new User();
        user.setId(id);
        user.setIcon(icon);
        user.setThirdId(thirdId);
        user.setEmail(email);
        user.setBanned(banned);
        userMapper.updateById(user);
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteById(id);
    }

    /**
     * 根据第三方id进行查询
     *
     * @param openID
     * @return
     */
    @Override
    public User getUserByThirdId(String openID) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("third_id", openID));
    }


}
