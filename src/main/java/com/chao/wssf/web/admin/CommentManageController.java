package com.chao.wssf.web.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Top;
import com.chao.wssf.pojo.TopArticle;
import com.chao.wssf.service.ICommentService;
import com.chao.wssf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class CommentManageController {

    @Autowired
    private ICommentService commentService;

    /**
     * 查询所有评论
     *
     * @param page
     * @param limit
     * @param title
     * @param username
     * @param content
     * @param contentSize
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping("getComments")
    @ResponseBody
    public Map<String, Object> getComments(Integer page, Integer limit, String title, String username, String content, String contentSize, String startTime, String endTime) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Page commentPage = commentService.getComments(page, limit, title, username, content, startTime, endTime);
            //封装数据
            map.put("code", 0);
            map.put("count", commentPage.getTotal());
            map.put("data", commentPage.getRecords());
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);
            return map;
        }
    }

    /**
     * 逻辑删除评论
     *
     * @param id
     */
    @RequestMapping("deleteComment")
    @ResponseBody
    public R deleteComment(Integer id) {
        try {
            commentService.deleteById(id);
            return R.OK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }

    /**
     * 逻辑删除评论
     *
     * @param id
     */
    @RequestMapping("updateComment")
    @ResponseBody
    public R updateComment(Integer id, String content) {
        try {
            commentService.updateComment(id, content);
            return R.OK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }

}
