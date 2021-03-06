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
    <link rel="icon" href="${pageContext.request.contextPath}/logo.png" type="image/x-icon"/>
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.png" type="image/x-icon"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/master.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gloable.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gloable.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/message.css"/>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/highlight/styles/monokai-sublime.css"/>--%>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/highlight/styles/a11y-dark.css">--%>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/highlight/styles/atelier-dune-light.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/highlight/styles/atelier-forest-light.css">
    <style>
        h1 {
            margin: 20px 0;
            border-left: 4px solid #89c30d;
            min-height: 26px;
            line-height: 30px;
            padding: 5px 20px;
            background-color: #f8f9f7;
            font-size: 22px;
            font-weight: 500;
            color: #6b6d69;
            text-shadow: 0 1px 0 rgba(255, 255, 255, .5);
            clear: both;
        }

        h2 {
            margin: 10px 0;
            border-left: 3px solid #89c30d;
            min-height: 26px;
            line-height: 26px;
            padding: 5px 20px;
            background-color: #f8f9f7;
            font-size: 18px;
            font-weight: 430;
            color: #585957;
            text-shadow: 0 1px 0 rgba(255, 255, 255, .5);
            clear: both;
        }

        h3 {
            margin: 6px 0;
            border-left: 2px solid #89c30d;
            min-height: 10px;
            line-height: 20px;
            padding: 5px 20px;
            background-color: #f8f9f7;
            font-size: 16px;
            font-weight: 400;
            color: #585957;
            text-shadow: 0 1px 0 rgba(255, 255, 255, .5);
            clear: both;
        }

        blockquote {
            background-color: #f8f8f8;
            padding: 15px 22.5px;
            font-size: 14px;
            border-radius: 2px;
            color: #999;
        }
    </style>
</head>
<body>
<div class="header">
</div>
<header class="gird-header">
    <div class="header-fixed">
        <div class="header-inner">
            <a href="/blog/list" class="header-logo" id="logo">Chao.Sir</a>
            <%@ include file="include-bar.jsp" %>
            <%-- <a href="#" class="blog-user">
                 <i class="fa fa-qq"></i>
             </a>--%>
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
                            <%--标题--%>
                            <h4>${article.title}</h4>
                            <p class="fc-grey fs-14">
                                <%--作者--%>
                                <small>
                                    作者：<a href="javascript:void(0)" target="_blank"
                                          class="fc-link">${article.author}</a>
                                </small>
                                <%--点击量--%>
                                <small class="ml10">点击量：<i
                                        class="readcount">${other.flow}</i></small>
                                <%--更新时间--%>
                                <small class="ml10">更新于 <label><f:formatDate value="${article.updateTime}"
                                                                             pattern="yyyy-MM-dd HH:mm:ss"/></label>
                                </small>
                            </p>
                        </aside>
                        <%--时间--%>
                        <div class="time mt10" style="padding-bottom:0;">
                            <span class="day">${day}</span>
                            <span class="month fs-18">${month}<small
                                    class="fs-14">月</small></span>
                            <span class="year fs-18"><f:formatDate value="${article.updateTime}" pattern="yyyy"/></span>
                        </div>
                        <div class="content artiledetail"
                             style="border-bottom: 1px solid #e1e2e0; padding-bottom: 20px;">
                            <%--正文--%>
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
                                    <a href="#">https://chao58.top/blog/read/${article.id}</a>
                                </p>
                            </div>
                            <div id="aplayer" style="margin:5px 0"></div>
                            <%--随机推荐--%>
                            <h6>延伸阅读</h6>
                            <ol class="b-relation">
                                <c:forEach items="${randomArticles}" var="randomArticle">
                                    <li class="f-toe">
                                        <a href="/blog/read/${randomArticle.id}">${randomArticle.title}</a>
                                    </li>
                                </c:forEach>
                            </ol>
                        </div>
                        <fieldset class="layui-elem-field layui-field-title">
                            <legend>发表评论</legend>
                            <div class="textarea-wrap message" id="textarea-wrap">
                                <div>
                                    <textarea id="tuArea" required lay-verify="required" placeholder="请输入要评论的话"
                                              class="layui-textarea"></textarea>
                                </div>
                                <br/>
                                <div>
                                    <input type="button" style="border: none ">
                                    <input id="tuButton" type="button" style="float: right" class="layui-btn"
                                           value="提交">
                                </div>
                            </div>

                        </fieldset>
                        <%--评论的页面--%>

                    </section>
                    <div class="mt20">
                        <ul class="message-list" id="message-list">
                            <c:forEach items="${comments}" var="comment">
                                <li class="zoomIn article">
                                    <div class="comment-parent">
                                        <a name="remark-1"></a>
                                            <%--头像--%>
                                        <img src="${comment.user.icon}"/>
                                        <div class="info">
                                                <%--昵称--%>
                                            <span class="username">${comment.user.name}</span>
                                            <c:if test="${comment.user.thirdId=='8E1544B0D015EC98612B39DD5D5B90B0'}">
                                                <span class="layui-badge">博主</span>
                                                &nbsp;
                                            </c:if>
                                        </div>
                                        <div class="comment-content">
                                                <%--内容--%>
                                            <span style="white-space: pre-line;"> ${comment.comment.content}</span>
                                        </div>
                                        <p class="info info-footer">
                                                <%--日期--%>
                                            <span class="comment-time" style="color: gray"><f:formatDate
                                                    value="${comment.comment.createTime}"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                            <a href="javascript:;" class="btn-reply"
                                               data-targetid="${comment.comment.id}"
                                               data-targetname="${comment.user.name}">回复</a>
                                        </p>
                                    </div>

                                    <c:forEach items="${comment.fullComments}" var="childComment" varStatus="num">
                                        <%--当子评论至少有个一个时才显示（第一个一般为“ERROR20020508”，所以从第2开始算）--%>
                                        <c:if test="${num.index == '0' and comment.fullComments.size() > 1}">
                                            <hr class="layui-bg-green">
                                        </c:if>
                                        <%--当每遇到一个“ERROR20020508”，说明该子评论已经到最后一个了--%>
                                        <c:if test="${childComment.user.name eq 'ERROR20020508' and num.index ne (comment.fullComments.size()-1)}">
                                            <hr class="layui-bg-green">
                                        </c:if>
                                        <%--当不是“ERROR20020508”时，将取出数据--%>
                                        <c:if test="${childComment.user.name ne 'ERROR20020508'}">
                                            <%--当不是第一，且上一个也不是“ERROR20020508”时，才加--%>
                                            <c:if test="${num.index != '0' and comment.fullComments.get(num.index-1).user.name ne 'ERROR20020508'}">
                                                <hr/>
                                            </c:if>
                                            <div class="comment-child">
                                                <a name="reply-1"></a>
                                                    <%--头像--%>
                                                <img src="${childComment.user.icon}">
                                                <div class="info">
                                                        <%--回复人--%>
                                                    <div>
                                                            <span class="username"
                                                                  style="color: #2E2D3C">${childComment.user.name}</span>
                                                        <c:if test="${childComment.user.thirdId=='8E1544B0D015EC98612B39DD5D5B90B0'}">
                                                            <span class="layui-badge"
                                                                  style="height: 16px;font-size: 9px;">博主</span>
                                                            &nbsp;
                                                        </c:if>
                                                        <span style="padding-right:0;margin-left:-5px;"><i
                                                                style="color: gray">回复</i></span>
                                                            <%--被回复人--%>
                                                        <span class="username"><i>${childComment.comment.parentName}：</i></span>
                                                    </div>
                                                        <%--回复内容--%>
                                                    <span style="white-space: pre-line;"> ${childComment.comment.content}</span>
                                                </div>

                                                <p class="info">
                                    <span class="comment-time" style="color: gray"><f:formatDate
                                            value="${childComment.comment.createTime}"
                                            pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                                        <%--只有是当前子评论最后一个时才加回复，要不然回复列表会乱序，如果你不喜欢，可以把if判断去掉即可--%>
                                                        <%--                                        <c:if test="${leave.fullLeaves.get(num.index+1).user.name eq 'ERROR20020508'}">--%>
                                                        <%--被回复人的id--%>
                                                    <a href="javascript:;" class="btn-reply"
                                                       data-targetid="${childComment.comment.id}"
                                                       data-targetname="${childComment.user.name}">回复</a>
                                                        <%--                                        </c:if>--%>
                                                </p>


                                            </div>
                                        </c:if>
                                    </c:forEach>
                                    <div class="replycontainer layui-hide">
                                        <div class="layui-form">
                                            <input id="pid" type="hidden" name="targetUserId" value="0">
                                            <div class="layui-form-item">
                                <textarea id="aaa" name="replyContent" lay-verify="replyContent" placeholder="请输入回复内容"
                                          class="layui-textarea" style="min-height:80px;"></textarea>
                                            </div>
                                            <div class="layui-form-item">
                                                <button class="layui-btn layui-btn-xs" lay-submit="formReply"
                                                        lay-filter="formReply">
                                                    提交
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </article>
            </div>
        </div>
    </div>
</div>
<%@ include file="include-footer.jsp" %>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/yss/gloable.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/nprogress.js"></script>
<script src="${pageContext.request.contextPath}/js/pagecomment.js"></script>
<script src="${pageContext.request.contextPath}/highlight/highlight.pack.js"></script>
<script>NProgress.start();</script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script>
    hljs.initHighlightingOnLoad();
    window.onload = function () {
        NProgress.done();
    };
    layui.use('element', function () {
        var $ = layui.$;
        var articleId = '${article.id}'
        $("#tuButton").click(function () {
            var txt = $("#tuArea").val();
            var userId = '${sessionScope.user.id}';
            if (userId === '') {
                layer.msg("请先登录")
                return;
            }
            if (txt === '') {
                layer.msg("内容不能为空");
                return;
            }
            $.ajax({
                type: 'post',
                url: "/comment/reply",
                data: {
                    'articleId': articleId,
                    'userId': userId,
                    'content': txt
                },
                success: function (data) {
                    if (data.code === 1000) {
                        //刷新文章
                        history.go(0);
                    } else {
                        layer.msg("添加失败，请重试")
                    }
                },
                typeDate: 'json'
            })
        })

        layui.use(['element', 'jquery', 'form', 'layedit', 'flow'], function () {
            var form = layui.form;
            var $ = layui.jquery;
            var layedit = layui.layedit;
            var pid;
            //留言的编辑器
            var editIndex = layedit.build('remarkEditor', {
                height: 150,
                tool: [],
            });
            //留言的编辑器的验证
            form.verify({
                replyContent: function (value) {
                    var userId = '${sessionScope.user.id}';
                    if (userId === '') {
                        layer.msg("请先登录")
                        return;
                    }
                    if (value === '') {
                        layer.msg("内容不能为空");
                        return
                    }
                    $.ajax({
                        type: 'post',
                        url: "/comment/reply",
                        data: {
                            'articleId': articleId,
                            'userId': userId,
                            'content': value,
                            'pid': pid
                        },
                        success: function (data) {
                            if (data.code === 1000) {
                                // layer.msg("添加成功")
                                history.go(0);
                            } else {
                                layer.msg("添加失败，请重试")
                            }
                        },
                        typeDate: 'json'
                    })
                }
            });
            //回复按钮点击事件
            $('#message-list').on('click', '.btn-reply', function () {
                var targetId = $(this).data('targetid')
                    , targetName = $(this).data('targetname')
                    , $container = $(this).parent('p').parent().siblings('.replycontainer');
                if ($(this).text() == '回复') {
                    $container.find('textarea').attr('placeholder', '回复【' + targetName + '】');
                    $container.removeClass('layui-hide');
                    pid = targetId;
                    $container.find('input[name="targetUserId"]').val(targetId);
                    $(this).parents('.message-list li').find('.btn-reply').text('回复');
                    $(this).text('收起');
                } else {
                    $container.addClass('layui-hide');
                    $container.find('input[name="targetUserId"]').val(0);
                    $(this).text('回复');
                }
            });

        });

    })

</script>
</body>
</html>
