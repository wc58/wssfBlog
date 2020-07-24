package com.chao.wssf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

@TableName("sys_link")
@ToString
@Data
public class Link implements Serializable {
    private static final long serialVersionUID = -16846219854071385L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer sort;
    private String title;
    private String icon;
    private String url;
    private String des;
    private String del;
    private Integer userId;
    private Date createTime;
    private Date updateTime;

}