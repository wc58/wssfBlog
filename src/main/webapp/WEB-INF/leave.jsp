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
    <link rel="icon" href="${pageContext.request.contextPath}/logo.png" type="image/x-icon"/>
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
            <a href="/blog/list" class="header-logo" id="logo">Chao.Sir</a>
            <%@ include file="include-bar.jsp" %>
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
                    把你想吐糟<span style="color: green">超哥</span>的话留在下面
                </p>
            </section>
            <div class="textarea-wrap message" id="textarea-wrap">
                <div>
                <textarea id="tuArea" required lay-verify="required" placeholder="请输入要吐槽的话"
                          class="layui-textarea"></textarea>
                </div>
                <br/>
                <div>
                    <input type="button" style="border: none ">
                    <input id="tuButton" type="button" style="float: right" class="layui-btn" value="提交">
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
                                <c:if test="${leave.user.thirdId=='8E1544B0D015EC98612B39DD5D5B90B0'}">
                                    <span class="layui-badge">博主</span>
                                    &nbsp;
                                </c:if>
                            </div>
                            <div class="comment-content">
                                    <%--内容--%>
                                <span style="white-space: pre-line;">${leave.leave.content}</>
                            </div>
                            <p class="info info-footer">
                                    <%--日期--%>
                                <span class="comment-time" style="color: gray"><f:formatDate
                                        value="${leave.leave.createTime}"
                                        pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                <a href="javascript:;" class="btn-reply" data-targetid="${leave.leave.id}"
                                   data-targetname="${leave.user.name}">回复</a>
                            </p>
                        </div>

                        <c:forEach items="${leave.fullLeaves}" var="childLeave" varStatus="num">
                            <%--当子评论至少有个一个时才显示（第一个一般为“ERROR20020508”，所以从第2开始算）--%>
                            <c:if test="${num.index == '0' and leave.fullLeaves.size() > 1}">
                                <hr class="layui-bg-green">
                            </c:if>
                            <%--当每遇到一个“ERROR20020508”，说明该子评论已经到最后一个了--%>
                            <c:if test="${childLeave.user.name eq 'ERROR20020508' and num.index ne (leave.fullLeaves.size()-1)}">
                                <hr class="layui-bg-green">
                            </c:if>
                            <%--当不是“ERROR20020508”时，将取出数据--%>
                            <c:if test="${childLeave.user.name ne 'ERROR20020508'}">
                                <%--当不是第一，且上一个也不是“ERROR20020508”时，才加--%>
                                <c:if test="${num.index != '0' and leave.fullLeaves.get(num.index-1).user.name ne 'ERROR20020508'}">
                                    <hr/>
                                </c:if>
                                <div class="comment-child">
                                    <a name="reply-1"></a>
                                        <%--头像--%>
                                    <img src="${childLeave.user.icon}">
                                    <div class="info">
                                            <%--回复人--%>
                                        <div>
                                            <span class="username" style="color: #2E2D3C">${childLeave.user.name}</span>
                                            <c:if test="${childLeave.user.thirdId=='8E1544B0D015EC98612B39DD5D5B90B0'}">
                                                <span class="layui-badge" style="height: 16px;font-size: 9px;">博主</span>
                                                &nbsp;
                                            </c:if>
                                            <span style="padding-right:0;margin-left:-5px;"><i
                                                    style="color: gray">回复</i></span>
                                                <%--被回复人--%>
                                            <span class="username"><i>${childLeave.leave.parentName}：</i></span>
                                        </div>

                                            <%--回复内容--%>
                                        <span style="white-space: pre-line;">${childLeave.leave.content}</span>
                                    </div>

                                    <p class="info">
                                    <span class="comment-time" style="color: gray"><f:formatDate
                                            value="${childLeave.leave.createTime}"
                                            pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                            <%--只有是当前子评论最后一个时才加回复，要不然回复列表会乱序，如果你不喜欢，可以把if判断去掉即可--%>
                                            <%--                                        <c:if test="${leave.fullLeaves.get(num.index+1).user.name eq 'ERROR20020508'}">--%>
                                            <%--被回复人的id--%>
                                        <a href="javascript:;" class="btn-reply"
                                           data-targetid="${childLeave.leave.id}"
                                           data-targetname="${childLeave.user.name}">回复</a>
                                            <%--                                        </c:if>--%>
                                    </p>


                                </div>
                            </c:if>
                        </c:forEach>


                        <div id="reply" class="replycontainer layui-hide">
                            <div class="layui-form">
                                    <%--                                <input type="hidden" name="remarkId" value="1">--%>
                                <input type="hidden" name="targetUserId" value="0">
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
    </div>
</div>
<%@ include file="include-footer.jsp" %>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/yss/gloable.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/nprogress.js"></script>
<script>NProgress.start();</script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script>
    window.onload = function () {
        NProgress.done();
    };


    layui.use('element', function () {
        var $ = layui.$;
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
                url: "/leave/topReply",
                data: {
                    'userId': userId,
                    'content': txt
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
                        url: "/leave/topReply",
                        data: {
                            'userId': userId,
                            'content': value,
                            'pid': pid
                        },
                        success: function (data) {
                            if (data.code === 1000) {
                                // layer.msg("添加成功")
                                // location.href = "/leave/leavePage"
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
