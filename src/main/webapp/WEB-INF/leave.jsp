<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/13
  Time: 20:36
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
    <title>吐槽板</title>
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.png" type="image/x-icon"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/master.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gloable.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gloable.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/message.css"/>
</head>
<body>
<div class="header">
</div>
<header class="gird-header">
    <div class="header-fixed">
        <div class="header-inner">
            <a href="/blog/list" class="header-logo" id="logo">Mr.Yss</a>
            <nav class="nav" id="nav">
                <ul>
                    <li><a href="/index">首页</a></li>
                    <li><a href="/blog/list">博客</a></li>
                    <li><a href="/leave/leavePage">吐槽</a></li>
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
        <div class="container-inner">
            <section class="msg-remark">
                <h1>吐槽板</h1>
                <p>
                    把你想吐糟<span style="color: #00F7DE">超哥</span>的话留在下面
                </p>
            </section>
            <div class="textarea-wrap message" id="textarea-wrap">
                <div>
                <textarea name="" required lay-verify="required" placeholder="请输入要吐槽的话"
                          class="layui-textarea"></textarea>
                </div>
                <br/>
                <div>
                    <input type="button" style="border: none ">
                    <input type="button"  style="float: right" class="layui-btn" value="提交">
                </div>
            </div>
        </div>
        <div class="f-cb"></div>
        <div class="mt20">
            <ul class="message-list" id="message-list">
                <c:forEach items="${leaves}" var="leave">
                    <li class="zoomIn article">
                        <div class="comment-parent">
                            <a name="remark-1"></a>
                                <%--头像--%>
                            <img src="${leave.user.icon}"/>
                            <div class="info">
                                    <%--昵称--%>
                                <span class="username">${leave.user.name}</span>
                            </div>
                            <div class="comment-content">
                                    <%--内容--%>
                                    ${leave.leave.content}
                            </div>
                            <p class="info info-footer">
                                    <%--日期--%>
                                <span class="comment-time"><f:formatDate value="${leave.leave.createTime}"
                                                                         pattern="yyyy-MM-dd hh:mm:ss"/></span>
                                <input type="hidden" id="pid" value="${childLeave.user.id}">
                                <a href="javascript:;" class="btn-reply" data-targetid="1"
                                   data-targetname="${leave.user.name}">回复</a>
                            </p>
                        </div>

                        <c:forEach items="${leave.fullLeaves}" var="childLeave">
                            <hr/>
                            <div class="comment-child">
                                <a name="reply-1"></a>
                                    <%--头像--%>
                                <img src="${childLeave.user.icon}">
                                <div class="info">
                                        <%--回复人--%>
                                    <span class="username">${childLeave.user.name}</span>
                                    <span style="padding-right:0;margin-left:-5px;">回复</span>
                                        <%--被回复人--%>
                                    <span class="username">${childLeave.leave.parentName}</span>
                                        <%--回复内容--%>
                                    <span>${childLeave.leave.content}</span>
                                </div>
                                <p class="info">
                                    <span class="comment-time"><f:formatDate value="${childLeave.leave.createTime}"
                                                                             pattern="yyyy-MM-dd hh:mm:ss"/></span>
                                        <%--被回复人的id--%>
                                    <input type="hidden" id="pid" value="${childLeave.user.id}">
                                    <a href="javascript:;" class="btn-reply" data-targetid="2"
                                       data-targetname="${childLeave.user.name}">回复</a>
                                </p>
                            </div>
                        </c:forEach>


                        <div class="replycontainer layui-hide">
                            <form class="layui-form" action="">
                                <input type="hidden" name="remarkId" value="1">
                                <input type="hidden" name="targetUserId" value="0">
                                <div class="layui-form-item">
                                <textarea name="replyContent" lay-verify="replyContent" placeholder="请输入回复内容"
                                          class="layui-textarea" style="min-height:80px;"></textarea>
                                </div>
                                <div class="layui-form-item">
                                    <button class="layui-btn layui-btn-xs" lay-submit="formReply"
                                            lay-filter="formReply">
                                        提交
                                    </button>
                                </div>
                            </form>
                        </div>
                    </li>
                </c:forEach>
            </ul>
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
<script src="${pageContext.request.contextPath}/js/pagemessage.js"></script>
<script>NProgress.start();</script>
<script>
    window.onload = function () {
        NProgress.done();
    };

    layui.use('element', function () {
        var $ = layui.$;
        $("#topButton").click(function () {
            var txt = $("remarkEditor").val();
            alert(txt);
        })
    })


</script>
</body>
</html>
