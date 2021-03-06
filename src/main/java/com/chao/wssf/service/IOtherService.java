package com.chao.wssf.service;

import com.chao.wssf.entity.Other;

import java.util.List;

public interface IOtherService {


    List<Other> getOtherPaging();

    Other getOtherByArticleId(Integer id);

    void flowAdd(Integer id);

    void commentSizeAdd(Integer id);

    void flowMinus(Integer id);

    int getAllFlowSize();

    void deleteRealOtherByArticleId(Integer id);

    void updateCommentSizeByArticleId(Integer id, Integer size);
}
