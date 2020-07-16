package com.chao.wssf.web.controller;

import com.chao.wssf.entity.Comment;
import com.chao.wssf.service.ICommentService;
import com.chao.wssf.service.IOtherService;
import com.chao.wssf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IOtherService otherService;

    @RequestMapping("reply")
    @ResponseBody
    public R reply(Comment comment) {
        if (comment.getPid() == null)
            comment.setPid(0);
        try {
            commentService.addReply(comment);
            //评论数加一
            otherService.commentSizeAdd(comment.getArticleId());
            //点击量减一
            otherService.flowMinus(comment.getArticleId());
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }


}
