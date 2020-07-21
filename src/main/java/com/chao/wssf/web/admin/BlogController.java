package com.chao.wssf.web.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Admin;
import com.chao.wssf.entity.Article;
import com.chao.wssf.entity.Top;
import com.chao.wssf.service.ArticleCache;
import com.chao.wssf.service.IArticleService;
import com.chao.wssf.service.ITopService;
import com.chao.wssf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin")
public class BlogController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ITopService topService;

    @Autowired
    private ArticleCache articleCache;


    /**
     * 提交博客内容
     *
     * @param clientId
     * @param title
     * @param assistant
     * @param picture
     * @param content
     * @param status
     * @param del
     * @param top
     * @param labels
     * @param session
     * @return
     */
    @PostMapping("addArticle")
    @ResponseBody
    public R addArticle(Integer clientId, String title, String assistant, String picture, String content, String status, Boolean del, Boolean top, Integer[] labels, HttpSession session) {
        int daoId = -1;
        //添加
        Admin admin = (Admin) session.getAttribute("admin");
        String author = admin.getName();
        if (clientId == null) {
            daoId = articleService.addArticle(title, assistant, picture, content, author, status, del, top, labels);
        } else {
            //修改
            daoId = articleService.updateArticle(clientId, title, assistant, picture, content, author, status, del, top, labels);
        }
        //刷新缓存
        articleCache.updateData();
        return R.OK().data("id", daoId);
    }

    /**
     * 主要用于在列表中直接更新使用的
     *
     * @param clientId
     * @param title
     * @param assistant
     * @param picture
     * @param author
     * @param status
     * @return
     */
    @PostMapping("updateArticle")
    @ResponseBody
    public R updateArticle(@RequestParam("id") Integer clientId, String title, String assistant, String picture, String author, String status) {

        Article article = null;
        try {
            article = new Article();
            article.setId(clientId);
            article.setTitle(title);
            article.setAssistant(assistant);
            article.setPicture(picture);
            article.setAuthor(author);
            article.setStatus(status);
            article.setUpdateTime(new Date());

            articleService.updateArticle(article);

            //刷新缓存
            articleCache.updateData();
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK().data("article", article);
    }

    /**
     * 逻辑删除文章
     *
     * @param id
     */
    @RequestMapping("deleteArticle")
    @ResponseBody
    public R deleteArticle(Integer id) {
        try {
            articleService.deleteArticleById(id);
            //刷新缓存
            articleCache.updateData();
            return R.OK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }

    /**
     * 真实删除文章
     *
     * @param id
     */
    @RequestMapping("deleteRealArticle")
    @ResponseBody
    public R deleteRealArticle(Integer id) {
        try {
            articleService.deleteRealArticleById(id);
            //刷新缓存
            articleCache.updateData();
            return R.OK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }

    /**
     * 还原文章
     *
     * @param id
     */
    @RequestMapping("restoreArticle")
    @ResponseBody
    public R restoreArticle(Integer id) {
        try {
            articleService.restoreArticle(id);
            //刷新缓存
            articleCache.updateData();
            return R.OK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }

    /**
     * 根据id查找文章
     *
     * @param id
     * @return
     */
    @RequestMapping("getArticleById")
    @ResponseBody
    public R getArticleById(Integer id) {
        try {
            Article articleById = articleService.getArticleById(id);
            return R.OK().data("article", articleById);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }

    /**
     * 根据id置顶文章
     *
     * @param id
     * @return
     */
    @RequestMapping("topArticle")
    @ResponseBody
    public R topArticle(Integer id) {
        try {
            int flag = articleService.topArticle(id);
            if (flag == 1) {//返回说明置顶成功
                articleCache.updateData();
                return R.OK();
            } else {
                return R.ERROR();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }

    /**
     * 查出普通的文章
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("getCommArticle")
    @ResponseBody
    public Map<String, Object> getCommArticle(Integer page, Integer limit, String title, String author, String status, String startTime, String endTime) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            //查询出既不是置顶也不是删除的文章
            List<Integer> tops = topService.getArticleIdByTops().stream().map(Top::getArticleId).collect(Collectors.toList());
            //防止sql错误
            tops.add(-1);
            Page<Article> articlePage = articleService.getCommArticle(tops, page, limit, title, author, status, startTime, endTime);
            //封装数据
            map.put("code", 0);
            map.put("count", articlePage.getTotal());
            map.put("data", articlePage.getRecords());
            return map;
        } catch (ParseException e) {
            e.printStackTrace();
            map.put("code", 1);
            return map;
        }
    }

    /**
     * 查出删除的文章
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("getDelArticle")
    @ResponseBody
    public Map<String, Object> getDelArticle(Integer page, Integer limit, String title, String author, String status, String startTime, String endTime) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            //查询出是删除的文章
            Page<Article> articlePage = articleService.getDelArticle(page, limit, title, author, status, startTime, endTime);
            //封装数据
            map.put("code", 0);
            map.put("count", articlePage.getTotal());
            map.put("data", articlePage.getRecords());
            return map;
        } catch (ParseException e) {
            e.printStackTrace();
            map.put("code", 1);
            return map;
        }
    }

}
