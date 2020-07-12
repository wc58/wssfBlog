package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.entity.Comment;
import com.chao.wssf.mapper.CommentMapper;
import com.chao.wssf.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {


    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<Comment> getCommentsByArticleId(Integer id) {
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("article_id", id);
        List<Comment> comments = commentMapper.selectList(commentQueryWrapper);

        return comments;
    }
}
