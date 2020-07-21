package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.entity.Top;
import com.chao.wssf.mapper.OtherMapper;
import com.chao.wssf.mapper.TopMapper;
import com.chao.wssf.service.ITopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TopServiceImpl implements ITopService {

    @Autowired
    private TopMapper topMapper;


    /**
     * 主要查询哪些是置顶的文章
     *
     * @return 文章的ID
     */
    @Override
    public List<Top> getArticleIdByTops() {
        //查询所有的指定文章
        QueryWrapper<Top> topQueryWrapper = new QueryWrapper<>();
        topQueryWrapper.orderByAsc("sort");
        return topMapper.selectList(topQueryWrapper);
    }

    /**
     * 获取所有置顶数
     *
     * @return
     */
    @Override
    public int getAllTopSize() {
        return topMapper.selectCount(new QueryWrapper<>());
    }

    public int getTopSize() {
        QueryWrapper<Top> topQueryWrapper = new QueryWrapper<>();
        return topMapper.selectCount(topQueryWrapper);
    }

    /**
     * 根据文文章id查询
     *
     * @param id
     * @return
     */
    @Override
    public Top getTopByArticleId(Integer id) {
        QueryWrapper<Top> topQueryWrapper = new QueryWrapper<>();
        topQueryWrapper.eq("article_id", id);
        return topMapper.selectOne(topQueryWrapper);
    }
}
