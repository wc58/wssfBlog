package com.chao.wssf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

@TableName("blog_top")
@Data
@ToString
public class Top implements Serializable {
    private static final long serialVersionUID = -35056194715104008L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer articleId;
    private Integer sort;
    private String ever;
    private String del;
    private Date startTime;
    private Date endTime;

}