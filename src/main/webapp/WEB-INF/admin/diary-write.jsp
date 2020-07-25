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
    <title>写写日记</title>
    <%@ include file="staticSource.jsp" %>
    <link type="text/css" href="../../css/wangEditor.css">
    <link rel="stylesheet" href="../../highlight/styles/atelier-forest-light.css">
</head>
<body>

<div class="layui-container">
    <br/>
    <br/>
    <div class="layui-row">
        <div id="content" class="text">
            <p>${dairy.content}</p>
        </div>
    </div>
</div>

<div class="layui-row">
    <br/>
    <div class="layui-col-md8">
        <input type="button" style="border: none">
    </div>
    <div class="layui-col-md4">
        <div class="layui-form-item">
            <button id="newBlog" class="layui-btn layui-btn-radius layui-btn-normal">重写</button>
            <button id="save" class="layui-btn layui-btn-radius">保存</button>
        </div>
    </div>
</div>
</div>


<script src="${pageContext.request.contextPath}/highlight/highlight.pack.js"></script>
<script>

    document.addEventListener('keydown', function (e) {
        if (e.keyCode == 83 && (navigator.platform.match("Mac") ? e.metaKey : e.ctrlKey)) {
            e.preventDefault();
            save();
        }
    });

    var E = window.wangEditor
    var editor = new E('#content')
    editor.customConfig.uploadImgMaxSize = 10 * 1024 * 1024
    editor.customConfig.uploadImgMaxLength = 5
    editor.customConfig.uploadFileName = 'diary'
    editor.customConfig.uploadImgServer = '/admin/uploadDiary'
    editor.customConfig.menus = [
        'italic', // 斜体
        'underline', // 下划线 
        'strikeThrough', // 删除线
        'foreColor', // 文字颜色
        'backColor', // 背景颜色
        'emoticon', // 表情
        'image', // 插入图片 
    ]
    editor.create()
    E.fullscreen.init('#content');
    $(function () {
        $('pre code').each(function (i, block) {
            hljs.highlightBlock(block);
        });
    });
    /**
     * @todo 查看源码
     */
    window.wangEditor.viewsource = {
        init: function (editorSelector) {
            $(editorSelector + " .w-e-toolbar").append('<div class="w-e-menu"><a class="_wangEditor_btn_viewsource" href="###" onclick="window.wangEditor.viewsource.toggleViewsource(\'' + editorSelector + '\')">源码</a></div>');
        },
        toggleViewsource: function (editorSelector) {
            editorHtml = editor.txt.html();
            if ($(editorSelector + ' ._wangEditor_btn_viewsource').text() == '源码') {
                editorHtml = editorHtml.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/ /g, "&nbsp;");
                $(editorSelector + ' ._wangEditor_btn_viewsource').text('返回');
            } else {
                editorHtml = editor.txt.text().replace(/&lt;/ig, "<").replace(/&gt;/ig, ">").replace(/&nbsp;/ig, " ");
                $(editorSelector + ' ._wangEditor_btn_viewsource').text('源码');
            }
            editor.txt.html(editorHtml);
            editor.change && editor.change();	//更新编辑器的内容
        }
    };
    E.viewsource.init('#content');


    var unloadPageTip = function () {
        return "您编辑的文章内容还没有进行保存!";
    };
    window.onbeforeunload = unloadPageTip;

    //标签
    var id;

    /*回显数据*/
    $(function () {
        //数据回显
        id = '${dairy.id}';
    })


    //保存设置
    $("#save").click(function () {
        save();
    });

    function save() {
        var content = editor.txt.html();
        $.ajax({
            type: 'post',
            url: '/admin/addDiary',
            data: {
                'id': id,
                'content': content
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
    }

    //写新的文章
    $("#newBlog").click(function () {
        location.reload();
    });

</script>
</body>
</html>
