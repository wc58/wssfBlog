package com.chao.wssf.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Label;
import com.chao.wssf.query.LabelQuery;

import java.text.ParseException;
import java.util.List;

public interface ILabelService {

    List<Label> getAllLabel();

    List<String> getLabelNamesByArticleId(Integer id);

    List<Integer> getArticleIdsByLabelId(Integer id);

    int getAllLabelSize();

    List<Integer> getLabelIdsByArticleId(Integer id);

    void deleteLabelsByArticleId(int articleId);

    Page<Label> getLabels(LabelQuery labelQuery) throws ParseException;

    void deleteLabelsById(Integer id);

    void updateLabel(Integer id, String name, Integer sort);

    void addLabel(String name);
}
