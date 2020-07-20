package com.chao.wssf.web.admin;

import com.chao.wssf.entity.Admin;
import com.chao.wssf.entity.Article;
import com.chao.wssf.entity.Top;
import com.chao.wssf.service.IArticleService;
import com.chao.wssf.service.ITopService;
import com.chao.wssf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin")
public class BlogController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ITopService topService;


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
        return R.OK().data("id", daoId);
    }


    @RequestMapping("getCommArticle")
    @ResponseBody
    public List<Article> getCommArticle() {
        //查询出既不是置顶也不是删除的文章
        List<Integer> tops = topService.getArticleIdByTops().stream().map(Top::getArticleId).collect(Collectors.toList());
        return articleService.getCommArticle(tops);
    }

}
