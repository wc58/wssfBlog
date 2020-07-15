package com.chao.wssf.pojo;

import com.chao.wssf.entity.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 留言响应的格式
 */
@Data
@ToString
public class FullLeave {

    private Leave leave;
    private User user;

    private List<FullLeave> fullLeaves = new ArrayList<>();

}
