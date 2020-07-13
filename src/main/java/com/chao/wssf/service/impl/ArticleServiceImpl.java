package com.chao.wssf.service.impl;

import com.chao.wssf.entity.Article;
import com.chao.wssf.entity.Other;
import com.chao.wssf.service.ArticleCache;
import com.chao.wssf.service.IArticleService;
import com.chao.wssf.service.ILabelService;
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

    @Autowired
    private ILabelService labelService;

    /**
     * 点击量排序查询
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
     * 置顶文章
     *
     * @return
     */
    @Override
    public List<Article> topArticle() {
        //查出对应的文章
        return articleCache.getTops();
    }

    /**
     * 分页查询出文章信息（置顶，普通，分类）
     *
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> listArticle(Integer currentPage, Integer size, Boolean isSort, Boolean isCondition) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("tops", 0);
        //若是第一页且是普通查询（非条件），查出置顶文章
        if (currentPage.equals(1) && !isSort && !isCondition) {
            int topArticleTotal = articleCache.getTopArticleTotal();
            //若出现异常则可能一直在转圈圈！！！
            if (topArticleTotal > size)
                throw new RuntimeException("置顶文章不能大于首页最大显示数量");
            map.put("tops", topArticleTotal);
        }

        //分页文章
        map.put("data", articleCache.getAllOrSortArticle(currentPage, size, isSort, isCondition));
        //当前状态总页
        map.put("total", articleCache.getCurrentTotal());
        return map;
    }

    /**
     * 按标签进行分类
     *
     * @param id
     */
    @Override
    public void sortArticle(Integer id) {
        articleCache.sortArticle(labelService.getArticleIdsByLabelId(id));
    }

    /**
     * 条件查询
     *
     * @param condition
     */
    @Override
    public void conditionArticle(String condition) {
        articleCache.conditionArticle(condition);
    }


}
