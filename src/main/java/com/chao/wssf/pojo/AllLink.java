package com.chao.wssf.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class AllLink {

    private Integer id;
    private Integer sort;
    private String title;
    private String icon;
    private String url;
    private String des;
    private String del;
    private String userName;
    private Date createTime;
    private Date updateTime;

}
