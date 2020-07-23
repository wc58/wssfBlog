package com.chao.wssf.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Label;

import java.util.List;

public interface ILabelService {

    List<Label> getAllLabel();

    List<String> getLabelNamesByArticleId(Integer id);

    List<Integer> getArticleIdsByLabelId(Integer id);

    int getAllLabelSize();

    List<Integer> getLabelIdsByArticleId(Integer id);

    void deleteLabelsByArticleId(int articleId);
}
