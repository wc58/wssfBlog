package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.ArticleLabel;
import com.chao.wssf.entity.Label;
import com.chao.wssf.mapper.ArticleLabelMapper;
import com.chao.wssf.mapper.LabelMapper;
import com.chao.wssf.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Label> getAllLabel() {
        return labelMapper.selectList(new QueryWrapper<Label>().orderByDesc("sort"));
    }

    /**
     * 根据文章id查出所有的标签
     *
     * @param id
     * @return
     */
    @Override
    public List<String> getLabelNamesByArticleId(Integer id) {
        //首先查询出文章id所对的标签id有哪些
        List<Integer> labelIds = getLabelIdsByArticleId(id);

        //然后再查出所对应的名称
        if (labelIds.size() > 0) {
            QueryWrapper<Label> labelQueryWrapper = new QueryWrapper<>();
            labelQueryWrapper.select("name").in("id", labelIds);
            List<String> labelNames = labelMapper.selectList(labelQueryWrapper).stream().map(Label::getName).collect(Collectors.toList());
            return labelNames;
        }
        return new ArrayList<>();
    }

    /**
     * 根据分类id查询出对应的文章
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> getArticleIdsByLabelId(Integer id) {
        QueryWrapper<ArticleLabel> articleLabelQueryWrapper = new QueryWrapper<>();
        articleLabelQueryWrapper.select("article_id").eq("label_id", id);
        return articleLabelMapper.selectList(articleLabelQueryWrapper).stream().map(ArticleLabel::getArticleId).collect(Collectors.toList());
    }

    //==========================================================================================================

    /**
     * 获取所有标签数
     *
     * @return
     */
    @Override
    public int getAllLabelSize() {
        return labelMapper.selectCount(new QueryWrapper<>());
    }

    /**
     * 获取文章所对应的标签id
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> getLabelIdsByArticleId(Integer id) {
        QueryWrapper<ArticleLabel> articleLabelQueryWrapper = new QueryWrapper<>();
        articleLabelQueryWrapper.select("label_id").eq("article_id", id);
        return articleLabelMapper.selectList(articleLabelQueryWrapper).stream().map(ArticleLabel::getLabelId).collect(Collectors.toList());
    }

    /**
     * 删除文章对应的标签
     *
     * @param articleId
     */
    @Override
    public void deleteLabelsByArticleId(int articleId) {
        articleLabelMapper.delete(new QueryWrapper<ArticleLabel>().eq("article_id", articleId));
    }

    /**
     * 获取所有标签信息
     *
     * @param page
     * @param limit
     * @param labelName
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public Page<Label> getLabels(Integer page, Integer limit, String labelName, String startTime, String endTime) throws ParseException {

        QueryWrapper<Label> labelQueryWrapper = new QueryWrapper<>();
        Page<Label> labelPage = new Page<>(page, limit);
        labelQueryWrapper.orderByDesc("sort");
        if (!StringUtils.isEmpty(labelName)) {
            labelQueryWrapper.eq("name", labelName);
        }
        //对日期进行转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(startTime)) {
            Date date = simpleDateFormat.parse(startTime);
            labelQueryWrapper.ge("create_time", date);
        }
        if (!StringUtils.isEmpty(endTime)) {
            Date date = simpleDateFormat.parse(endTime);
            labelQueryWrapper.le("create_time", date);
        }
        Page<Label> selectPage = labelMapper.selectPage(labelPage, labelQueryWrapper);
        for (Label record : selectPage.getRecords()) {
            List<Integer> articleIds = getArticleIdsByLabelId(record.getId());
            record.setArticleSize(articleIds.size());
        }
        return selectPage;
    }

    /**
     * 根据id删除标签
     *
     * @param id
     */
    @Override
    public void deleteLabelsById(Integer id) {
        List<Integer> articleIds = getArticleIdsByLabelId(id);
        if (articleIds != null && articleIds.size() > 0) {
            throw new RuntimeException("该标签下还有文章，先不能删除！");
        } else {
            labelMapper.deleteById(id);
        }
    }

    /**
     * 更新标签内容
     *
     * @param id
     * @param name
     * @param sort
     */
    @Override
    public void updateLabel(Integer id, String name, Integer sort) {
        Label label = new Label();
        label.setId(id);
        label.setName(name);
        label.setSort(sort);
        labelMapper.updateById(label);
    }

    /**
     * 添加标签
     *
     * @param name
     */
    @Override
    public void addLabel(String name) {
        Label label = new Label();
        label.setSort(0);
        label.setName(name);
        label.setCreateTime(new Date());
        labelMapper.insert(label);
    }
}
