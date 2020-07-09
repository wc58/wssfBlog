package com.chao.wssf.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.mapper.UserMapper;
import com.chao.wssf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("ceshi")
    @ResponseBody
    public List<User> ceshi() {
        System.out.println("aaaa");
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        return users;
    }

}
