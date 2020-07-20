package com.chao.wssf.service;

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

    List<Article> getCommArticle(List<Integer> tops);
}
