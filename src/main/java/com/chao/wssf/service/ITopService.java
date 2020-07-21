package com.chao.wssf.service;

import com.chao.wssf.entity.Top;

import java.util.List;

public interface ITopService {


    List<Top> getArticleIdByTops();

    int getAllTopSize();

    int getTopSize();

    Top getTopByArticleId(Integer id);

    void updateSortByArticleId(Integer clientId, Integer sort);

    void cancelTop(Integer id);

    void addTop(Integer articleId);
}
