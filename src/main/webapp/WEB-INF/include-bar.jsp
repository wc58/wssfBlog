<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/16
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="nav" id="nav">
    <ul>
        <li><a href="/index">首页</a></li>
        <li><a href="/blog/list">博客</a></li>
        <li><a href="/leave/leavePage">吐槽</a></li>
        <li><a href="/link/page">友链</a></li>
        <li><a href="/diary/page">日记</a></li>
        <li><a href="/about">关于</a></li>
    </ul>
</nav>
<%--登录位置--%>
<div>
    <c:if test="${user == null}">
        <a onclick="login()" href="javascript:;" id="qq" class="blog-user">
            <i class="fa fa-qq"></i>
        </a>
    </c:if>
    <c:if test="${user != null}">
        <a onclick="logout()" href="javascript:;" class="blog-user">
            <img src="${user.icon}"
                 alt="${user.name}"
                 title="${user.name}"/>
        </a>
    </c:if>
</div>
<script>
    function login() {
        location.href = '/user/loginByQQ?url=' + location.pathname
    }

    function logout() {
        $.ajax({
            type: 'get',
            url: '/user/logout',
            dataType: 'json',
            success: function (data) {
                if (data.code === 1000) {
                    layer.msg("退出成功")
                    location.reload();
                } else {
                    layer.msg("退出失败")
                }
            }
        })
    }

</script>