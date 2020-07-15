package com.chao.wssf.pojo;

import com.chao.wssf.entity.Comment;
import com.chao.wssf.entity.Leave;
import com.chao.wssf.entity.User;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论响应的格式
 */
@Data
@ToString
public class FullComment {

    private Comment comment;
    private User user;

    private List<FullComment> fullComments = new ArrayList<>();

}
