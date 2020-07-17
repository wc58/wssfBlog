package com.chao.wssf.service;

import com.chao.wssf.entity.Admin;

public interface IAdminService {
    Admin checkAdmin(String username, String password);

    int getAllAdminSize();
}
