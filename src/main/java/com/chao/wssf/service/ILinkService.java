package com.chao.wssf.service;

import com.chao.wssf.entity.Link;

import java.util.List;

public interface ILinkService {

    List<Link> getAllLinks();

    int getAllLinkSize();

    Link getLinkByUserId(Integer id);

    void addLink(String title, String icon, String url, String desc, Integer id);
}
