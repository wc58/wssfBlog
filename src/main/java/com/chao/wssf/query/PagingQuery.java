package com.chao.wssf.query;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PagingQuery {

    private Integer page;
    private Integer limit;

}
