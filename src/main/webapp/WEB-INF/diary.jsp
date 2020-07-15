<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/15
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
s
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width"/>
    <meta name="author" content="www.yanshisan.cn"/>
    <meta name="robots" content="all"/>
    <title>日记</title>
    <link rel="icon" href="${pageContext.request.contextPath}/logo.png" type="image/x-icon"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/master.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gloable.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gloable.css"/>
    <link rel="stylesheet" href="../css/timeline.css"/>
</head>
<body>
<div class="header">
</div>
<header class="gird-header">
    <div class="header-fixed">
        <div class="header-inner">
            <a href="/blog/list" class="header-logo" id="logo">Chao.Sir</a>
            <nav class="nav" id="nav">
                <ul>
                    <li><a href="/index">首页</a></li>
                    <li><a href="/blog/list">博客</a></li>
                    <li><a href="/leave/leavePage">吐槽</a></li>
                    <li><a href="/link/page">友链</a></li>
                    <li><a href="/diary/page">日记</a></li>
                </ul>
            </nav>
            <a href="#" class="blog-user">
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
        <div class="timeline-box shadow">
            <div class="timeline-main">
                <h1><i class="fa fa-clock-o"></i>日记</h1>
                <div class="timeline-line"></div>


                <c:forEach items="${diaries}" var="diary">
                    <div class="timeline-year">
                        <h2><a class="yearToggle">${diary.key} 年</a><i class="fa fa-caret-down fa-fw"></i></h2>
                        <div class="timeline-month">
                            <ul>
                                <c:forEach items="${diary.value}" var="dia">
                                    <li>
                                        <div class="h4 animated fadeInLeft">
                                            <p class="date"><f:formatDate value="${dia.createTime}"
                                                                          pattern="MM月dd日 hh:mm"/></p>
                                        </div>
                                        <p class="dot-circle animated "><i class="fa fa-dot-circle-o"></i></p>
                                        <div class="content animated fadeInRight">${dia.content}</div>
                                        <div class="clear"></div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </c:forEach>
                <h1 style="padding-top:4px;padding-bottom:2px;margin-top:40px;"><i class="fa fa-hourglass-end"></i>THE
                    END</h1>
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
                    <a href="#" class="qq" target="_blank"><i class="fa fa-qq"></i></a>
                    <a href="#" class="email" target="_blank"><i class="fa fa-envelope"></i></a>
                    <a href="javascript:void(0)" class="weixin"><i class="fa fa-weixin"></i></a>
                </div>
                <p class="mt05">
                    Copyright &copy; 2018-2018 燕十三 All Rights Reserved V.3.1.3 蜀ICP备18008600号
                </p>
            </div>
        </div>
    </div>
</footer>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/yss/gloable.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/nprogress.js"></script>
<script>NProgress.start();</script>
<script src="../js/pagediary.js"></script>
<script>
    window.onload = function () {
    };
</script>
</body>
</html>
