package com.chao.wssf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@TableName("blog_other")
@ToString
@Data
public class Other implements Serializable {
    private static final long serialVersionUID = 640544820981535122L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer articleId;
    private Integer flow;
    private Integer commentSize;
    private Integer like;

    /*
    冗余字段，目前没什么用，主要减少后期新增功能对代码和表的影响
     */
    @TableField(exist = false)
    private String o1;
    @TableField(exist = false)
    private String o2;
    @TableField(exist = false)
    private String o3;
}