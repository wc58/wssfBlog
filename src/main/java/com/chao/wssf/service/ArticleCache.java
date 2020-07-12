package com.chao.wssf.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.entity.Article;
import com.chao.wssf.entity.Top;
import com.chao.wssf.mapper.ArticleMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 由于这个是个人博客，一般都是查询
 * 所以我直接在项目启动的时候把数据读取进来
 * 后续就不对数据库进行操作，可以减少数据库压力，以及提高效率
 */
@Component
public class ArticleCache implements InitializingBean {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ITopService topService;

    //数据库原封不动的文章
    List<Article> allArticles = new ArrayList<>();
    //仅有置顶文章
    List<Article> topArticles = new ArrayList<>();
    //置顶加普通文章
    List<Article> topAndArticles = new ArrayList<>();
    //普通文章
    List<Article> articles = new ArrayList<>();
    //已经删除的文章
    List<Article> delArticles = new ArrayList<>();
    List<Integer> topIds = new ArrayList<>();

    /**
     * 项目启动进行读取数据
     */
    @Override
    public void afterPropertiesSet() {
        //数据采集
        addOrUpdateData();

    }

    /**
     * 当后台对文字进行增删改的时候，调用该方法进行数据刷新，防止数据不准确
     */
    public void updateData() {
        //清空数据
        allArticles = new ArrayList<>();
        topArticles = new ArrayList<>();
        topAndArticles = new ArrayList<>();
        articles = new ArrayList<>();
        addOrUpdateData();
    }

    /**
     * 正在负责处理数据
     */
    private void addOrUpdateData() {
        //查询所有数据
        allArticles = articleMapper.selectList(new QueryWrapper<>());
        //置顶文章的id顺序
        topIds = topService.getArticleIdByTops().stream().map(Top::getArticleId).collect(Collectors.toList());
        for (Article a : allArticles) {
            //表示数据被删除
            if (!a.getDel().equals("0")) {
                delArticles.add(a);
                continue;
            }
            //置顶文章
            if (topIds.contains(a.getId())) {
                topArticles.add(a);
                //普通文章
            } else {
                articles.add(a);
            }
        }
        //时间排序
        insertSort(articles);
        //置顶排序
        topSort();
        topAndArticles.addAll(topArticles);
        topAndArticles.addAll(articles);
    }


    /**
     * 对置顶文章进行排序
     */
    private void topSort() {
        ArrayList<Article> selectArticles = new ArrayList<>();
        //按照置顶的顺序排序
        for (Integer topId : topIds) {
            for (Article topArticle : topArticles) {
                if (topArticle.getId().equals(topId))
                    selectArticles.add(topArticle);
            }
        }
        topArticles = selectArticles;
    }

    /**
     * 选择排序
     * 按照时间进行排序
     */
    public void insertSort(List<Article> arrs) {
        for (int i = 1; i < arrs.size(); i++) {
            Comparable insertVal = arrs.get(i).getUpdateTime();
            Article temp = arrs.get(i);
            int insertIndex = i - 1;
            while (insertIndex >= 0 && less(arrs.get(insertIndex).getUpdateTime(), insertVal)) {
                arrs.set(insertIndex + 1, arrs.get(insertIndex));
                insertIndex--;
            }
            arrs.set((insertIndex + 1), temp);
        }
    }

    /**
     * 升序排序
     */
    public boolean more(Comparable v, Comparable m) {
        return v.compareTo(m) > 0;
    }

    /**
     * 降序排序
     */
    public boolean less(Comparable v, Comparable m) {
        return v.compareTo(m) < 0;
    }


    /**
     * 获取指定的文章
     *
     * @param ids
     * @return
     */
    public List<Article> getArticleByIds(List<Integer> ids) {
        ArrayList<Article> selectArticles = new ArrayList<>();
        for (Integer id : ids) {
            for (Article a : topAndArticles) {
                if (a.getId().equals(id))
                    selectArticles.add(a);
            }
        }
        return selectArticles;
    }

    /**
     * 获取置顶文章
     *
     * @return
     */
    public List<Article> getTops() {
        return topArticles;
    }

    /**
     * 获取置顶和普通的文章
     *
     * @param currentPage
     * @param size
     * @return
     */
    public List<Article> getTopAndArticle(Integer currentPage, Integer size) {
        int index = (currentPage - 1) * size;
        ArrayList<Article> list = new ArrayList<>();
        //i：每次访问的索引 j：查询的个数
        for (int i = index; i < index + size; i++) {
            //说明到头了
            if (i == topAndArticles.size())
                break;
            Article article = topAndArticles.get(i);
            //没有被逻辑删除的
            if (article.getDel().equals("0"))
                list.add(article);
        }
        return list;
    }


    /**
     * 有效的总文章
     *
     * @return
     */
    public int getAllArticleTotal() {
        return topAndArticles.size();
    }

    /**
     * 有效的置顶文章
     *
     * @return
     */
    public int getTopArticleTotal() {
        return topArticles.size();
    }
}
