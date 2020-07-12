package com.chao.wssf.web.controller;

import com.chao.wssf.entity.Article;
import com.chao.wssf.entity.Label;
import com.chao.wssf.entity.Other;
import com.chao.wssf.service.ArticleCache;
import com.chao.wssf.service.IArticleService;
import com.chao.wssf.service.ILabelService;
import com.chao.wssf.service.IOtherService;
import com.chao.wssf.util.ArticleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ILabelService labelService;

    @Autowired
    private IOtherService otherService;

    @Autowired
    private ArticleCache articleCache;

    @RequestMapping("update")
    public void update() {
        articleCache.updateData();
    }

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @RequestMapping({"/", "index"})
    public String indexPage(Model model) {
        //首页热门文章
        List<Article> articles = articleService.hotArticle();

        model.addAttribute("articles", articles);
        return "index";
    }

    /**
     * 文章列表
     *
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String listPage(Model model) {
        //侧边栏热门文章
        List<Article> hotArticles = articleService.hotArticle();
        model.addAttribute("hotArticles", hotArticles);
        //置顶文章
        List<Article> topArticles = articleService.topArticle();
        model.addAttribute("topArticles", topArticles);
        //标签导航
        List<Label> labels = labelService.allLabel();
        model.addAttribute("labels", labels);
        return "article";
    }

    /**
     * 返回文章信息
     *
     * @param page
     * @return
     */
    @RequestMapping("article")
    @ResponseBody
    public Map<String, Object> articles(Integer page) {
        //每次查询5条（文章的主要信息）
        Map<String, Object> articleMap = articleService.listArticle(page, 5);
        HashMap<String, Object> map = new HashMap<>();
        //渲染页面
        map.put("articles", renderTemplate((List<Article>) articleMap.get("data"), (int) articleMap.get("tops")));
        //总页面数
        map.put("pageTotal", ((int) articleMap.get("total") / 5) + 1);
        return map;
    }

    /**
     * 主要用于渲染页面
     *
     * @param articles
     * @param topArticleIds
     * @return
     */
    private String renderTemplate(List<Article> articles, Integer topArticleIds) {
        StringBuilder template = new StringBuilder();
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            Integer articleId = article.getId();
            Other other = otherService.getOtherByArticleId(articleId);
            List<String> labels = labelService.getLabelsByArticleId(articleId);

            ArticleTemplate articleTemplate = new ArticleTemplate();
            articleTemplate.setAssistant(article.getAssistant());
            articleTemplate.setCommentSize(other.getCommentSize());
            articleTemplate.setFlow(other.getFlow());
            articleTemplate.setLabels(labels);
            articleTemplate.setLately(article.getUpdateTime());
            articleTemplate.setLink("http://www.lzqcode.com/Blog/Java/6.html");
            articleTemplate.setPicture(article.getPicture());
            articleTemplate.setTitle(article.getTitle());
            //topArticleIds表示置顶文章的数量
            //小于这个数说明都是置顶的文章
            articleTemplate.setTop(i < topArticleIds);
            articleTemplate.setStatus(article.getStatus());
            template.append(articleTemplate.build());
        }
        return template.toString();
    }

}
