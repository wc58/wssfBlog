package com.chao.wssf.web.admin;

import com.chao.wssf.entity.Article;
import com.chao.wssf.entity.Top;
import com.chao.wssf.properties.WssfProperties;
import com.chao.wssf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class PageController {

    @Autowired
    private IArticleService articleService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private ILeaveService leaveService;
    @Autowired
    private ILabelService labelService;
    @Autowired
    private ILinkService linkService;
    @Autowired
    private IOtherService otherService;
    @Autowired
    private IDiaryService diaryService;
    @Autowired
    private ITopService topService;
    @Autowired
    private IAdminService adminService;
    @Autowired
    private WssfProperties wssfProperties;

    /**
     * 桌面页面
     *
     * @return
     */
    @RequestMapping("desk")
    public String desk(Model model) {
        //获取文章数
        int articleSize = articleService.getAllArticleSize();
        model.addAttribute("articleSize", articleSize);
        //获取用户数
        int userSize = userService.getAllUserSize();
        model.addAttribute("userSize", userSize);
        //获取评论数
        int commentSize = commentService.getAllCommentSize();
        model.addAttribute("commentSize", commentSize);
        //获取吐槽数
        int leaveSize = leaveService.getAllLeaveSize();
        model.addAttribute("leaveSize", leaveSize);
        //获取标签数
        int labelSize = labelService.getAllLabelSize();
        model.addAttribute("labelSize", labelSize);
        //获取友链数
        int linkSize = linkService.getAllLinkSize();
        model.addAttribute("linkSize", linkSize);
        //获取浏览数
        int flowSize = otherService.getAllFlowSize();
        model.addAttribute("flowSize", flowSize);
        //获取日记数
        int diarySize = diaryService.getAllDiarySize();
        model.addAttribute("diarySize", diarySize);
        //获取置顶数
        int topSize = topService.getAllTopSize();
        model.addAttribute("topSize", topSize);
        //获取管理员数
        int adminSize = adminService.getAllAdminSize();
        model.addAttribute("adminSize", adminSize);
        return "admin/desk";
    }

    /**
     * 写博客页面
     *
     * @return
     */
    @RequestMapping("writeBlog")
    public String writeBlog(Model model) {
        int topSize = topService.getTopSize();
        model.addAttribute("topSize", topSize < wssfProperties.getQuerySize());
        return "admin/write-blog";
    }

    /**
     * 普通文章列表
     *
     * @return
     */
    @RequestMapping("ArticleCommList")
    public String ArticleCommList(Model model) {
        //查询出既不是置顶也不是删除的文章
        List<Integer> tops = topService.getArticleIdByTops().stream().map(Top::getArticleId).collect(Collectors.toList());
        List<Article> articles = articleService.getCommArticle(tops);
        model.addAttribute("articles", articles);
        return "admin/article-comm-list";
    }

}
