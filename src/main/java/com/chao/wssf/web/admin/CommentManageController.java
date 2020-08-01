package com.chao.wssf.web.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.query.CommentQuery;
import com.chao.wssf.service.ICommentService;
import com.chao.wssf.service.impl.CommentServiceImpl;
import com.chao.wssf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class CommentManageController {

    @Autowired
    private ICommentService commentService;

    /**
     * 查询所有评论
     *
     * @return
     */
    @RequestMapping("getComments")
    @ResponseBody
    public Map<String, Object> getComments(CommentQuery commonQuery) {
        return getCommentMap(false, commonQuery);
    }

    /**
     * 查询删除评论
     *
     * @return
     */
    @RequestMapping("getDelComments")
    @ResponseBody
    public Map<String, Object> getDelComments(CommentQuery commonQuery) {
        return getCommentMap(true, commonQuery);
    }

    /**
     * 查询评论的核心方法
     *
     * @return
     */
    private Map<String, Object> getCommentMap(Boolean isDel, CommentQuery commonQuery) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Page commentPage = commentService.getComments(isDel, commonQuery);
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
     * 真实删除评论
     *
     * @param id
     */
    @RequestMapping("deleteRealComment")
    @ResponseBody
    public R deleteRealComment(Integer id) {
        try {
            commentService.deleteRealById(id);
            return R.OK();
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
    }


    /**
     * 还原评论
     *
     * @param id
     */
    @RequestMapping("restoreComment")
    @ResponseBody
    public R restoreComment(Integer id) {
        try {
            CommentServiceImpl commentServiceImpl = (CommentServiceImpl) commentService;
            commentService.restoreCommentById(id);
            if (commentServiceImpl.isRestore) {
                return R.OK();
            }
            R ok = R.ERROR().data("pName", commentServiceImpl.pName);
            //重置数据
            commentServiceImpl.isRestore = true;
            commentServiceImpl.pName = "";
            return ok;
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
