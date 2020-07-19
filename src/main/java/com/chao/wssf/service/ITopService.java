package com.chao.wssf.service;

import com.chao.wssf.entity.Top;

import java.util.List;

public interface ITopService {


    List<Top> getArticleIdByTops();

    int getAllTopSize();

    int getTopSize();
}
