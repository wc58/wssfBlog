package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.entity.Leave;
import com.chao.wssf.entity.User;
import com.chao.wssf.mapper.LeaveMapper;
import com.chao.wssf.mapper.UserMapper;
import com.chao.wssf.pojo.FullLeave;
import com.chao.wssf.service.ILeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LeaverServiceImpl implements ILeaveService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LeaveMapper leaveMapper;

    /**
     * 获取评论及其对应的用户信息
     *
     * @return
     */
    @Override
    public List<FullLeave> getAllFullComments() {

        QueryWrapper<Leave> leaveQueryWrapper = new QueryWrapper<>();
        leaveQueryWrapper.eq("pid", "0").eq("del", "0").orderByDesc("create_time");
        List<Leave> leaves = leaveMapper.selectList(leaveQueryWrapper);

        ArrayList<FullLeave> fullLeaves = new ArrayList<>();
        for (Leave leaf : leaves) {
            FullLeave fullLeave = new FullLeave();
            User user = userMapper.selectById(leaf.getUserId());
            fullLeave.setLeave(leaf);
            fullLeave.setUser(user);
            fullLeaves.add(fullLeave);
            //获取回复评论
            replyLeave(leaf.getId(), user.getName(), fullLeave.getFullLeaves());
        }

        return fullLeaves;
    }

    /**
     * 添加回复
     *
     * @param leave
     */
    @Override
    public void addReply(Leave leave) {
        leave.setCreateTime(new Date());
        leave.setDel("0");
        leaveMapper.insert(leave);
    }

    /**
     * 获取对应的回复
     *
     * @param pid
     * @param name
     * @param fullLeaves
     */
    private void replyLeave(Integer pid, String name, List<FullLeave> fullLeaves) {

        QueryWrapper<Leave> leaveQueryWrapper = new QueryWrapper<>();
        leaveQueryWrapper.eq("pid", pid).eq("del", 0).orderByAsc("create_time");
        List<Leave> leaves = leaveMapper.selectList(leaveQueryWrapper);
        if (leaves.size() <= 0) {
            //主要用于页面来分割评论
            //核心：“ERROR20020508”
            //在leave.jsp中会使用到
            FullLeave fullLeave = new FullLeave();
            User user = new User();
            user.setName("ERROR20020508");
            fullLeave.setUser(user);
            fullLeaves.add(fullLeave);
            return;
        }
        for (Leave leaf : leaves) {
            FullLeave fullLeave = new FullLeave();
            leaf.setParentName(name);
            User user = userMapper.selectById(leaf.getUserId());
            fullLeave.setUser(user);
            fullLeave.setLeave(leaf);
            fullLeaves.add(fullLeave);
            replyLeave(leaf.getId(), user.getName(), fullLeaves);
        }

    }

}
