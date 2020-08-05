package com.chao.wssf.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.User;
import com.chao.wssf.query.UserQuery;

import java.text.ParseException;
import java.util.List;

public interface IUserService {

    void addUser(User user);

    int getAllUserSize();

    User getUserById(Integer userId);

    List<User> getUserByUsername(String title);

    Page<User> getUsers(UserQuery userQuery);

    List<User> getUsers();

    void updateUserById(Integer id, String name, String icon, String thirdId, String email, String banned);

    void deleteUserById(Integer id);

    User getUserByThirdId(String openID);

    void updateUserByThirdId(User user);
}
