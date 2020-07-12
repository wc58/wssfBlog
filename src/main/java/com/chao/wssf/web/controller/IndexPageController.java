package com.chao.wssf.web.controller;

import com.chao.wssf.entity.Article;
import com.chao.wssf.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 由于特殊原因先只能这么写l
 */
@Controller
public class IndexPageController {

    @Autowired
    private IArticleService articleService;

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

}
