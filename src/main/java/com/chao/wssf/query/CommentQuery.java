package com.chao.wssf.query;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentQuery extends CommonQuery {
    private String title;
    private String username;
    private String content;
    private String contentSize;
}
