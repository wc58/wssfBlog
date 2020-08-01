package com.chao.wssf.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Leave;
import com.chao.wssf.pojo.FullLeave;
import com.chao.wssf.query.LeaveQuery;

import java.text.ParseException;
import java.util.List;

public interface ILeaveService {

    List<FullLeave> getAllFullComments();

    void addReply(Leave leave);

    int getAllLeaveSize();

    Page getComments(Boolean isDel, LeaveQuery leaveQuery);

    void deleteById(Integer id);

    void restoreCommentById(Integer id);

    void deleteRealById(Integer id);

    void updateLeave(Integer id, String content);

}
