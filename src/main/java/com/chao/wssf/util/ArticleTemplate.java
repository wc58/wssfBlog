package com.chao.wssf.util;

import com.alibaba.druid.sql.visitor.functions.Char;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 用于页面渲染
 * 天呐，我真不知道我是怎么想的，居然手写的把一句一句的给复制过来
 * 累啊类
 */
@Data
public class ArticleTemplate {

    //文章内容的链接
    private String link;
    //置顶？
    private Boolean top;
    //标题
    private String title;
    //自创or转载
    private String status;
    //最近修改时间
    private Date lately;
    //图片
    private String picture;
    //副标题
    private String assistant;
    //标签
    private List<String> labels;
    //点击量
    private Integer flow;
    //评论总数
    private Integer commentSize;


    /**
     * 构建文章模板
     *
     * @return
     */
    public String build() {
        
         /*
    以下都是模板
     */
        String sectionStart = "<section class=\"article-item zoomIn article\">";
        String sectionEnd = "</section>";

        //置顶则加html
        String top = "<div class=\"fc-flag\">置顶</div>";

        //标题部分
        String h5Start = "<h5 class=\"title\">";
        String h5End = "</h5>";
        String statusStart = "<span class=\"fc-blue\">【";
        String statusEnd = "】</span>";
        String titleStart = "<a href=\"";
        String titleCenter = "\">";
        String titleEnd = "</a>";

        //日期部分
        String timeStart = "<div class=\"time\">";
        String timeEnd = " </div>";
        String dayStart = " <span class=\"day\">";
        String dayEnd = "</span>";
        String monthStart = "<span class=\"month fs-18\">";
        String monthEnd = "<span class=\"fs-14\">月</span></span>";
        String yearStart = "<span class=\"year fs-18 ml10\">";
        String yearEnd = "</span>";

        //副标题部分
        String contentStart = "<div class=\"content\">";
        String contentEnd = "</div>";
        String aStart = "<a href=\"";
        String aCentre = "\" class=\"cover img-light\">";
        String aEnd = "</a>";
        String imgStart = "<img src=\"";
        String imgEnd = "\">";

        //文章内容
        String readMoreStart = "<div class=\"read-more\">";
        String readMoreEnd = "</div>";
        String readMoreLinkStart = " <a href=\"";
        String readMoreLinkEnd = "\" class=\"fc-black f-fwb\">文章内容</a>";

        //底部信息
        String footerStart = "<aside class=\"f-oh footer\">";
        String footerEnd = "</aside>";

        //标签信息
        String tagsStart = " <div class=\"f-fl tags\">";
        String tagsEnd = "</div>";
        String tagsCenter = "<span class=\"fa fa-tags fs-16\"></span>";
        String tagsAStart = "<a class=\"tag\">";
        String tagsAEnd = "</a>";

        //右边信息
        String frStart = "<div class=\"f-fr\">";
        String frEnd = "</div>";

        //点击量
        String readStart = "<span class=\"read\">";
        String readEnd = "</span>";
        String eye = "<i class=\"fa fa-eye fs-16\">&nbsp;</i>";
        String numStart = "<i class=\"num\">";
        String numEnd = "</i>";

        //评论数
        String ml20Start = "<span class=\"ml20\">";
        String ml20End = "</span>";
        String comments = "<i class=\"fa fa-comments fs-16\">&nbsp;</i>";
        String greyStart = "<a href=\"javascript:void(0)\" class=\"num fc-grey\">";
        String greyEnd = "</a>";

        //最终页面
        StringBuilder template = new StringBuilder();

        //根节点
        template.append(sectionStart);

        //若是置顶文章，则加
        if (this.top)
            template.append(top);

        //标题部分
        template.append(h5Start);
        template.append(statusStart);
        template.append(status);//文章状态？自创or转载
        template.append(statusEnd);
        template.append(titleStart);
        template.append(link);//文章内容链接
        template.append(titleCenter);
        template.append(title);//文章标题
        template.append(titleEnd);
        template.append(h5End);

        //日期部分
        template.append(timeStart);
        template.append(dayStart);
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        String day = dayFormat.format(lately);
        day = wipeZero(day);//截取零
        template.append(day);//真正的日期：天
        template.append(dayEnd);
        template.append(monthStart);
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        String month = monthFormat.format(lately);
        month = wipeZero(month);//截取零
        template.append(month);//真正的日期：月
        template.append(monthEnd);
        template.append(yearStart);
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String year = yearFormat.format(lately);
        template.append(year);//真正的日期：年
        template.append(yearEnd);
        template.append(timeEnd);

        //副标题部分
        template.append(contentStart);
        template.append(aStart);
        template.append(link);//文章内容链接
        template.append(aCentre);
        template.append(imgStart);
        template.append(picture);//图片
        template.append(imgEnd);
        template.append(aEnd);
        template.append(assistant);//副标题
        template.append(contentEnd);

        //文章内容（字面意思，不是真正内容）
        template.append(readMoreStart);
        template.append(readMoreLinkStart);
        template.append(link);//文章内容链接
        template.append(readMoreLinkEnd);
        template.append(readMoreEnd);

        //底部信息
        template.append(footerStart);

        //标签信息
        template.append(tagsStart);
        template.append(tagsCenter);
        template.append(tagsAStart);
        if (labels != null) {
            for (String label : labels) {
                template.append(label + " ");//标签内容，可以用多个
            }
        }
        template.append(tagsAEnd);
        template.append(tagsEnd);

        //右边信息
        template.append(frStart);

        //点击量
        template.append(readStart);
        template.append(eye);
        template.append(numStart);
        template.append(flow);//点击量
        template.append(numEnd);
        template.append(readEnd);


        //评论数
        template.append(ml20Start);
        template.append(comments);
        template.append(greyStart);
        template.append(commentSize);//点赞量
        template.append(greyEnd);
        template.append(ml20End);


        template.append(frEnd);

        template.append(footerEnd);
        template.append(sectionEnd);
        return template.toString();
    }


    /**
     * 把天或月前面的零给截取掉
     */
    private String wipeZero(String str) {
        char[] strs = str.toCharArray();
        if (strs[0] == '0') {
            str = strs[1] + "";
        }
        return str;
    }

}
