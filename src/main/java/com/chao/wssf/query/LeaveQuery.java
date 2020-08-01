package com.chao.wssf.query;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LeaveQuery extends CommonQuery {
    private String username;
    private String content;

}
