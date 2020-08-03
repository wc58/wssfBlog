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

        <div></div>
        <a href="javascript:;" onclick="location.reload()">刷新</a>
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">数据统计</div>
                <div class="layui-card-body ">
                    <ul class="layui-row layui-col-space10 layui-this x-admin-carousel x-admin-backlog">
                        <li class="layui-col-md2 layui-col-xs6">
                            <a href="javascript:;" class="x-admin-backlog-body">
                                <h3>建站时间</h3>
                                <p>
                                    <cite>9</cite></p>
                            </a>
                        </li>
                        <li class="layui-col-md2 layui-col-xs6">
                            <a href="javascript:;" class="x-admin-backlog-body">
                                <h3>实现倒计时</h3>
                                <p>
                                    <cite>332</cite></p>
                            </a>
                        </li>
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
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-sm12 layui-col-md6">
                    <div class="layui-card">
                        <div class="layui-card-header">首页访问量</div>
                        <div class="layui-card-body" style="min-height: 280px;">
                            <div id="indexFlow" class="layui-col-sm12" style="height: 300px;"></div>

                        </div>
                    </div>
                </div>
                <div class="layui-col-sm12 layui-col-md6">
                    <div class="layui-card">
                        <div class="layui-card-header">文章点击量</div>
                        <div class="layui-card-body" style="min-height: 280px;">
                            <div id="articleFlow" class="layui-col-sm12" style="height: 300px;"></div>

                        </div>
                    </div>
                </div>

                <div class="layui-col-sm12 layui-col-md6">
                    <div class="layui-card">
                        <div class="layui-card-header">文章回复量</div>
                        <div class="layui-card-body" style="min-height: 280px;">
                            <div id="eleaveSize" class="layui-col-sm12" style="height: 300px;"></div>

                        </div>
                    </div>
                </div>
                <div class="layui-col-sm12 layui-col-md6">
                    <div class="layui-card">
                        <div class="layui-card-header">吐槽量</div>
                        <div class="layui-card-body" style="min-height: 280px;">
                            <div id="earticleSize" class="layui-col-sm12" style="height: 300px;"></div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../js/echarts.min.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('indexFlow'));

    // 指定图表的配置项和数据
    var option = {
        grid: {
            top: '5%',
            right: '1%',
            left: '1%',
            bottom: '10%',
            containLabel: true
        },
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            type: 'category',
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            name: '用户量',
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line',
            smooth: true
        }]
    };


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('articleFlow'));

    // 指定图表的配置项和数据
    var option = {
        grid: {
            top: '5%',
            right: '1%',
            left: '1%',
            bottom: '10%',
            containLabel: true
        },
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            type: 'category',
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            name: '用户量',
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line',
            smooth: true
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);


    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('earticleSize'));

    // 指定图表的配置项和数据
    var option = {
        grid: {
            top: '5%',
            right: '1%',
            left: '1%',
            bottom: '10%',
            containLabel: true
        },
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            type: 'category',
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            name: '用户量',
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line',
            smooth: true
        }]
    };


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('eleaveSize'));

    // 指定图表的配置项和数据
    var option = {
        grid: {
            top: '5%',
            right: '1%',
            left: '1%',
            bottom: '10%',
            containLabel: true
        },
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            type: 'category',
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            name: '用户量',
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line',
            smooth: true
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);


</script>
</body>
</html>