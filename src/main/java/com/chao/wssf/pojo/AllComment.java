package com.chao.wssf.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class AllComment {

    private Integer id;
    private String content;
    private Date createTime;

    private String title;
    private String userName;

}
