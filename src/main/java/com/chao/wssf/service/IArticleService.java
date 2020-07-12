package com.chao.wssf.service;

import com.chao.wssf.entity.Article;

import java.util.List;
import java.util.Map;

public interface IArticleService {

    List<Article> hotArticle();


    List<Article> topArticle();

    Map<String, Object> listArticle(Integer currentPage, Integer size);

}
