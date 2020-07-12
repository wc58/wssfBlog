package com.chao.wssf.service.impl;

import com.chao.wssf.entity.Article;
import com.chao.wssf.entity.Other;
import com.chao.wssf.service.ArticleCache;
import com.chao.wssf.service.IArticleService;
import com.chao.wssf.service.IOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private IOtherService otherService;
    @Autowired
    private ArticleCache articleCache;

    /**
     * 按点击量查询
     *
     * @return
     */
    @Override
    public List<Article> hotArticle() {
        List<Integer> articleIds = otherService.getOtherPaging().stream().map(Other::getArticleId).collect(Collectors.toList());
        //查出对应的文章
        return articleCache.getArticleByIds(articleIds);
    }

    /**
     * 查出哪些表是置顶状态
     *
     * @return
     */
    @Override
    public List<Article> topArticle() {
        //查出对应的文章
        return articleCache.getTops();
    }

    /**
     * 分页查询出文章信息
     *
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> listArticle(Integer currentPage, Integer size) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("tops", 0);
        //如果是第一页，查出置顶文章
        if (currentPage.equals(1)) {
            int topArticleTotal = articleCache.getTopArticleTotal();
            //如果出现异常则可能一直在转圈圈！！！
            if (topArticleTotal > size)
                throw new RuntimeException("置顶文章不能大于首页最大显示数量");
            map.put("tops", topArticleTotal);
        }

        //查出文章
        map.put("data", articleCache.getTopAndArticle(currentPage, size));
        map.put("total", articleCache.getAllArticleTotal());
        return map;
    }

}
