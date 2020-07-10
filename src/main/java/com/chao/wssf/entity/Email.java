package com.chao.wssf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@TableName("sys_email")
@ToString
@Data
public class Email implements Serializable {
    private static final long serialVersionUID = 961328642463955369L;

    @TableId(type = IdType.AUTO)
    private Integer id;


}