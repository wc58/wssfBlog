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
                        <input id="title" type="text" name="title" placeholder="请输入标题" autocomplete="off"
                               class="layui-input">
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
                        <textarea id="assistant" name="desc" placeholder="请输入文章描述" class="layui-textarea"></textarea>
                    </div>
                </div>
            </div>
            <div class="layui-col-md8">
                <div class="layui-form-item">
                    <label class="layui-form-label">封面：</label>
                    <button type="button" class="layui-btn" id="uploadCover">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
                    <span id="image"></span>
                </div>
            </div>
        </div>

        <div class="layui-row">
            <div class="layui-col-md4">
                <div class="layui-form-item">
                    <label class="layui-form-label">状态：</label>
                    <div class="layui-input-block">
                        <input id="status" type="text" name="title" placeholder="专题？转载？自创？" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
            </div>

            <div class="layui-col-md4">
                <div class="layui-form-item">
                    <label class="layui-form-label">发布</label>
                    <div class="layui-input-inline">
                        <input id="del" value="0" type="checkbox" name="switch" lay-skin="switch" lay-text="ON|OFF">
                    </div>
                </div>
            </div>

            <div class="layui-col-md4">
                <div class="layui-form-item">
                    <label class="layui-form-label">置顶</label>
                    <div class="layui-input-inline">
                        <input id="top" value="1" type="checkbox" name="switch" lay-skin="switch" lay-text="ON|OFF">
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

    var unloadPageTip = function () {
        return "您编辑的文章内容还没有进行保存!";
    };
    window.onbeforeunload = unloadPageTip;

    $(function () {
        var topSize = '${topSize}';
        //说明支持置顶了
        if (topSize == 'false') {
            $("#top").attr('disabled', 'disabled')
        }
    })

    //标签
    var select;
    var id;

    //仅仅保存，不发布
    $("#save").click(function () {
        //标题
        var title = $("#title").val();
        //描述
        var assistant = $("#assistant").val();
        //封面
        var picture = $("#coverImage").attr('src');
        //内容
        var content = editor.txt.html();
        //状态
        var status = $("#status").val();
        //发布
        var del = $("#del").prop("checked");
        //置顶
        var top = $("#top").prop("checked");
        //标签
        var labels = select.getValue('value');

        if (title == '') {
            layer.msg("怎么能没有了标题呢？")
            return;
        }

        if (labels == '') {
            layer.msg("至少选择一个标签")
            return;
        }

        if (picture === undefined && assistant === '') {
            layer.msg("封面和描述必须任选其一")
            return;
        }

        if (status == '') {
            layer.msg("状态也不能为空啊！")
            return;
        }

        if (content.length < 20) {
            layer.msg("内容也忒少了把！")
            return;
        }

        $.ajax({
            type: 'post',
            url: '/admin/addArticle',
            data: {
                'clientId': id,
                'title': title,
                'assistant': assistant,
                'picture': picture,
                'content': content,
                'status': status,
                'del': del,
                'top': top,
                'labels': labels.join()
            },
            success: function (data) {
                if (data.code === 1000) {
                    layer.msg("保存成功")
                    id = data.map.id;
                } else {
                    layer.msg("保存失败，请注意数据！！！")
                }
            },
            dataType: 'json'
        })
    });

    //直接发布
    $("#submit").click(function () {
        alert(editor.txt.html());
    });

    layui.use('form', function () {

    });

    var E = window.wangEditor
    var editor = new E('#tools', '#content')
    editor.customConfig.uploadImgMaxSize = 10 * 1024 * 1024
    editor.customConfig.uploadImgMaxLength = 5
    editor.customConfig.uploadFileName = 'article'
    editor.customConfig.uploadImgServer = '/admin/uploadPictures'
    editor.create()

    $.ajax({
        type: 'post',
        url: '/label/getAllLabels',
        dataType: 'json',
        success: function (data) {
            select = xmSelect.render({
                // 这里绑定css选择器
                el: '#label',
                // 渲染的数据
                data: data,
            });
        }
    })


    /*封面上传和删除*/
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
                    document.getElementById("uploadCover").style.display = "none";
                    var img = document.createElement('img');//创建一个标签
                    img.setAttribute('src', res.map.imageUrl);//给标签定义src链接
                    img.setAttribute('id', 'coverImage');
                    img.setAttribute("title", "双击删除图片");
                    img.ondblclick = function () {
                        // img.style.display = "none";
                        //删除照片
                        $("#coverImage").remove();
                        document.getElementById("uploadCover").style.display = "block";
                        //删除图片
                        $.ajax({
                            type: 'post',
                            url: '/admin/delCover',
                            data: {
                                'filePath': res.map.imageUrl
                            },
                            success: function (data) {
                                if (data.code === 1000) {
                                    layer.msg("删除成功");
                                }
                            },
                            dataType: 'json'
                        })
                    };
                    img.style.height = '150px';
                    img.style.width = '250px';
                    document.getElementById('image').appendChild(img);//放到指定的id里
                }
            },
            error: function () {

            }
        });
    });
</script>
</body>
</html>
