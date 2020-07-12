package com.chao.wssf.service;

import com.chao.wssf.entity.Label;

import java.util.List;

public interface ILabelService {

    List<Label> allLabel();

    List<String> getLabelsByArticleId(Integer id);

}
