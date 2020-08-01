package com.chao.wssf.query;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ArticleQuery extends CommonQuery {
    private Integer clientId;
    private String title;
    private String assistant;
    private String picture;
    private String content;
    private String author;
    private String status;
    private Boolean del;
    private Boolean top;
    private Integer[] labels;
}
