package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Article;
import com.chao.wssf.entity.ArticleLabel;
import com.chao.wssf.entity.Other;
import com.chao.wssf.entity.Top;
import com.chao.wssf.mapper.ArticleLabelMapper;
import com.chao.wssf.mapper.ArticleMapper;
import com.chao.wssf.mapper.OtherMapper;
import com.chao.wssf.mapper.TopMapper;
import com.chao.wssf.service.ArticleCache;
import com.chao.wssf.service.IArticleService;
import com.chao.wssf.service.ILabelService;
import com.chao.wssf.service.IOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private IOtherService otherService;
    @Autowired
    private ArticleLabelMapper articleLabelMapper;
    @Autowired
    private TopMapper topMapper;
    @Autowired
    private OtherMapper otherMapper;
    @Autowired
    private ArticleCache articleCache;
    @Autowired
    private ILabelService labelService;
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 点击量排序查询
     *
     * @return
     */
    @Override
    public List<Article> hotArticle() {
        List<Integer> articleIds = otherService.getOtherPaging().stream().map(Other::getArticleId).collect(Collectors.toList());
        //查出对应的文章
        return articleCache.getArticleByIds(articleIds);
    }

    /**
     * 置顶文章
     *
     * @return
     */
    @Override
    public List<Article> topArticle() {
        //查出对应的文章
        return articleCache.getTops();
    }

    /**
     * 分页查询出文章信息（置顶，普通，分类）
     *
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> listArticle(Integer currentPage, Integer size, Boolean isSort, Boolean isCondition) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("tops", 0);
        //若是第一页且是普通查询（非条件），查出置顶文章
        if (currentPage.equals(1) && !isSort && !isCondition) {
            int topArticleTotal = articleCache.getTopArticleTotal();
            //若出现异常则可能一直在转圈圈！！！
            if (topArticleTotal > size)
                throw new RuntimeException("置顶文章不能大于首页最大显示数量");
            map.put("tops", topArticleTotal);
        }

        //分页文章
        map.put("data", articleCache.getAllOrSortArticle(currentPage, size, isSort, isCondition));
        //当前状态总页
        map.put("total", articleCache.getCurrentTotal());
        return map;
    }

    /**
     * 按标签进行分类
     *
     * @param id
     */
    @Override
    public void sortArticle(Integer id) {
        articleCache.sortArticle(labelService.getArticleIdsByLabelId(id));
    }

    /**
     * 条件查询
     *
     * @param condition
     */
    @Override
    public void conditionArticle(String condition) {
        articleCache.conditionArticle(condition);
    }

    //==========================================================================================================

    /**
     * 获取所有文章数（包括未展示）
     */
    @Override
    public int getAllArticleSize() {
        return articleMapper.selectCount(new QueryWrapper<>());
    }

    /**
     * 添加文章
     *
     * @param title
     * @param assistant
     * @param picture
     * @param content
     * @param status
     * @param del
     * @param top
     * @param labels
     */
    @Override
    @Transactional
    public int addArticle(String title, String assistant, String picture, String content, String author, String status, Boolean del, Boolean top, Integer[] labels) {
        //添加文章表
        int articleId = insertOrUpdateArticle(null, title, assistant, picture, content, author, status, del);
        //其他表
        Other other = new Other();
        other.setArticleId(articleId);
        other.setFlow(0);
        other.setCommentSize(0);
        otherMapper.insert(other);
        //设置标签
        setLabels(articleId, labels);
        //设置置顶状态
        setTop(articleId, top);
        articleCache.updateData();
        return articleId;
    }

    /**
     * 更新操作
     *
     * @param id
     * @param title
     * @param assistant
     * @param picture
     * @param content
     * @param author
     * @param status
     * @param del
     * @param top
     * @param labels
     * @return
     */
    @Override
    @Transactional
    public int updateArticle(Integer id, String title, String assistant, String picture, String content, String author, String status, Boolean del, Boolean top, Integer[] labels) {

        //更新文章表
        int articleId = insertOrUpdateArticle(id, title, assistant, picture, content, author, status, del);

        //删除旧的重新添加
        QueryWrapper<ArticleLabel> articleLabelQueryWrapper = new QueryWrapper<>();
        articleLabelQueryWrapper.eq("article_id", articleId);
        articleLabelMapper.delete(articleLabelQueryWrapper);
        //设置标签
        setLabels(articleId, labels);

        //置顶表
        QueryWrapper<Top> topQueryWrapper = new QueryWrapper<>();
        topQueryWrapper.eq("article_id", articleId);
        //设置置顶状态
        setTop(articleId, top);
        articleCache.updateData();
        return articleId;
    }


    /**
     * 更新或者添加文章
     *
     * @param id
     * @param title
     * @param assistant
     * @param picture
     * @param content
     * @param author
     * @param status
     * @param del
     * @return
     */
    private int insertOrUpdateArticle(Integer id, String title, String assistant, String picture, String content, String author, String status, Boolean del) {
        Article article = new Article();
        article.setTitle(title);
        article.setAssistant(assistant);
        article.setPicture(picture);
        article.setContent(content);
        article.setAuthor(author);
        article.setStatus(status);
        if (del) {
            article.setDel("0");
        } else {
            article.setDel("1");
        }
        article.setUpdateTime(new Date());
        if (id != null) {
            article.setId(id);
            articleMapper.updateById(article);
        } else {
            article.setCreateTime(new Date());
            articleMapper.insert(article);
        }
        return article.getId();
    }

    /**
     * 设置置顶状态
     *
     * @param articleId
     * @param top
     */
    private void setTop(Integer articleId, Boolean top) {
        Integer tops = topMapper.selectCount(new QueryWrapper<Top>().eq("del", "0"));
        QueryWrapper<Top> topQueryWrapper = new QueryWrapper<>();
        topQueryWrapper.eq("article_id", articleId);
        if (top && tops < 8) {
            QueryWrapper<Top> topQueryWrapper1 = new QueryWrapper<>();
            topQueryWrapper1.eq("article_id", articleId);
            Integer integer = topMapper.selectCount(topQueryWrapper1);
            Top t = new Top();
            t.setArticleId(articleId);
            //永久
            t.setEver("1");
            t.setDel("0");
            //首次最高
            t.setSort(0);
            t.setStartTime(new Date());
            t.setEndTime(new Date());
            if (integer != 0) {
                topMapper.update(t, topQueryWrapper);
            } else {
                topMapper.insert(t);
            }
        } else {
            Top t = new Top();
            t.setArticleId(articleId);
            t.setDel("1");
            t.setEver("0");
            t.setStartTime(new Date());
            t.setEndTime(new Date());
            topMapper.update(t, topQueryWrapper);
        }
    }

    /**
     * 为文章设置标签
     *
     * @param articleId
     * @param labels
     */
    private void setLabels(Integer articleId, Integer[] labels) {
        for (Integer labelId : labels) {
            ArticleLabel articleLabel = new ArticleLabel();
            articleLabel.setArticleId(articleId);
            articleLabel.setLabelId(labelId);
            articleLabelMapper.insert(articleLabel);
        }
    }


    /**
     * 既不是置顶也没有被删除
     *
     * @param tops
     * @return
     */
    @Override
    public Page<Article> getCommArticle(List<Integer> tops, Integer page, Integer limit) {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("del", "0").orderByDesc("update_time");
        Page<Article> articlePage = new Page<>(page, limit);
        Page<Article> selectPage = articleMapper.selectPage(articlePage, articleQueryWrapper);
        //把置顶的文章排除掉（mybatis plus查询语句没有错，但是查出来有问题，所有手动排除）
        selectPage.getRecords().removeIf(next -> tops.contains(next.getId()));
        //一定要注意，置顶列表中无论如何都不能存在 已经被删除 文章id
        selectPage.setTotal(selectPage.getTotal() - tops.size());
        return selectPage;
    }

    /**
     * 根据id查询文章
     *
     * @param id
     * @return
     */
    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.selectById(id);
    }

    /**
     * 主要用于在列表中更新使用
     *
     * @param article
     */
    @Override
    public void updateArticle(Article article) {
        articleMapper.updateById(article);
    }

    /**
     * 逻辑删除
     *
     * @param id
     */
    @Override
    public void deleteArticleById(Integer id) {
        Article article = new Article();
        article.setId(id);
        article.setDel("1");
        articleMapper.updateById(article);
    }

}
