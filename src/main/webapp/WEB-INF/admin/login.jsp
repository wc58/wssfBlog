<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/17
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>后台登录-X-admin2.2</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <%@ include file="staticSource.jsp" %>
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">往事随风 - 后台管理系统</div>
    <div id="darkbannerwrap"></div>

    <input id="username" name="username" placeholder="用户名" type="text" class="layui-input">
    <hr class="hr15">
    <input id="password" name="password" placeholder="密码" type="password" class="layui-input">
    <hr class="hr15">
    <input id="login" value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
    <hr class="hr20">
</div>

<script>
    layui.use('layer', function () {
        var layer = layui.layer;
        var $ = layui.$;
        $("#login").click(function () {
            var username = $("#username").val();
            var password = $("#password").val();
            if (username == '' || password == '') {
                layer.msg("账号和密码不能为空！")
                return;
            }
            $.ajax({
                type: 'post',
                url: '/admin/login',
                success: function (data) {
                    if (data.code == 1000) {
                        location.href = '/admin/index'
                    } else {
                        layer.msg("账号或密码不正确！")
                    }
                },
                data: {
                    'username': username,
                    'password': password
                },
                dataType: 'json'
            })

        })
    });
</script>
<!-- 底部结束 -->
</body>
</html>