package com.chao.wssf.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("sys_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String icon;
    private String thirdId;
    private String email;
    private String banned;
    private Date createTime;
    private Date updateTime;

}
