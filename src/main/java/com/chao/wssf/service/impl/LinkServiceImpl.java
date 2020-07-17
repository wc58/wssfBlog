package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.entity.Link;
import com.chao.wssf.mapper.LinkMapper;
import com.chao.wssf.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements ILinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public List<Link> getAllLinks() {
        QueryWrapper<Link> linkQueryWrapper = new QueryWrapper<>();
        linkQueryWrapper.eq("del", "1").orderByAsc("sort");
        return linkMapper.selectList(linkQueryWrapper);
    }

    @Override
    public int getAllLinkSize() {
        return linkMapper.selectCount(new QueryWrapper<>());
    }
}
