package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.entity.Admin;
import com.chao.wssf.mapper.AdminMapper;
import com.chao.wssf.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {


    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin checkAdmin(String username, String password) {
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("username", username).eq("password", password).eq("del", "0");
        return adminMapper.selectOne(adminQueryWrapper);
    }

    /**
     * 获取所有各领域数
     *
     * @return
     */
    @Override
    public int getAllAdminSize() {
        return adminMapper.selectCount(new QueryWrapper<>());
    }
}
