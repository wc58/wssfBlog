<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/12
  Time: 15:31
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
    <title>文章阅读</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/master.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gloable.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gloable.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog.css"/>
</head>
<body>
<div class="header">
</div>
<header class="gird-header">
    <div class="header-fixed">
        <div class="header-inner">
            <a href="javascript:void(0)" class="header-logo" id="logo">Mr.Yss</a>
            <nav class="nav" id="nav">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/index">首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/blog/list">博客</a></li>
                    <li><a href="message.html">留言</a></li>
                    <li><a href="link.html">友链</a></li>
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
        <div class="col-content" style="width:100%">
            <div class="inner">
                <article class="article-list">
                    <input type="hidden" value="@Model.BlogTypeID" id="blogtypeid"/>
                    <section class="article-item">
                        <aside class="title" style="line-height:1.5;">
                            <h4>${article.title}</h4>
                            <p class="fc-grey fs-14">
                                <small>
                                    作者：<a href="javascript:void(0)" target="_blank"
                                          class="fc-link">${article.author}</a>
                                </small>
                                <small class="ml10">点击量：<i class="readcount">${other.flow}</i></small>
                                <small class="ml10">更新于 <label><f:formatDate value="${article.updateTime}"
                                                                             pattern="yyyy-MM-dd hh:mm:ss"/></label>
                                </small>
                            </p>
                        </aside>
                        <div class="time mt10" style="padding-bottom:0;">
                            <span class="day">${day}</span>
                            <span class="month fs-18">${month}<small
                                    class="fs-14">月</small></span>
                            <span class="year fs-18"><f:formatDate value="${article.updateTime}" pattern="yyyy"/></span>
                        </div>
                        <div class="content artiledetail"
                             style="border-bottom: 1px solid #e1e2e0; padding-bottom: 20px;">
                            ${article.content}
                            <div class="copyright mt20">
                                <p class="f-toe fc-black">
                                    非特殊说明，本文版权归 <a href="javascript:void(0)" target="_blank"
                                                   class="fc-link">${article.author}</a> 所有，转载请注明出处.
                                </p>
                                <p class="f-toe">
                                    本文标题：
                                    <a href="javascript:void(0)" class="r-title">${article.title}</a>
                                </p>
                                <p class="f-toe">
                                    本文网址：
                                    <a href="#">http://127.0.0.1:8080/blog/read/${article.id}</a>
                                </p>
                            </div>
                            <div id="aplayer" style="margin:5px 0"></div>
                            <h6>延伸阅读</h6>
                            <ol class="b-relation">
                                <c:forEach items="${randomArticles}" var="randomArticle">
                                    <li class="f-toe">
                                        <a href="/blog/read/${randomArticle.id}">${randomArticle.title}</a>
                                    </li>
                                </c:forEach>
                            </ol>
                        </div>
                        <div class="bdsharebuttonbox share" data-tag="share_1">
                            <ul>
                                <li class="f-praise"><span><a class="s-praise"></a></span></li>
                                <li class="f-weinxi"><a class="s-weinxi" data-cmd="weixin"></a></li>
                                <li class="f-sina"><a class="s-sina" data-cmd="tsina"></a></li>
                                <li class="f-qq" href="#"><i class="fa fa-qq"></i></li>
                                <li class="f-qzone"><a class="s-qzone" data-cmd="qzone"></a></li>
                            </ul>
                        </div>
                        <div class="f-cb"></div>
                        <div class="mt20 f-fwn fs-24 fc-grey comment"
                             style="border-top: 1px solid #e1e2e0; padding-top: 20px;">
                        </div>
                        <fieldset class="layui-elem-field layui-field-title">
                            <legend>发表评论</legend>
                            <div class="layui-field-box">
                                <div class="leavemessage" style="text-align:initial">
                                    <form class="layui-form blog-editor" action="">
                                        <input type="hidden" name="articleid" id="articleid" value="12">
                                        <div class="layui-form-item">
                                            <textarea name="editorContent" lay-verify="content" id="remarkEditor"
                                                      placeholder="请输入内容" class="layui-textarea layui-hide"></textarea>
                                        </div>
                                        <div class="layui-form-item">
                                            <button class="layui-btn" lay-submit="formLeaveMessage"
                                                    lay-filter="formLeaveMessage">提交留言
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </fieldset>
                        <ul class="blog-comment" id="blog-comment"></ul>
                    </section>
                </article>
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
<script src="${pageContext.request.contextPath}/js/pagecomment.js"></script>
<script>NProgress.start();</script>
<script>
    window.onload = function () {
        NProgress.done();
    };
</script>
</body>
</html>
