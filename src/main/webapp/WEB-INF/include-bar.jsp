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
<a href="#" onclick="login()" class="blog-user">
    <i class="fa fa-qq"></i>
</a>
<script>
    function login() {
        alert("由于网站在进行备案中，暂时不能支持登录")
    }
</script>