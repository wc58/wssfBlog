package com.chao.wssf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@TableName("blog_article_label")
public class ArticleLabel implements Serializable {
    private static final long serialVersionUID = 220575327721035893L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer articleId;

    private Integer labelId;


}