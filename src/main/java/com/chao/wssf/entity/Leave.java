package com.chao.wssf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

@TableName("oth_leave")
@Data
@ToString
public class Leave implements Serializable {
    private static final long serialVersionUID = 966247252234012883L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer pid;
    @TableField(exist = false)
    private String parentName;
    private Integer userId;
    private String content;
    private String site;
    private String browser;
    private String del;
    private Date createTime;

}