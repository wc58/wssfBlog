package com.chao.wssf.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.User;

import java.text.ParseException;
import java.util.List;

public interface IUserService {

    User checkUser(String thirdId, String email);


    int getAllUserSize();

    User getUserById(Integer userId);

    List<User> getUserByUsername(String title);

    Page getUsers(Integer page, Integer limit, String username, String startTime, String endTime) throws ParseException;

    void updateUserById(Integer id, String name, String icon, String thirdId, String email, String banned);

    void deleteUserById(Integer id);
}
