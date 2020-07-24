package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.entity.Link;
import com.chao.wssf.mapper.LinkMapper;
import com.chao.wssf.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LinkServiceImpl implements ILinkService {

    @Autowired
    private LinkMapper linkMapper;

    /**
     * 所有友链
     *
     * @return
     */
    @Override
    public List<Link> getAllLinks() {
        QueryWrapper<Link> linkQueryWrapper = new QueryWrapper<>();
        linkQueryWrapper.eq("del", "0").orderByDesc("sort");
        return linkMapper.selectList(linkQueryWrapper);
    }

    /**
     * 友链数量
     *
     * @return
     */
    @Override
    public int getAllLinkSize() {
        return linkMapper.selectCount(new QueryWrapper<>());
    }

    /**
     * 根据用户查找对应的友链
     *
     * @param id
     * @return
     */
    @Override
    public Link getLinkByUserId(Integer id) {
        return linkMapper.selectOne(new QueryWrapper<Link>().eq("user_id", id));
    }

    /**
     * 添加友链
     *
     * @param title
     * @param icon
     * @param url
     * @param desc
     * @param id
     */
    @Override
    public void addLink(String title, String icon, String url, String desc, Integer id) {
        Link link = new Link();
        link.setTitle(title);
        link.setIcon(icon);
        link.setUrl(url);
        link.setSort(0);
        link.setDes(desc);
        link.setUserId(id);
        link.setDel("1");
        Date date = new Date();
        link.setUpdateTime(date);
        link.setCreateTime(date);
        linkMapper.insert(link);
    }
}
