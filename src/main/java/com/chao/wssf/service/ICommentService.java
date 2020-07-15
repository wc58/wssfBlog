package com.chao.wssf.service;

import com.chao.wssf.entity.Comment;
import com.chao.wssf.pojo.FullComment;

import java.util.List;

public interface ICommentService {

    List<FullComment> getCommentsByArticleId(Integer id);

    void addReply(Comment comment);

}
