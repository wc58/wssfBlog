package com.chao.wssf.service;

import com.chao.wssf.entity.User;

public interface IUserService {

    User checkUser(String thirdId, String email);


}
