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
        //查询现在所有的指定文章
        QueryWrapper<Top> topQueryWrapper = new QueryWrapper<>();
        topQueryWrapper.eq("del", 0).orderByAsc("sort");
        List<Top> tops = topMapper.selectList(topQueryWrapper);
        //判断置顶时间是否在指定时间内
        ArrayList<Top> ids = new ArrayList<>();
        for (Top top : tops) {
            long endTime = top.getEndTime().getTime();
            long startTime = top.getStartTime().getTime();
            long time = new Date().getTime();
            //1：则表明永久置顶
            if (top.getEver().equals("1")) {
                ids.add(top);
            } else if (startTime < time && endTime > time) {
                ids.add(top);
            }
        }
        return ids;
    }

    /**
     * 获取所有置顶数
     * @return
     */
    @Override
    public int getAllTopSize() {
        return topMapper.selectCount(new QueryWrapper<>());
    }
}
