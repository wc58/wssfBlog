package com.chao.wssf.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Article;

import java.util.List;
import java.util.Map;

public interface IArticleService {

    List<Article> hotArticle();

    List<Article> topArticle();

    Map<String, Object> listArticle(Integer currentPage, Integer size, Boolean isSort, Boolean isCondition);

    void sortArticle(Integer id);

    void conditionArticle(String condition);

    int getAllArticleSize();

    int addArticle(String title, String assistant, String picture, String content, String author, String status, Boolean del, Boolean top, Integer[] labels);

    int updateArticle(Integer id, String title, String assistant, String picture, String content, String author, String status, Boolean del, Boolean top, Integer[] labels);

    Page<Article> getCommArticle(List<Integer> tops, Integer page, Integer limit);

    Article getArticleById(Integer id);

    void updateArticle(Article article);

    void deleteArticleById(Integer id);
}
