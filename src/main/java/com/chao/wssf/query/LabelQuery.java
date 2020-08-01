package com.chao.wssf.query;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LabelQuery extends CommonQuery {
    String labelName;
}
