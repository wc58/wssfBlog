package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Other;
import com.chao.wssf.mapper.OtherMapper;
import com.chao.wssf.service.IOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
