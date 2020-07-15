package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.entity.Comment;
import com.chao.wssf.entity.User;
import com.chao.wssf.mapper.CommentMapper;
import com.chao.wssf.mapper.UserMapper;
import com.chao.wssf.pojo.FullComment;
import com.chao.wssf.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {


    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;




    @Override
    public List<FullComment> getCommentsByArticleId(Integer id) {
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("article_id", id).eq("del", "0").eq("pid", "0").orderByDesc("create_time");
        List<Comment> comments = commentMapper.selectList(commentQueryWrapper);
        ArrayList<FullComment> fullComments = new ArrayList<>();
        for (Comment comment : comments) {
            FullComment fullComment = new FullComment();
            fullComment.setComment(comment);
            User user = userMapper.selectById(comment.getUserId());
            fullComment.setUser(user);
            fullComments.add(fullComment);
            reply(comment.getId(), user.getName(), fullComment.getFullComments());
        }
        return fullComments;
    }



    @Override
    public void addReply(Comment comment) {
        comment.setCreateTime(new Date());
        comment.setDel("0");
        commentMapper.insert(comment);
    }


    private void reply(Integer pid, String parentName, List<FullComment> fullComments) {

        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("pid", pid).eq("del", "0").orderByAsc("create_time");
        List<Comment> comments = commentMapper.selectList(commentQueryWrapper);
        //递归的条件出口
        if (comments.size() <= 0) {
            //主要用于页面来分割评论
            //核心：“ERROR20020508”
            //在leave.jsp中会使用到
            FullComment fullComment = new FullComment();
            User user = new User();
            user.setName("ERROR20020508");
            fullComment.setUser(user);
            fullComments.add(fullComment);
            return;
        }
        for (Comment comment : comments) {
            FullComment fullComment = new FullComment();
            comment.setParentName(parentName);
            fullComment.setComment(comment);
            User user = userMapper.selectById(comment.getUserId());
            fullComment.setUser(user);
            fullComments.add(fullComment);
            reply(comment.getId(), user.getName(), fullComments);
        }

    }

    /**
     * 获取所有评论数
     *
     * @return
     */
    @Override
    public int getAllCommentSize() {
        return commentMapper.selectCount(new QueryWrapper<>());
    }




}
