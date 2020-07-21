package com.chao.wssf.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class TopArticle {

    private Integer id;
    private String title;
    private String assistant;
    private String picture;
    private String content;
    private String author;
    private String status;
    private String del;
    private Date createTime;
    private Date updateTime;

    private Integer sort;

}
