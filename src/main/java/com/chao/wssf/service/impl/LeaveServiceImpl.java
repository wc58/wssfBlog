package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.*;
import com.chao.wssf.mapper.LeaveMapper;
import com.chao.wssf.mapper.UserMapper;
import com.chao.wssf.pojo.AllComment;
import com.chao.wssf.pojo.AllLeave;
import com.chao.wssf.pojo.FullLeave;
import com.chao.wssf.service.ILeaveService;
import com.chao.wssf.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements ILeaveService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LeaveMapper leaveMapper;

    @Autowired
    private IUserService userService;

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

    //==========================================================================================================

    @Override
    public int getAllLeaveSize() {
        return leaveMapper.selectCount(new QueryWrapper<>());
    }

    /**
     * 查询吐槽评论
     *
     * @param isDel
     * @param page
     * @param limit
     * @param username
     * @param content
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public Page getComments(Boolean isDel, Integer page, Integer limit, String username, String content, String startTime, String endTime) throws ParseException {


        QueryWrapper<Leave> commentQueryWrapper = new QueryWrapper<>();

        if (isDel) {//被删除的
            commentQueryWrapper.ne("del", "0");
        } else {
            commentQueryWrapper.eq("del", "0");
        }
        commentQueryWrapper.orderByDesc("create_time");

        //条件判断
        if (username != null && !username.equals("")) {
            List<Integer> ids = userService.getUserByUsername(username).stream().map(User::getId).collect(Collectors.toList());
            ids.add(-1);
            commentQueryWrapper.in("user_id", ids);
        }
        if (content != null && !content.equals("")) {
            commentQueryWrapper.like("content", content);
        }

        //对日期进行转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(startTime)) {
            Date date = simpleDateFormat.parse(startTime);
            commentQueryWrapper.ge("create_time", date);
        }
        if (!StringUtils.isEmpty(endTime)) {
            Date date = simpleDateFormat.parse(endTime);
            commentQueryWrapper.le("create_time", date);
        }

        Page<Leave> leavePage = new Page<>(page, limit);
        Page selectPage = leaveMapper.selectPage(leavePage, commentQueryWrapper);

        List records = selectPage.getRecords();
        ArrayList<AllLeave> allLeaves = new ArrayList<>();
        for (Object obj : records) {
            Leave leave = (Leave) obj;
            AllLeave allLeave = new AllLeave();
            Integer userId = leave.getUserId();
            //根据评论信息查询对应的用户和文章
            String name = userService.getUserById(userId).getName();
            allLeave.setUserName(name);
            BeanUtils.copyProperties(leave, allLeave);
            allLeaves.add(allLeave);
        }
        selectPage.setRecords(allLeaves);

        return selectPage;
    }

    //是否成功还原
    public Boolean isRestore = true;
    public String pName = "";

    /**
     * 逻辑删除评论
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        deleteRecursion(id, 1);
    }

    /**
     * 真实删除评论
     *
     * @param id
     */
    @Override
    public void deleteRealById(Integer id) {
        deleteRecursion(id, 0);
    }


    /**
     * 还原数据
     *
     * @param id
     */
    @Override
    public void restoreCommentById(Integer id) {
        deleteRecursion(id, 2);
    }

    /**
     * 递归的删除子评论
     *
     * @param pid
     */
    private void deleteRecursion(Integer pid, Integer type) {
        QueryWrapper<Leave> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("pid", pid);
        List<Leave> leaves = leaveMapper.selectList(commentQueryWrapper);
        Leave lea = new Leave();
        lea.setId(pid);
        //真实删除
        if (type == 0) {
            leaveMapper.deleteById(pid);
        } else if (type == 1) {
            //说明子评论已经被删除
            if (getLeaveById(pid).getDel().equals("1")) {
                return;
            }
            //逻辑
            lea.setDel("1");
            leaveMapper.updateById(lea);
        } else if (type == 2) {
            //获取当前评论的父评论，判断是否被删除，如果被删除则不能还原子评论
            Integer pidDel = getLeaveById(pid).getPid();
            Leave commentDel = getLeaveById(pidDel);
            if (!pidDel.equals(0) && commentDel.getDel().equals("1")) {
                //表示不能被还原
                isRestore = false;
                //该评论父评论的内容
                pName = commentDel.getContent();
                return;
            }
            //还原
            lea.setDel("0");
            leaveMapper.updateById(lea);
        }
        //递归的条件出口
        if (leaves.size() <= 0) {
            return;
        }
        for (Leave leave : leaves) {
            deleteRecursion(leave.getId(), type);
        }
    }

    private Leave getLeaveById(Integer pid) {
        return leaveMapper.selectById(pid);
    }

    /**
     * 修改吐槽内容
     *
     * @param id
     * @param content
     */
    @Override
    public void updateLeave(Integer id, String content) {
        Leave leave = new Leave();
        leave.setId(id);
        leave.setContent(content);
        leaveMapper.updateById(leave);
    }


}
