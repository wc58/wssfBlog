<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/17
  Time: 20:32
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
    <script>
        // 是否开启刷新记忆tab功能
        var is_remember = false;
    </script>
</head>
<body class="index">
<!-- 顶部开始 -->
<div class="container">
    <div class="logo">
        <a href="/admin/index">往事随风</a></div>
    <div class="left_open">
        <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
    </div>
    <ul class="layui-nav left fast-add" lay-filter="">
        <!--<li class="layui-nav-item">
            <a href="javascript:;">+新增</a>

            <dl class="layui-nav-child">
                &lt;!&ndash; 二级菜单 &ndash;&gt;
                <dd>
                    <a onclick="xadmin.open('最大化','http://www.baidu.com','','',true)">
                        <i class="iconfont">&#xe6a2;</i>弹出最大化</a></dd>
                <dd>
                    <a onclick="xadmin.open('弹出自动宽高','http://www.baidu.com')">
                        <i class="iconfont">&#xe6a8;</i>弹出自动宽高</a></dd>
                <dd>
                    <a onclick="xadmin.open('弹出指定宽高','http://www.baidu.com',500,300)">
                        <i class="iconfont">&#xe6a8;</i>弹出指定宽高</a></dd>
                <dd>
                    <a onclick="xadmin.add_tab('在tab打开','member-list.html')">
                        <i class="iconfont">&#xe6b8;</i>在tab打开</a></dd>
                <dd>
                    <a onclick="xadmin.add_tab('在tab打开刷新','member-del.html',true)">
                        <i class="iconfont">&#xe6b8;</i>在tab打开刷新</a></dd>
            </dl>
        </li>-->
        <li class="layui-nav-item">
            <a href="javascript:;" onclick="xadmin.open('发布文章','writeBlog.html')">发布文章</a>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:;" onclick="xadmin.open('最大化','http://localhost:8080/',1100,600)">写写日记</a>
        </li>
    </ul>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">${sessionScope.admin.name}</a>
            <dl class="layui-nav-child">
                <dd>
                    <a href="/admin/logout">退出</a>
                </dd>
            </dl>
        </li>
        <li class="layui-nav-item to-index">
            <a href="javascript:;" onclick="xadmin.open('最大化','http://localhost:8080/')">前台首页</a></li>
    </ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="文章管理">&#xe6b8;</i>
                    <cite>文章管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('添加文章','/admin/writeBlog')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加文章</cite></a>
                    </li>

                    <li>
                        <a onclick="xadmin.add_tab('普通文章','/admin/articleCommList',true)">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>普通文章</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('置顶文章','/admin/articleTopList',true)">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>置顶文章</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('删除文章','/admin/articleDelList',true)">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>删除文章</cite></a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="评论管理">&#xe723;</i>
                    <cite>评论管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('普通评论','/admin/commentCommList',true)">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>普通评论</cite></a>
                    </li>

                    <li>
                        <a onclick="xadmin.add_tab('删除评论','/admin/commentDelList',true)">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>删除评论</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('普通吐槽','/admin/leaveCommList',true)">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>普通吐槽</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('删除吐槽','/admin/leaveDelList',true)">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>删除吐槽</cite></a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="其他管理">&#xe723;</i>
                    <cite>其他管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('标签管理','/admin/labelList')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>标签管理</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('友链管理','/admin/linkList')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>友链管理</cite></a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="其他管理">&#xe723;</i>
                    <cite>人员管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('标签管理','/admin/userList')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>普通用户管理</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('友链管理','/admin/linkList')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>管理员管理</cite></a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home">
                <i class="layui-icon">&#xe68e;</i>我的桌面
            </li>
        </ul>
        <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
            <dl>
                <dd data-type="this">关闭当前</dd>
                <dd data-type="other">关闭其它</dd>
                <dd data-type="all">关闭全部</dd>
            </dl>
        </div>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='/admin/desk' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
        <div id="tab_show"></div>
    </div>
</div>
<div class="page-content-bg"></div>
<style id="theme_style"></style>
</body>

</html>
