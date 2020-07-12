<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/10
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../layui/css/layui.css"/>
</head>
<body>

<ul id="demo">
    <li>1</li>
    <li>2</li>
    ……
    <li>6</li>
</ul>

<script src="../layui/layui.js"></script>
<script>
    layui.use('flow', function () {
        var $ = layui.jquery;
        var flow = layui.flow;
        flow.load({
            elem: '#demo',
            done: function (page, next) {
                $.ajax({
                    type: 'get',
                    url: '/test?page=' + page,
                    success: function (data) {
                        next('<li>' + data.title + '</li>',page<data.pages);
                    },
                    dataType: 'json'
                })
            }
        });
    });
</script>
</body>
</html>
