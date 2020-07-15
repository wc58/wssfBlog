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
    <title>文章列表</title>
    <link rel="icon" href="${pageContext.request.contextPath}/logo.png" type="image/x-icon"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/master.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gloable.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gloable.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="header">
</div>
<header class="gird-header">
    <div class="header-fixed">
        <div class="header-inner">
            <a href="/" class="header-logo" id="logo">Chao.Sir</a>
            <nav class="nav" id="nav">
                <ul>
                    <li><a href="/index">首页</a></li>
                    <li><a href="/blog/list">博客</a></li>
                    <li><a href="/leave/leavePage">吐槽</a></li>
                    <li><a href="/link/page">友链</a></li>
                </ul>
            </nav>
            <%-- <a href="/User/QQLogin" class="blog-user layui-anim-scale">
                 <i class="fa fa-qq"></i>
             </a>--%>
            <c:if test="${sessionScope.user !=null}">
                <img class="blog-user layui-anim-scale" src="${sessionScope.user.icon}" style="height: 50px">
            </c:if>
            <c:if test="${sessionScope.user ==null}">
                <a id="login" href="#" class="blog-user layui-anim-scale">
                    登录
                </a>
            </c:if>


            <a class="phone-menu ">
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

            <span class="searchphone d5 article-list">
                 <br class="searchphone"/>
                <form>
                    <input id="searchtxtphone" onkeydown="return disableTextSubmit(event)"
                           value="${condition != null&& !condition.equals("")?condition:''}" type="text"
                           placeholder="搜索从这里开始...">
                    <button id="searchbtnphone" class="layui-anim-rotate" type="button"></button>
                </form>
            </span>
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
                            <input type="text" id="searchtxt"
                                   value="${condition != null&& !condition.equals("")?condition:''}"
                                   placeholder="输入关键字搜索"/>
                            <span class="search-icon">
					                <i id="searchbtn" class="fa fa-search"></i>
					            </span>
                        </label>
                        <ul class="search-result"></ul>
                    </div>
                    <ul class="category mt20" id="category">
                        <li data-index="0" class="slider"></li>
                        <li data-index="1"><a href="/blog/list">全部文章</a></li>
                        <%--标签--%>
                        <c:forEach items="${labels}" var="label" varStatus="i">
                            <li data-index="${i.count+1}"><a href="/blog/list?sortId=${label.id}">${label.name}&nbsp;&nbsp;&nbsp;&nbsp;<i
                                    style="color:ForestGreen">(${label.size})</i></a></li>
                        </c:forEach>
                    </ul>
                </div>

                <!--右边悬浮 平板或手机设备显示-->
                <div class="category-toggle"><i class="layui-icon">&#xe603;</i></div>
                <div class="article-category">
                    <%--标签--%>
                    <div class="article-category-title">标签导航</div>
                    <a href="/blog/list">全部文章</a>
                    <c:forEach items="${labels}" var="label">
                        <a href="/blog/list?sortId=${label.id}">${label.name}&nbsp;&nbsp;&nbsp;&nbsp;<i>(${label.size})</i></a>
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
                                <li><a href="/blog/read/${hotArticle.id}">${hotArticle.title}</a></li>
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
                                <li><a href="/blog/read/${topArticle.id}">${topArticle.title}</a></li>
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
                    <a href="http://wpa.qq.com/msgrd?v=3&uin=2258354832&site=qq&menu=yes" class="qq" target="_blank"
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

<form class="layui-form" id="test" style="display:none">
    <br/>
    <br/>
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input id="username" type="text" name="title" required lay-verify="required" placeholder="用户名"
                   autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码框</label>
        <div class="layui-input-inline">
            <input id="password" type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                   autocomplete="off"
                   class="layui-input">
        </div>
    </div>
</form>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/yss/gloable.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/nprogress.js"></script>
<script>NProgress.start();</script>
<script src="${pageContext.request.contextPath}/js/yss/article.js"></script>
<script>

    layui.use('layer', function () {
        var layer = layui.layer;
        var $ = layui.$

        $("#login").click(function () {
            layer.open({
                type: 1,
                area: ['400px', '300px'],
                title: '登录'
                , content: $("#test"),
                shade: 0,
                btn: ['提交', '重置']
                , btn1: function (index, layero) {
                    let username = $("#username").val();
                    let password = $("#password").val();
                    $.ajax({
                        type: 'post',
                        url: "/user/testLogin",
                        data: {
                            "thirdId": username,
                            "email": password
                        },
                        success: function (data) {
                            if (data.code === 1000) {
                                layer.msg("登录成功");
                                location.reload();
                                return true;
                            } else {
                                layer.msg("登录失败");
                            }
                            return false;
                        },
                        dataType: "json"
                    })
                },
                btn2: function (index, layero) {
                    $("#username").val("");
                    $("#password").val("");
                    return false;
                },
                cancel: function (layero, index) {
                    layer.closeAll();
                }

            });
        })

    });

    layui.use('element', function () {
        var element = layui.element;
        var $ = layui.$
        $("#searchbtn").click(function () {
            var condition = $("#searchtxt").val();
            location.href = "/blog/list?condition=" + condition
        })

        $("#searchbtnphone").click(function () {
            var conditionphone = $("#searchtxtphone").val();
            location.href = "/blog/list?condition=" + conditionphone
        })

    });

    function disableTextSubmit(e) {
        if (e.keyCode == 13) {
            return false;
        }
    }

    /*加载文章*/
    layui.use('flow', function () {
        var $ = layui.jquery;
        var flow = layui.flow;
        flow.load({
            elem: '#LAY_bloglist',
            isAuto: false,
            done: function (page, next) {
                $.ajax({
                    type: 'post',
                    url: 'article?page=' + page,
                    success: function (data) {
                        next(data.articles, page < data.pageTotal);
                    },
                    error: function () {
                        layer.msg('服务器出现了错误，请联系管理员，谢谢。<br/>QQ：2258354832<br/>email：2258354832@qq.com', {
                            icon: 2,
                            time: 4000
                        }, function (index) {
                            location.href = "${pageContext.request.contextPath}/"
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
