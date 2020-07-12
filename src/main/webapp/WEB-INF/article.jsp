<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/10
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width"/>
    <meta name="author" content="www.yanshisan.cn"/>
    <meta name="robots" content="all"/>
    <title>文章</title>
    <link rel="stylesheet" href="../font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../layui/css/layui.css"/>
    <link rel="stylesheet" href="../css/master.css"/>
    <link rel="stylesheet" href="../css/gloable.css"/>
    <link rel="stylesheet" href="../css/nprogress.css"/>
    <link rel="stylesheet" href="../css/blog.css"/>
</head>
<body>
<div class="header">
</div>
<header class="gird-header">
    <div class="header-fixed">
        <div class="header-inner">
            <a href="/" class="header-logo" id="logo">Mr.Yss</a>
            <nav class="nav" id="nav">
                <ul>
                    <li><a href="index">首页</a></li>
                    <li><a href="list">博客</a></li>
                    <li><a href="message">留言</a></li>
                    <li><a href="link.html">友链</a></li>
                </ul>
            </nav>
            <a href="/User/QQLogin" class="blog-user">
                <i class="fa fa-qq"></i>
            </a>
            <a class="phone-menu">
                <i></i>
                <i></i>
                <i></i>
            </a>
        </div>
    </div>
</header>
<div class="doc-container" id="doc-container">
    <div class="container-fixed">
        <div class="col-content">
            <div class="inner">
                <article class="article-list bloglist" id="LAY_bloglist">
                    <%--文章位置--%>
                </article>
            </div>
        </div>
        <div class="col-other">
            <div class="inner">
                <div class="other-item" id="categoryandsearch">
                    <div class="search">
                        <label class="search-wrap">
                            <input type="text" id="searchtxt" placeholder="输入关键字搜索"/>
                            <span class="search-icon">
					                <i class="fa fa-search"></i>
					            </span>
                        </label>
                        <ul class="search-result"></ul>
                    </div>
                    <ul class="category mt20" id="category">
                        <li data-index="0" class="slider"></li>
                        <li data-index="1"><a href="#">全部文章</a></li>
                        <%--标签--%>
                        <c:forEach items="${labels}" var="label" varStatus="i">
                            <li data-index="${i.count+1}"><a href="${label.id}">${label.name}&nbsp;&nbsp;&nbsp;&nbsp;<i
                                    style="color:ForestGreen">(${label.size})</i></a></li>
                        </c:forEach>
                    </ul>
                </div>

                <!--右边悬浮 平板或手机设备显示-->
                <div class="category-toggle"><i class="layui-icon">&#xe603;</i></div>
                <div class="article-category">
                    <%--标签--%>
                    <div class="article-category-title">标签导航</div>
                    <c:forEach items="${labels}" var="label">
                        <a href="${label.id}">${label.name}&nbsp;&nbsp;&nbsp;&nbsp;<i>(${label.size})</i></a>
                    </c:forEach>
                </div>

                <!--遮罩-->
                <div class="blog-mask animated layui-hide"></div>
                <div class="other-item">
                    <h5 class="other-item-title">热门文章</h5>
                    <div class="inner">
                        <ul class="hot-list-article">
                            <%--热门文章--%>
                            <c:forEach items="${hotArticles}" var="hotArticle" begin="0" end="7">
                                <li><a href="${hotArticle.id}">${hotArticle.title}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="other-item">
                    <h5 class="other-item-title">置顶推荐</h5>
                    <div class="inner">
                        <ul class="hot-list-article">
                            <%--置顶文章--%>
                            <c:forEach items="${topArticles}" var="topArticle">
                                <li><a href="${topArticle.id}">${topArticle.title}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<footer class="grid-footer">
    <div class="footer-fixed">
        <div class="copyright">
            <div class="info">
                <div class="contact">
                    <a href="javascript:void(0)" class="github" target="_blank"><i class="fa fa-github"></i></a>
                    <a href="https://wpa.qq.com/msgrd?v=3&uin=930054439&site=qq&menu=yes" class="qq" target="_blank"
                       title="930054439"><i class="fa fa-qq"></i></a>
                    <a href="https://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=gbiysbG0tbWyuMHw8K-i7uw"
                       class="email" target="_blank" title="930054439@qq.com"><i class="fa fa-envelope"></i></a>
                    <a href="javascript:void(0)" class="weixin"><i class="fa fa-weixin"></i></a>
                </div>
                <p class="mt05">
                    Copyright &copy; 2018-2018 燕十三 All Rights Reserved V.1.0.0 蜀ICP备18008600号
                </p>
            </div>
        </div>
    </div>
</footer>
<script src="../layui/layui.js"></script>
<script src="../js/yss/gloable.js"></script>
<script src="../js/plugins/nprogress.js"></script>
<script>NProgress.start();</script>
<script src="../js/yss/article.js"></script>
<script>
    layui.use('element', function () {
        var element = layui.element;

    });

    layui.use('flow', function () {
        var $ = layui.jquery;
        var flow = layui.flow;
        flow.load({
            elem: '#LAY_bloglist',
            isAuto: false,
            done: function (page, next) {
                $.ajax({
                    type: 'post',
                    url: '/article?page=' + page,
                    success: function (data) {
                        next(data.articles, page < data.pageTotal);
                    },
                    error: function (data) {
                        layer.msg('服务器出现了错误，请联系管理员，谢谢。<br/>QQ：2258354832<br/>email：2258354832@qq.com', {icon: 2,time:4000},function(index){
                            location.href = "${pageContext.request.contextPath}/index.html"
                            layer.close(index);
                        });
                    },
                    dataType: 'json'
                })
            }
        });
    });

    window.onload = function () {
        NProgress.done();
    };
</script>
</body>
</html>
