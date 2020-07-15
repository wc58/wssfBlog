<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/10
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-Hans-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width"/>
    <title>往事随风</title>
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.png" type="image/x-icon" />
    <link href="${pageContext.request.contextPath}/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/index_style.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet" type="text/css">
</head>
<style>
    /*.phone{display: none;}*/
    @media (max-width: 768px) {
        .phone {
            display: none;
        }
    }
    .imgCa {
        pointer-events: none;
    }
</style>
<body>
<div id="menu" class="hover_animation menu_open" data-mark="false">
    <span></span>
    <span></span>
    <span></span>
</div>
<div id="navgation" class="navgation navgation_close">
    <ul class="point">
        <li><a href="#">首页</a></li>
        <li><a href="${pageContext.request.contextPath}/blog/list">博客</a></li>
        <li><a href="/leave/leavePage">吐槽</a></li>
        <li><a href="message.html">日记</a></li>
    </ul>
    <div class="logo"><a>Chao.Sir</a></div>
</div>
<div class="section" id="section1">
    <div class="fp-tablecell">
        <div class="page1">
            <div class="nav wow zoomIn" data-wow-duration="2s">
                <h1>往事随风</h1>
                <p>人生如棋，漫漫长路，爱恨情愁几时休...</p>
                <a class="layui-btn " style="margin-top: 20px;background-color:transparent;" href="${pageContext.request.contextPath}/blog/list">GO TO
                    BLOGS&nbsp;&nbsp;<i
                            class="layui-icon layui-icon-release"></i></a>
            </div>
            <a class="next wow  layui-anim-rotate" data-wow-duration="2s" id="next"></a>
        </div>
    </div>
</div>
<div class="section" id="section2">
    <div class="fp-tablecell">
        <div class="page2">
            <div class="warp-box">
                <div class="new-article">
                    <div class="inner wow fadeInDown" data-wow-delay=".3s">
                        <h1>热门文章</h1>
                        <p>
                            世界上没有绝望的处境
                            <br>只有对处境绝望的人
                        </p>
                    </div>
                </div>
                <div class="layui-row">

                    <%--三篇热门文章--%>
                    <c:forEach items="${articles}" var="article" begin="0" end="2">
                        <div class="layui-col-xs12 layui-col-sm4 layui-col-md4  wow layui-anim-scale"
                             style="padding: 0 10px">
                            <div class="single-news">
                                <div class="news-head">
                                        <%--图片--%>
                                    <img class="imgCa" src="${article.picture}"/>
                                    <a href="/blog/read/${article.id}" class="link"><i class="fa fa-link"></i></a>
                                </div>
                                <div class="news-content">
                                    <h4>
                                        <a href="/blog/read/${article.id}">
                                                <%--标题--%>
                                                ${article.title}
                                        </a>
                                    </h4>
                                    <div class="date">
                                            <%--日期--%>
                                        <f:formatDate value="${article.updateTime}" pattern="yyyy年MM月dd日"/>
                                    </div>
                                    <p>
                                            <%--副标题--%>
                                            ${article.assistant}
                                    </p>
                                    <a href="/blog/read/${article.id}" class="btn">
                                        文章内容
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <%--三篇热门文章--%>
                    <c:forEach items="${articles}" var="article" begin="3" end="5">
                        <div class="phone layui-col-xs12 layui-col-sm4 layui-col-md4  wow layui-anim-scale"
                             style="padding: 0 10px">
                            <div class="single-news">
                                <div class="news-head">
                                        <%--图片--%>
                                    <img src="${article.picture}"/>
                                    <a href="${pageContext.request.contextPath}/blog/read/${article.id}" class="link"><i class="fa fa-link"></i></a>
                                </div>
                                <div class="news-content">
                                    <h4>
                                        <a href="${pageContext.request.contextPath}/blog/read/${article.id}">
                                                <%--标题--%>
                                                ${article.title}
                                        </a>
                                    </h4>
                                    <div class="date">
                                            <%--日期--%>
                                        <f:formatDate value="${article.updateTime}" pattern="yyyy年MM月dd日"/>
                                    </div>
                                    <p>
                                            <%--副标题--%>
                                            ${article.assistant}
                                    </p>
                                    <a href="${pageContext.request.contextPath}/blog/read/${article.id}" class="btn">
                                        文章内容
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="section" id="section3">
    <div class="fp-tablecell">
        <div class="page3">
            <div class="warp-box">
                <div class="warp">
                    <div class="inner">
                        <div class="links">
                            <ul>
                                <li class="wow fadeInLeft"><a href="#">关于</a></li>
                                <li class="wow fadeInRight"><a href="#">友情链接</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="section" id="section4">
    <div class="fp-tablecell">
        <div class="page4">
            <div class="warp-box">
                <div class="about">
                    <div class="inner">
                        <h1 class="wow fadeInLeft">技术宅</h1>
                        <p class="wow fadeInRight">
                            爱好音乐，健身，码代码，偶尔瞅瞅电影。闲来无事时，喜欢研究一些小技术捉弄人，嘿嘿嘿。
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<footer class="footer wow fadeInUp" id="contact">
    <div class="footer-top">
        <div class="container">
            <div class="layui-row">
                <div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
                    <div class="single-widget about">
                        <div class="footer-logo">
                            <h2>往事随风</h2>
                        </div>
                        <p>往事已过去，追忆也成空。</p>
                        <p> 因缘来缘聚，何必太在意？</p>
                        <!-- <div class="button">
                             <a href="#" class="btn layui-btn layui-btn-normal">About Me</a>
                         </div>-->
                    </div>
                </div>
                <div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
                    <div class="single-widget">
                        <h2>相关链接</h2>
                        <ul class="social-icon">
                            <li class="active"><a href="#"><i class="fa fa-book"></i>博文</a></li>
                            <li class="active"><a href="#"><i class="fa fa-comments"></i>留言</a></li>
                            <li class="active"><a href="#"><i class="fa fa-share"></i>资源</a></li>
                            <li class="active"><a href="#"><i class="fa fa-snowflake-o"></i>日记</a></li>
                            <li class="active"><a href="#"><i class="fa fa-files-o"></i>归档</a></li>
                        </ul>
                    </div>
                </div>
                <div class="layui-col-xs12 layui-col-sm12 layui-col-md4">
                    <div class="single-widget contact">
                        <h2>联系我</h2>
                        <ul class="list">
                            <li><i class="fa fa-map"></i>地址: 河南省林州市</li>
                            <li><i class="fa fa-qq"></i>Q Q: 2258354832</li>
                            <li><i class="fa fa-envelope"></i>邮箱: 2258354832@qq.com</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="copyright">
        <div class="container">
            <div class="layui-row">
                <div class="layui-col-xs12 layui-col-sm12 layui-col-md12 text-center">
                    <p>Copyright &copy; 2018-2018 往事随风 All Rights Reserved V.3.1.3 蜀ICP备18008600号</p>
                </div>
            </div>
        </div>
    </div>
</footer>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>