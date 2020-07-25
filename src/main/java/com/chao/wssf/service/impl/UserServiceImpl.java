package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Link;
import com.chao.wssf.entity.User;
import com.chao.wssf.mapper.UserMapper;
import com.chao.wssf.pojo.AllLink;
import com.chao.wssf.service.ICommentService;
import com.chao.wssf.service.ILeaveService;
import com.chao.wssf.service.ILinkService;
import com.chao.wssf.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ILinkService linkService;

    @Autowired
    private ILeaveService leaveService;

    @Autowired
    private ICommentService commentService;

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

    /**
     * 查询所有用户信息
     *
     * @param page
     * @param limit
     * @param username
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    @Override
    public Page getUsers(Integer page, Integer limit, String username, String startTime, String endTime) throws ParseException {
        QueryWrapper<User> linkQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(username)) {
            linkQueryWrapper.like("name", username);
        }
        //对日期进行转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(startTime)) {
            Date date = simpleDateFormat.parse(startTime);
            linkQueryWrapper.ge("create_time", date);
        }
        if (!StringUtils.isEmpty(endTime)) {
            Date date = simpleDateFormat.parse(endTime);
            linkQueryWrapper.le("create_time", date);
        }

        Page<User> linkPage = new Page<>(page, limit);
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


}
