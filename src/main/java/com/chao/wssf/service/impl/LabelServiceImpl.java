package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.entity.ArticleLabel;
import com.chao.wssf.entity.Label;
import com.chao.wssf.mapper.ArticleLabelMapper;
import com.chao.wssf.mapper.LabelMapper;
import com.chao.wssf.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LabelServiceImpl implements ILabelService {

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private ArticleLabelMapper articleLabelMapper;


    /**
     * 查询所有的标签信息
     *
     * @return
     */
    @Override
    public List<Label> allLabel() {
        QueryWrapper<Label> labelQueryWrapper = new QueryWrapper<>();
        labelQueryWrapper.eq("del", 0).orderByAsc("sort");
        return labelMapper.selectList(labelQueryWrapper);
    }

    /**
     * 根据文章id查出所有的标签
     *
     * @param id
     * @return
     */
    @Override
    public List<String> getLabelsByArticleId(Integer id) {
        //首先查询出文章id所对的标签id有哪些
        QueryWrapper<ArticleLabel> articleLabelQueryWrapper = new QueryWrapper<>();
        articleLabelQueryWrapper.select("label_id").eq("article_id", id);
        List<Integer> labelIds = articleLabelMapper.selectList(articleLabelQueryWrapper).stream().map(ArticleLabel::getLabelId).collect(Collectors.toList());

        //然后再查出所对应的名称
        if (labelIds.size() > 0) {
            QueryWrapper<Label> labelQueryWrapper = new QueryWrapper<>();
            labelQueryWrapper.select("name").in("id", labelIds);
            List<String> labelNames = labelMapper.selectList(labelQueryWrapper).stream().map(Label::getName).collect(Collectors.toList());
            return labelNames;
        }
        return new ArrayList<>();
    }
}
