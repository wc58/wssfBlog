<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/17
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>欢迎页面-往事随风</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <%@ include file="staticSource.jsp" %>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <blockquote class="layui-elem-quote">欢迎管理员：
                        <span class="x-red">${sessionScope.admin.name}</span>！当前时间:<span id="cg"></span>
                        <script>setInterval("cg.innerHTML=new Date().toLocaleString()", 1000);</script>
                    </blockquote>
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">数据统计</div>
                <div class="layui-card-body ">
                    <ul class="layui-row layui-col-space10 layui-this x-admin-carousel x-admin-backlog">
                        <li class="layui-col-md2 layui-col-xs6">
                            <a href="javascript:;" class="x-admin-backlog-body">
                                <h3>文章数量</h3>
                                <p>
                                    <cite>${articleSize}</cite></p>
                            </a>
                        </li>
                        <li class="layui-col-md2 layui-col-xs6">
                            <a href="javascript:;" class="x-admin-backlog-body">
                                <h3>用户数量</h3>
                                <p>
                                    <cite>${userSize}</cite></p>
                            </a>
                        </li>
                        <li class="layui-col-md2 layui-col-xs6">
                            <a href="javascript:;" class="x-admin-backlog-body">
                                <h3>评论数量</h3>
                                <p>
                                    <cite>${commentSize}</cite></p>
                            </a>
                        </li>
                        <li class="layui-col-md2 layui-col-xs6">
                            <a href="javascript:;" class="x-admin-backlog-body">
                                <h3>吐槽数量</h3>
                                <p>
                                    <cite>${leaveSize}</cite></p>
                            </a>
                        </li>
                        <li class="layui-col-md2 layui-col-xs6">
                            <a href="javascript:;" class="x-admin-backlog-body">
                                <h3>标签数量</h3>
                                <p>
                                    <cite>${labelSize}</cite></p>
                            </a>
                        </li>
                        <li class="layui-col-md2 layui-col-xs6 ">
                            <a href="javascript:;" class="x-admin-backlog-body">
                                <h3>友链数量</h3>
                                <p>
                                    <cite>${linkSize}</cite></p>
                            </a>
                        </li>
                        <li class="layui-col-md2 layui-col-xs6 ">
                            <a href="javascript:;" class="x-admin-backlog-body">
                                <h3>文章浏览数量</h3>
                                <p>
                                    <cite>${flowSize}</cite></p>
                            </a>
                        </li>
                        <li class="layui-col-md2 layui-col-xs6 ">
                            <a href="javascript:;" class="x-admin-backlog-body">
                                <h3>日记数量</h3>
                                <p>
                                    <cite>${diarySize}</cite></p>
                            </a>
                        </li>
                        <li class="layui-col-md2 layui-col-xs6 ">
                            <a href="javascript:;" class="x-admin-backlog-body">
                                <h3>置顶数量</h3>
                                <p>
                                    <cite>${topSize}</cite></p>
                            </a>
                        </li>
                        <li class="layui-col-md2 layui-col-xs6 ">
                            <a href="javascript:;" class="x-admin-backlog-body">
                                <h3>管理员数量</h3>
                                <p>
                                    <cite>${adminSize}</cite></p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">系统信息</div>
                <div class="layui-card-body ">
                    <table class="layui-table">
                        <tbody>
                        <tr>
                            <th>系统版本</th>
                            <td>1.0</td>
                        </tr>
                        <tr>
                            <th>服务器地址</th>
                            <td>www.chao58.top</td>
                        </tr>
                        <tr>
                            <th>操作系统</th>
                            <td>Linux</td>
                        </tr>
                        <tr>
                            <th>JDK版本</th>
                            <td>8u121</td>
                        </tr>
                        <tr>
                            <th>Tomcat版本</th>
                            <td>8.5.50</td>
                        </tr>
                        <tr>
                            <th>MYSQL版本</th>
                            <td>5.7.29</td>
                        </tr>
                        <tr>
                            <th>带宽</th>
                            <td>5 MB</td>
                        </tr>
                        <tr>
                            <th>流量</th>
                            <td>1000 GB</td>
                        </tr>
                        <tr>
                            <th>内存</th>
                            <td>2 GB</td>
                        </tr>
                        <tr>
                            <th>储存</th>
                            <td>40 GB</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script>

</script>
</body>
</html>