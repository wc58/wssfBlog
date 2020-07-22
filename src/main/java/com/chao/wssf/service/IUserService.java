package com.chao.wssf.service;

import com.chao.wssf.entity.User;

import java.util.List;

public interface IUserService {

    User checkUser(String thirdId, String email);


    int getAllUserSize();

    User getUserById(Integer userId);

    List<User> getUserByUsername(String title);
}
