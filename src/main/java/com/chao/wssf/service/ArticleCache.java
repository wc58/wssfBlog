package com.chao.wssf.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chao.wssf.entity.Article;
import com.chao.wssf.entity.Top;
import com.chao.wssf.mapper.ArticleMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
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
    private List<Article> allArticles = new ArrayList<>();
    //仅有置顶文章
    private List<Article> topArticles = new ArrayList<>();
    //置顶加普通文章（时间排序）
    private List<Article> topAndArticles = new ArrayList<>();
    //普通文章（时间排序）
    private List<Article> articles = new ArrayList<>();
    //已经删除的文章
    private List<Article> delArticles = new ArrayList<>();
    //置顶文章的顺序
    private List<Integer> topIds = new ArrayList<>();
    //分类文章
    private List<Article> sortArticles = new ArrayList<>();
    //条件文章
    private List<Article> conditionArticles = new ArrayList<>();
    //当前状态的总条数
    private int currentTotal;

    /**
     * 项目启动读取数据
     */
    @Override
    public void afterPropertiesSet() {
        addOrUpdateData();
    }

    /**
     * 数据库发生修改，调用该方法更新缓存
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
     * 负责加载处理数据
     */
    private void addOrUpdateData() {
        //查询所有数据
        allArticles = articleMapper.selectList(new QueryWrapper<Article>().select());
        //置顶文章的id顺序
        topIds = topService.getArticleIdByTops().stream().map(Top::getArticleId).collect(Collectors.toList());
        for (Article a : allArticles) {
            if (!a.getDel().equals("0")) {//表示数据被删除
                delArticles.add(a);
                continue;
            }
            if (topIds.contains(a.getId())) { //置顶文章
                topArticles.add(a);
            } else {//普通文章
                articles.add(a);
            }
        }
        //时间排序
        this.articles = insertSort(this.articles);
        //置顶排序
        topSort();
        topAndArticles.addAll(topArticles);
        topAndArticles.addAll(this.articles);
    }


    /**
     * 置顶文章按指定顺序排序
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
     * 普通文章时间属性排序
     */
    private List<Article> insertSort(List<Article> arrs) {
        return arrs.stream()
                .sorted(Comparator.comparing(Article::getCreateTime).reversed())
                .collect(Collectors.toList());
    }

    /**
     * 获取指定的所有文章
     *
     * @param ids
     * @return
     */
    public List<Article> getArticleByIds(List<Integer> ids) {
        ArrayList<Article> selectArticles = new ArrayList<>();
        for (Integer id : ids) {
            for (Article a : topAndArticles) {
                if (a.getId().equals(id)) {
                    selectArticles.add(a);
                    break;
                }
            }
        }
        return selectArticles;
    }


    /**
     * 指定id获取文章
     *
     * @param id
     * @return
     */
    public Article getArticleById(Integer id) {
        for (Article a : allArticles) {
            if (a.getId().equals(id))
                return a;
        }
        return null;
    }

    /**
     * 随机推荐阅读
     *
     * @return
     */
    public List<Article> getRandomArticles(Integer id) {
        ArrayList<Article> randomArticles = new ArrayList<>();
        List<Article> topAndArticle = getAllOrSortArticle(1, getAllArticleTotal(), false, false);
        Random random = new Random();
        int maxSize = 3;
        if (topAndArticle.size() <= 3) {
            maxSize = topAndArticle.size() - 1;
        }
        for (int i = 0; i < maxSize; i++) {
            int ran = random.nextInt(topAndArticle.size());
            //排除现读的文章
            if (topAndArticle.get(ran).getId().equals(id)) {
                i--;
                continue;
            }
            randomArticles.add(topAndArticle.get(ran));
            topAndArticle.remove(ran);
        }
        return randomArticles;
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
     * 或者分类查询的文章
     *
     * @param currentPage
     * @param size
     * @return
     */
    public List<Article> getAllOrSortArticle(Integer currentPage, Integer size, Boolean isSort, Boolean isCondition) {
        List<Article> pageArticles = topAndArticles;
        if (isCondition) {//优先条件查询
            pageArticles = conditionArticles;
            currentTotal = getConditionArticleTotal();
        } else {
            if (isSort) {//分类查询
                pageArticles = sortArticles;
                currentTotal = getSortArticleTotal();
            } else {//普通查询
                currentTotal = getAllArticleTotal();
            }
        }
        int index = (currentPage - 1) * size;
        ArrayList<Article> list = new ArrayList<>();
        //i：每次访问的索引 j：已查询的个数
        for (int i = index; i < index + size; i++) {
            //说明到头了
            if (i >= pageArticles.size())
                break;
            list.add(pageArticles.get(i));
        }
        return list;
    }

    /**
     * 分类条件筛选
     *
     * @param ids
     */
    public void sortArticle(List<Integer> ids) {
        sortArticles = getArticleByIds(ids);
        //再进行时间排序
        sortArticles = insertSort(sortArticles);
    }


    /**
     * 清空分类集合
     */
    public void clearSortArticle() {
        sortArticles.clear();
    }


    public void conditionArticle(String condition) {
        for (Article article : topAndArticles) {
            //去空格，转小写
            condition = condition.trim().toLowerCase(Locale.CHINESE);
            String title = article.getTitle().trim().toLowerCase(Locale.CHINESE);
            if (title.contains(condition)) {
                conditionArticles.add(article);
                continue;
            }
        }
    }

    public void clearConditionArticle() {
        conditionArticles.clear();
    }

    /**
     * 当前状态的总条数
     *
     * @return
     */
    public int getCurrentTotal() {
        return currentTotal;
    }

    /**
     * 条件中文章数
     *
     * @return
     */
    public int getConditionArticleTotal() {
        return conditionArticles.size();
    }

    /**
     * 分类中文章数
     *
     * @return
     */
    public int getSortArticleTotal() {
        return sortArticles.size();
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
