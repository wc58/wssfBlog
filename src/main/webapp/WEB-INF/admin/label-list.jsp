<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/20
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.2</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <%@ include file="staticSource.jsp" %>
</head>

<body>
<div class="x-nav">
            <span class="layui-breadcrumb">
                <a href="">首页</a>
                <a href="">演示</a>
                <a>
                    <cite>导航元素</cite></a>
            </span>
    <br/>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <div class="layui-form layui-col-space5">
                        <div class="layui-inline layui-show-xs-block">
                            <input id="sLabelName" class="layui-input" autocomplete="off" placeholder="标签名称"
                                   name="start">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" class="layui-input" placeholder="开始时间" id="startTime">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" class="layui-input" placeholder="结束时间" id="endTime">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button id="search" class="layui-btn">
                                <i class="layui-icon">&#xe615;</i></button>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button id="reset" class="layui-btn layui-btn-normal">
                                <i class="layui-icon layui-icon-refresh"></i></button>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <a id="add" class="layui-btn layui-btn-primary layui-btn-xs"><i
                                    class="layui-icon">&#xe654;</i></a>
                        </div>
                    </div>
                </div>
                <!--表格-->
                <div class="layui-card-body ">
                    <table id="article" lay-filter="articleTable"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="bar">
    <div class="layui-btn-container">
        <a class="layui-btn layui-btn-xs" lay-event="edit"> <i class="layui-icon">&#xe605;</i></a>
        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="delete"><i class="layui-icon">&#xe640;</i></a>

    </div>
</script>
<script>

    var $;
    var tableArticle;
    layui.use('table', function () {
        var table = layui.table;
        $ = layui.$;
        //第一个实例
        tableArticle = table.render({
            elem: '#article'
            , url: '/admin/getLabels' //数据接口
            , size: 'lg'
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'id', title: 'ID', width: 60, sort: true, fixed: 'left'}
                , {field: 'name', title: '标签名称', width: 500, sort: true, edit: 'text'}
                , {field: 'sort', title: '排序', width: 130, edit: 'text'}
                , {field: 'articleSize', title: '文章数量', width: 220, sort: true}
                , {field: 'createTime', title: '创建时间', width: 145, sort: true}
                , {fixed: 'right', width: 170, align: 'center', toolbar: '#bar'}
            ]]

        });

        $("#search").click(function () {
            tableArticle.reload({
                url: '/admin/getLabels' //数据接口
                , where: {
                    labelName: $("#sLabelName").val(),
                    startTime: $("#startTime").val(),
                    endTime: $("#endTime").val()
                }, page: {
                    curr: 1
                }
            })
        })

        $("#reset").click(function () {
            location.reload();
        })


        layui.use('laydate', function () {
            var laydate = layui.laydate;

            laydate.render({
                elem: '#startTime'
            });

            laydate.render({
                elem: '#endTime'
            });

        });

        //监听事件
        table.on('tool(articleTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            //置顶
            if (layEvent === 'delete') {
                layer.confirm('确定要删除该标签吗？', function () {//编辑
                    //服务器删除
                    $.ajax({
                        type: 'post',
                        url: '/admin/deleteLabel',
                        data: data,
                        success: function (res) {
                            if (res.code === 1000) {
                                layer.msg("删除成功！");
                                tableArticle.reload({
                                    url: '/admin/getLabels' //数据接口
                                })
                            } else {
                                layer.msg("删除失败！该标签下面可能还有文章");
                            }
                        },
                        dataType: 'json'
                    })
                });
            } else if (layEvent === 'edit') {
                layer.confirm('确定要修改标签信息吗？', function () {//编辑
                    $.ajax({
                        type: 'post',
                        url: '/admin/updateLabel',
                        data: data,
                        success: function (res) {
                            if (res.code == 1000) {
                                layer.msg("更新成功！");
                            } else {
                                layer.msg("更新失败！服务器错误！");
                            }
                        },
                        dataType: 'json'
                    })
                });
            }
        });

        $("#add").click(function () {
            layer.prompt({
                title: '添加标签',
            }, function (value, index) {
                $.ajax({
                    type: 'post',
                    url: '/admin/addLabel',
                    data: {name: value},
                    success: function (res) {
                        if (res.code == 1000) {
                            layer.msg("添加成功！");
                            tableArticle.reload({
                                url: '/admin/getLabels' //数据接口
                            })
                        } else {
                            layer.msg("添加失败！服务器错误！");
                        }
                    },
                    dataType: 'json'
                })
                layer.close(index);
            });

        })

    });


</script>
</html>
