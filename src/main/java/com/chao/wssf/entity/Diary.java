package com.chao.wssf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

@TableName("oth_diary")
@Data
@ToString
public class Diary implements Serializable {
    private static final long serialVersionUID = 790018912219016338L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String content;
    private Date createTime;
    private Date updateTime;

}