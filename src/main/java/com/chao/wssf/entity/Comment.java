package com.chao.wssf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

@TableName("blog_comment")
@Data
@ToString
public class Comment implements Serializable {
    private static final long serialVersionUID = 591874363607206911L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer pid;
    @TableField(exist = false)
    private String parentName;
    private Integer articleId;
    private String userId;
    private String content;
    private String del;
    private Date createTime;

}