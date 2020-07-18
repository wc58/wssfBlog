<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/18
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>发布文章</title>
    <%@ include file="staticSource.jsp" %>
    <style type="text/css">
        .toolbar {
            border: 1px solid #ccc;
        }

        .text {
            border: 1px solid #ccc;
            height: 580px;
        }
    </style>
</head>
<body>
<div class="layui-container">
    <br/>
    <br/>
    <br/>
    <form class="layui-form">
        <div class="layui-row">
            <div class="layui-col-md4">
                <div class="layui-form-item">
                    <label class="layui-form-label">标题：</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" placeholder="请输入标题" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-col-md8">
                <div class="layui-form-item">
                    <label class="layui-form-label">标签：</label>
                    <div class="layui-input-block">
                        <div id="label"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-row">
            <div class="layui-col-md4">
                <div class="layui-form-item">
                    <label class="layui-form-label">描述：</label>
                    <div class="layui-input-block">
                        <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
                    </div>
                </div>
            </div>
            <div class="layui-col-md8">
                <div class="layui-form-item">
                    <label class="layui-form-label">封面：</label>
                    <button type="button" class="layui-btn" id="uploadCover">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
                </div>
            </div>
        </div>

        <div class="layui-row">
            <div class="layui-col-md4">
                <div class="layui-form-item">
                    <label class="layui-form-label">状态：</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" placeholder="请输入标题" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>

            <div class="layui-col-md4">
                <div class="layui-form-item">
                    <label class="layui-form-label">发布</label>
                    <div class="layui-input-inline">
                        <input type="checkbox" name="switch" lay-skin="switch">
                    </div>
                </div>
            </div>

            <div class="layui-col-md4">
                <div class="layui-form-item">
                    <label class="layui-form-label">置顶</label>
                    <div class="layui-input-inline">
                        <input type="checkbox" name="switch" lay-skin="switch">
                    </div>
                </div>
            </div>
        </div>

    </form>
    <div class="layui-row">
        <br/>
        <br/>
        <div id="tools" class="toolbar">
        </div>
        <div style="padding: 5px 0; color: #ccc"></div>
        <div id="content" class="text">
        </div>
    </div>
</div>

<div class="layui-row">
    <br/>
    <div class="layui-col-md9">
        <input type="button" style="border: none">
    </div>
    <div class="layui-col-md3">
        <div class="layui-form-item">
            <div class="layui-input-inline">
                <button id="save" class="layui-btn layui-btn-radius">保存</button>
                <button id="submit" class="layui-btn layui-btn-radius layui-btn-normal">立即提交</button>
            </div>
        </div>
    </div>
</div>

<script>

    layui.use('form', function () {

    });

    var E = window.wangEditor
    var editor = new E('#tools', '#content')
    editor.create()

    $("#save").click(function () {
        layer.msg(editor.txt.text());
    });

    $("#submit").click(function () {
        alert(editor.txt.html());
    });


    $.ajax({
        type: 'post',
        url: '/label/getAllLabels',
        dataType: 'json',
        success: function (data) {
            xmSelect.render({
                // 这里绑定css选择器
                el: '#label',
                // 渲染的数据
                data: data,
            });
        }
    })


    layui.use('upload', function () {
        var upload = layui.upload;

        //执行实例
        upload.render({
            elem: '#uploadCover',
            url: '/admin/uploadCover',
            accept: 'images',
            field: 'cover',
            size: 1024 * 3,
            done: function (res) {
                if (res.code === 1000) {
                    alert(res.map.servicePath);
                }
            },
            error: function () {
                //请求异常回调
            }
        });
    });
</script>
</body>
</html>
