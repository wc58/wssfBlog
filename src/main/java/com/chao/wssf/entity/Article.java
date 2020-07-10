package com.chao.wssf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@TableName("blog_article")
@ToString
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = 541311388623014153L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String assistant;
    private String picture;
    private String content;
    private String author;
    private String status;
    private String url;
    private String del;
    private Date createTime;
    private Date updateTime;

}