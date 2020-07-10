package com.chao.wssf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

@TableName("sys_admin")
@ToString
@Data
public class Admin implements Serializable {
    private static final long serialVersionUID = -85217260013643557L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String del;
    private Date createTime;
    private Date updateTime;

}