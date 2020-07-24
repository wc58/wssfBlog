<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/24
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<div id="layui-container">
    <div class="layui-row">
        <br/>
        <br/>
        <div class="layui-form-item">
            <label class="layui-form-label">名称：</label>
            <div class="layui-input-block" style="width: 200px">
                <input id="title" type="text" name="title" placeholder="往事随风"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">网址：</label>
            <div class="layui-input-block" style="width: 200px">
                <input id="url" type="text" name="title" value="http://"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">图标：</label>
            <div class="layui-input-block" style="width: 200px">
                <input id="icon" type="text" name="title" value="http://"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">描述：</label>
            <div class="layui-input-block" style="width: 200px">
                <input id="desc" type="text" name="title" placeholder="山不在高有仙则名，水不在深有龙则灵"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="width: 200px">
                <button id="apply" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal">确定申请</button>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script>
    layui.use('layer', function () {
        $("#apply").click(function () {
            var title = $("#title").val();
            var icon = $("#icon").val();
            var url = $("#url").val();
            var desc = $("#desc").val();
            if (title == '' || icon == '' || url == '' || desc == '') {
                layer.msg("上述内容不能为空")
                return;
            }
            if (title.length > 6) {
                layer.msg("名称过长")
                return;
            }
            if (desc.length > 18) {
                layer.msg("描述过长")
                return;
            }
            layer.confirm('一个账号只能提交一次，且不能修改，若有误请吐槽板留言', {title: '提示'}, function (index) {
                $.ajax({
                    type: 'post',
                    url: '/link/applyLink',
                    data: {
                        title, title,
                        url: url,
                        icon: icon,
                        desc: desc
                    },
                    success: function (res) {
                        if (res.code === 1000) {
                            layer.alert('24小时内审核', {icon: 1}, function () {
                                var i = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(i); //再执行关闭
                                layer.close(index);
                            });
                        } else {
                            layer.alert('申请失败，刷新页面请重试', {icon: 2}, function () {
                                var i = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(i); //再执行关闭
                                layer.close(index);
                            });
                        }
                    },
                    dataType: "json"
                })
                layer.close(index);
            });


        })
    })

</script>