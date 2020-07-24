package com.chao.wssf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

@TableName("blog_label")
@Data
@ToString
public class Label implements Serializable {
    private static final long serialVersionUID = 635225674537985746L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer sort;
    private Date createTime;
    @TableField(exist = false)
    private Integer articleSize;

}