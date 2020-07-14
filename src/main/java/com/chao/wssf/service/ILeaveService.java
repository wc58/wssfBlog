package com.chao.wssf.service;

import com.chao.wssf.entity.Leave;
import com.chao.wssf.pojo.FullLeave;

import java.util.List;

public interface ILeaveService {

    List<FullLeave> getAllFullComments();

    void addReply(Leave leave);
}
