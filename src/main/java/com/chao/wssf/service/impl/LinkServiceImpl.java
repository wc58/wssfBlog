package com.chao.wssf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Link;
import com.chao.wssf.entity.User;
import com.chao.wssf.mapper.LinkMapper;
import com.chao.wssf.pojo.AllLink;
import com.chao.wssf.service.ILinkService;
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
public class LinkServiceImpl implements ILinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Autowired
    private IUserService userService;

    /**
     * 所有正常友链
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

    /**
     * 获取所有的友链
     *
     * @param page
     * @param limit
     * @param username
     * @param name
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public Page getLinks(Integer page, Integer limit, String username, String name, String startTime, String endTime) throws ParseException {
        QueryWrapper<Link> linkQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(username)) {
            List<Integer> ids = userService.getUserByUsername(username).stream().map(User::getId).collect(Collectors.toList());
            ids.add(-1);
            linkQueryWrapper.in("user_id", ids);
        }
        if (!StringUtils.isEmpty(name)) {
            linkQueryWrapper.like("title", name);
        }
        //对日期进行转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(startTime)) {
            Date date = simpleDateFormat.parse(startTime);
            linkQueryWrapper.ge("create_time", date);
        }
        if (!StringUtils.isEmpty(endTime)) {
            Date date = simpleDateFormat.parse(endTime);
            linkQueryWrapper.le("create_time", date);
        }
        Page linkPage = new Page<>(page, limit);
        Page selectPage = linkMapper.selectPage(linkPage, linkQueryWrapper);
        ArrayList<AllLink> allLinks = new ArrayList<>();
        for (Object record : selectPage.getRecords()) {
            Link link = (Link) record;
            AllLink allLink = new AllLink();
            Integer userId = link.getUserId();
            allLink.setUserName(userService.getUserById(userId).getName());
            BeanUtils.copyProperties(link, allLink);
            allLinks.add(allLink);
        }
        linkPage.setRecords(allLinks);
        return selectPage;
    }
}
