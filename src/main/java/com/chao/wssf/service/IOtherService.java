package com.chao.wssf.service;

import com.chao.wssf.entity.Other;

import java.util.List;

public interface IOtherService {


    List<Other> getOtherPaging();

    Other getOtherByArticleId(Integer id);

}
