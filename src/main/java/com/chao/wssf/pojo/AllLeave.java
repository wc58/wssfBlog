package com.chao.wssf.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AllLeave {
    private Integer id;
    private String content;
    private String site;
    private Date createTime;

    private String userName;

}
