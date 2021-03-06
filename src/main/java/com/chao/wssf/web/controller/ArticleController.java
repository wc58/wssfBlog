package com.chao.wssf.web.controller;

import com.chao.wssf.entity.Article;
import com.chao.wssf.entity.Label;
import com.chao.wssf.entity.Other;
import com.chao.wssf.entity.User;
import com.chao.wssf.pojo.FullComment;
import com.chao.wssf.properties.WssfProperties;
import com.chao.wssf.query.UserQuery;
import com.chao.wssf.service.*;
import com.chao.wssf.util.ArticleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private IUserService userService;

    @Autowired
    private WssfProperties wssfProperties;

    /**
     * 可以手动刷新缓存中的值
     */
    @RequestMapping("update")
    public void update() {
        articleCache.updateData();
    }


    private Boolean isSort = false;
    private Boolean isCondition = false;

    /**
     * 查询出文章的详细信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("read/{id}")
    public String readPage(@PathVariable Integer id, Model model) {

        //点击量增加一
        otherService.flowAdd(id);

        Article article = articleCache.getArticleById(id);
        List<Article> randomArticles = articleCache.getRandomArticles(id);
        Other other = otherService.getOtherByArticleId(id);

        //根据文章查出对应的评论
        List<FullComment> fullComments = commentService.getCommentsByArticleId(id);

        //处理日期
        Date lately = article.getCreateTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        String day = dayFormat.format(lately);
        day = ArticleTemplate.wipeZero(day);//截取零
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        String month = monthFormat.format(lately);
        month = ArticleTemplate.wipeZero(month);//截取零


        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("article", article);
        model.addAttribute("randomArticles", randomArticles);
        model.addAttribute("other", other);
        model.addAttribute("comments", fullComments);

        return "read";
    }

    /**
     * 文章列表的其他信息
     *
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String listPage(Integer sortId, String condition, Model model, HttpSession session) {
        //防止数据误读
        articleCache.clearSortArticle();
        isSort = false;
        articleCache.clearConditionArticle();
        isCondition = false;
        if (condition != null && !condition.equals("")) {
            //条件查询
            articleService.conditionArticle(condition);
            isCondition = true;
        } else {
            if (sortId != null) {
                //分类查询
                articleService.sortArticle(sortId);
                isSort = true;
            }
        }
        User user = (User) session.getAttribute("user");
        //如果用户登录，则更新数据
        if (user != null && !user.getThirdId().equals("8E1544B0D015EC98612B39DD5D5B90B0"))
            userService.updateUserByThirdId(user);
        //最近访问的用户
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        //侧边栏热门文章
        List<Article> hotArticles = articleService.getHotArticle();
        model.addAttribute("hotArticles", hotArticles);
        //置顶文章
        List<Article> topArticles = articleService.getTopArticle();
        model.addAttribute("topArticles", topArticles);
        //标签导航
        List<Label> labels = labelService.getAllLabel();
        model.addAttribute("labels", labels);
        //条件回显
        model.addAttribute("condition", condition);
        return "article";
    }

    /**
     * 加载文章信息
     *
     * @param page
     * @return
     */
    @RequestMapping("article")
    @ResponseBody
    public Map<String, Object> articleJson(Integer page) {
        //每次查询8条（文章的主要信息）
        Map<String, Object> articleMap = articleService.listArticle(page, wssfProperties.getQuerySize(), isSort, isCondition);
        HashMap<String, Object> map = new HashMap<>();
        //渲染页面
        map.put("articles", renderTemplate((List<Article>) articleMap.get("data"), (int) articleMap.get("tops")));
        //总页面数
        map.put("pageTotal", ((int) articleMap.get("total") / 8) + 1);
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
            List<String> labels = labelService.getLabelNamesByArticleId(articleId);

            ArticleTemplate articleTemplate = new ArticleTemplate();
            articleTemplate.setAssistant(article.getAssistant());
            articleTemplate.setCommentSize(other.getCommentSize());
            //排除评论页面刷新所增加的点击量
            System.out.println(other.getFlow() - other.getCommentSize());
            articleTemplate.setFlow(other.getFlow());
            articleTemplate.setLabels(labels);
            articleTemplate.setLately(article.getCreateTime());
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
