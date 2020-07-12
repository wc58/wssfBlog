package com.chao.wssf.web.controller;

import com.chao.wssf.entity.Article;
import com.chao.wssf.entity.Comment;
import com.chao.wssf.entity.Label;
import com.chao.wssf.entity.Other;
import com.chao.wssf.service.*;
import com.chao.wssf.util.ArticleTemplate;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("blog")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ILabelService labelService;

    @Autowired
    private IOtherService otherService;

    @Autowired
    private ArticleCache articleCache;

    @Autowired
    private ICommentService commentService;

    @RequestMapping("update")
    public void update() {
        articleCache.updateData();
    }

    /**
     * 查询出文章的详细信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("read/{id}")
    public String readPage(@PathVariable Integer id, Model model) {
        Article article = articleCache.getArticleById(id);
        Other other = otherService.getOtherByArticleId(id);
        List<Comment> comments = commentService.getCommentsByArticleId(id);

        model.addAttribute("article", article);
        model.addAttribute("other", other);
        model.addAttribute("comments", comments);

        return "read";
    }

    /**
     * 文章列表的其他信息
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
    public Map<String, Object> articleJson(Integer page) {
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
            //设置链接地址
            articleTemplate.setLink("/blog/read/" + article.getId());
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
