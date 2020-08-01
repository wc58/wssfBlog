package com.chao.wssf.query;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LinkQuery extends CommonQuery {

    private String username;
    private String name;
}
