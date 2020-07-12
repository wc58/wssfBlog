package com.chao.wssf.service;

import com.chao.wssf.entity.Comment;

import java.util.List;

public interface ICommentService {

    List<Comment> getCommentsByArticleId(Integer id);

}
