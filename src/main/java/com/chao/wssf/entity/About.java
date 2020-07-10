package com.chao.wssf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

@TableName("sys_about")
@Data
@ToString
public class About implements Serializable {
    private static final long serialVersionUID = 564837984704443976L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private String del;
    private Date createTime;
    private Date updateTime;

}