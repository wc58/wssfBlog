package com.chao.wssf.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Article;
import com.chao.wssf.entity.Top;
import com.chao.wssf.query.ArticleQuery;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IArticleService {

    List<Article> getHotArticle();

    List<Article> getTopArticle();

    Map<String, Object> listArticle(Integer currentPage, Integer size, Boolean isSort, Boolean isCondition);

    void sortArticle(Integer id);

    void conditionArticle(String condition);

    int getAllArticleSize();

    Page<Article> getCommArticle(List<Integer> tops, ArticleQuery articleQuery) throws ParseException;

    Article getArticleById(Integer id);

    void updateArticle(Article article);

    void deleteArticleById(Integer id);

    Page<Article> getDelArticle(ArticleQuery articleQuery);

    int topArticle(Integer id);

    void deleteRealArticleById(Integer id);

    int insertOrUpdateArticle(ArticleQuery articleQuery);

    void restoreArticle(Integer id);

    Page getTopArticle(List<Top> tops, ArticleQuery articleQuery);

    List<Article> getArticleByTitle(String title);
}
