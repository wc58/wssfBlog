package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.entity.Comment;
import com.chao.wssf.entity.Other;
import com.chao.wssf.mapper.OtherMapper;
import com.chao.wssf.service.IOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OtherServiceImpl implements IOtherService {

    @Autowired
    private OtherMapper otherMapper;

    /**
     * 分页查出目前最火爆的文章
     *
     * @return 返回指定火爆文章的其他信息
     */
    @Override
    public List<Other> getOtherPaging() {
        //根据“点击量”降序排序，越靠前越热门
        QueryWrapper<Other> otherQueryWrapper = new QueryWrapper<>();
        otherQueryWrapper.orderByDesc("flow");
        return otherMapper.selectList(otherQueryWrapper);
    }

    /**
     * 根据文章id查找对应的其他信息
     *
     * @param id
     * @return
     */
    @Override
    public Other getOtherByArticleId(Integer id) {
        QueryWrapper<Other> otherQueryWrapper = new QueryWrapper<>();
        otherQueryWrapper.eq("article_id", id);
        return otherMapper.selectOne(otherQueryWrapper);
    }


    /**
     * 评论加一
     *
     * @param id
     */
    @Override
    public void commentSizeAdd(Integer id) {
        QueryWrapper<Other> otherQueryWrapper = new QueryWrapper<>();
        otherQueryWrapper.select("id", "comment_size").eq("article_id", id);
        Other other = otherMapper.selectOne(otherQueryWrapper);
        other.setCommentSize(other.getCommentSize() + 1);
        otherMapper.updateById(other);
    }

    /**
     * 访问量加一
     *
     * @param id
     */
    @Override
    public void flowAdd(Integer id) {
        flow("add", id);
    }

    /**
     * 访问量减一
     *
     * @param id
     */
    @Override
    public void flowMinus(Integer id) {
        flow("minus", id);
    }


    private void flow(String type, Integer id) {
        QueryWrapper<Other> otherQueryWrapper = new QueryWrapper<>();
        otherQueryWrapper.select("id", "flow").eq("article_id", id);
        Other other = otherMapper.selectOne(otherQueryWrapper);
        if (type.equals("add")) {
            other.setFlow(other.getFlow() + 1);
        } else {
            other.setFlow(other.getFlow() - 1);
        }
        otherMapper.updateById(other);
    }


    /**
     * 所有文章浏览量
     *
     * @return
     */
    @Override
    public int getAllFlowSize() {
        QueryWrapper<Other> otherQueryWrapper = new QueryWrapper<>();
        List<Integer> collect = otherMapper.selectList(otherQueryWrapper).stream().map(Other::getFlow).collect(Collectors.toList());
        int allFlow = 0;
        for (Integer integer : collect) {
            allFlow += integer;
        }
        return allFlow;
    }

    /**
     * 真实的删除其他表的信息
     *
     * @param id
     */
    @Override
    public void deleteRealOtherByArticleId(Integer id) {
        otherMapper.delete(new QueryWrapper<Other>().eq("article_id", id));
    }

    /**
     * 根据文章id更新评论数量
     *
     * @param id
     * @param size
     */
    @Override
    public void updateCommentSizeByArticleId(Integer id, Integer size) {
        Other other = new Other();
        other.setCommentSize(size);
        otherMapper.update(other, new QueryWrapper<Other>().eq("article_id", id));
    }

}
