package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Article;
import com.chao.wssf.entity.Comment;
import com.chao.wssf.entity.User;
import com.chao.wssf.mapper.CommentMapper;
import com.chao.wssf.mapper.UserMapper;
import com.chao.wssf.pojo.AllComment;
import com.chao.wssf.pojo.FullComment;
import com.chao.wssf.service.IArticleService;
import com.chao.wssf.service.ICommentService;
import com.chao.wssf.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService {


    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private IArticleService articleService;


    @Override
    public List<FullComment> getCommentsByArticleId(Integer id) {
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("article_id", id).eq("del", "0").eq("pid", "0").orderByDesc("create_time");
        List<Comment> comments = commentMapper.selectList(commentQueryWrapper);
        ArrayList<FullComment> fullComments = new ArrayList<>();
        for (Comment comment : comments) {
            FullComment fullComment = new FullComment();
            fullComment.setComment(comment);
            User user = userMapper.selectById(comment.getUserId());
            fullComment.setUser(user);
            fullComments.add(fullComment);
            reply(comment.getId(), user.getName(), fullComment.getFullComments());
        }
        return fullComments;
    }


    @Override
    public void addReply(Comment comment) {
        comment.setCreateTime(new Date());
        comment.setDel("0");
        commentMapper.insert(comment);
    }


    private void reply(Integer pid, String parentName, List<FullComment> fullComments) {

        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("pid", pid).eq("del", "0").orderByAsc("create_time");
        List<Comment> comments = commentMapper.selectList(commentQueryWrapper);
        //递归的条件出口
        if (comments.size() <= 0) {
            //主要用于页面来分割评论
            //核心：“ERROR20020508”
            //在leave.jsp中会使用到
            FullComment fullComment = new FullComment();
            User user = new User();
            user.setName("ERROR20020508");
            fullComment.setUser(user);
            fullComments.add(fullComment);
            return;
        }
        for (Comment comment : comments) {
            FullComment fullComment = new FullComment();
            comment.setParentName(parentName);
            fullComment.setComment(comment);
            User user = userMapper.selectById(comment.getUserId());
            fullComment.setUser(user);
            fullComments.add(fullComment);
            reply(comment.getId(), user.getName(), fullComments);
        }

    }

    /**
     * 获取所有评论数
     *
     * @return
     */
    @Override
    public int getAllCommentSize() {
        return commentMapper.selectCount(new QueryWrapper<>());
    }

    /**
     * 删除文章对应的评论
     *
     * @param id
     */
    @Override
    public void deleteRealCommentByArticleId(Integer id) {
        commentMapper.delete(new QueryWrapper<Comment>().eq("article_id", id));
    }

    /**
     * 分页获取评论内容
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
    @Override
    public Page getComments(Integer page, Integer limit, String title, String username, String content, String startTime, String endTime) throws ParseException {
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("del", "0").orderByDesc("create_time");


        if (title != null && !title.equals("")) {
            List<Integer> ids = articleService.getArticleByTitle(title).stream().map(Article::getId).collect(Collectors.toList());
            ids.add(-1);
            commentQueryWrapper.in("article_id", ids);
        }
        if (username != null && !username.equals("")) {
            List<Integer> ids = userService.getUserByUsername(username).stream().map(User::getId).collect(Collectors.toList());
            ids.add(-1);
            commentQueryWrapper.in("user_id", ids);
        }
        if (content != null && !content.equals("")) {
            commentQueryWrapper.like("content", content);
        }

        //对日期进行转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(startTime)) {
            Date date = simpleDateFormat.parse(startTime);
            commentQueryWrapper.ge("create_time", date);
        }
        if (!StringUtils.isEmpty(endTime)) {
            Date date = simpleDateFormat.parse(endTime);
            commentQueryWrapper.le("create_time", date);
        }

        Page<Comment> commentPage = new Page<>(page, limit);
        Page selectPage = commentMapper.selectPage(commentPage, commentQueryWrapper);

        List records = selectPage.getRecords();
        ArrayList<AllComment> allComments = new ArrayList<>();
        for (Object obj : records) {
            Comment comment = (Comment) obj;
            AllComment allComment = new AllComment();
            Integer userId = comment.getUserId();
            Integer articleId = comment.getArticleId();
            //根据评论信息查询对应的用户和文章
            String articleTitle = articleService.getArticleById(articleId).getTitle();
            String name = userService.getUserById(userId).getName();
            allComment.setTitle(articleTitle);
            allComment.setUserName(name);
            BeanUtils.copyProperties(comment, allComment);
            allComments.add(allComment);
        }
        selectPage.setRecords(allComments);

        return selectPage;
    }

    /**
     * 逻辑删除评论
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setDel("1");
        commentMapper.updateById(comment);
    }

    /**
     * 真实删除评论
     *
     * @param id
     */
    @Override
    public void deleteRealById(Integer id) {
        commentMapper.deleteById(id);
    }

    /**
     * 修改评论内容
     *
     * @param id
     * @param content
     */
    @Override
    public void updateComment(Integer id, String content) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setContent(content);
        commentMapper.updateById(comment);
    }


}
