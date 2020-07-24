package com.chao.wssf.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.wssf.entity.Link;

import java.text.ParseException;
import java.util.List;

public interface ILinkService {

    List<Link> getAllLinks();

    int getAllLinkSize();

    Link getLinkByUserId(Integer id);

    void addLink(String title, String icon, String url, String desc, Integer id);

    Page getLinks(Integer page, Integer limit, String username, String name, String startTime, String endTime) throws ParseException;

    void deleteLinkById(Integer id);

    void updateLinkById(Integer id, String title, String icon, String url, Integer sort, String des, String del);
}
