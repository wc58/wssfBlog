<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/15
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width"/>
    <meta name="author" content="www.yanshisan.cn"/>
    <meta name="robots" content="all"/>
    <title>友链</title>
    <link rel="icon" href="${pageContext.request.contextPath}/logo.png" type="image/x-icon"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/master.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gloable.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/about.css"/>
    <style>
        .blog-user {
            margin-top: 17.5px;
        }
    </style>
</head>
<body>
<div class="header">
</div>
<header class="gird-header">
    <div class="header-fixed">
        <div class="header-inner">
            <a href="/blog/list" class="header-logo" id="logo">Chao.Sir</a>
            <%@ include file="include-bar.jsp" %>
            <a class="phone-menu">
                <i></i>
                <i></i>
                <i></i>
            </a>
        </div>
    </div>
</header>
<div id="doc-container">
    <div class="about-banner" id="container">
        <header class="l-top hasAnim arrow-holder">
            <a data-path-hover="M31.3184948,33.1943359 C36.3357454,28.0664371 44.4728686,28.0690462 49.572124,33.2807584 C54.6360745,38.4563871 54.6061839,46.8782889 49.6566817,51.9369454 L31.318494,69.5197703 L49.6566817,89.71735 C54.6739322,94.8452488 54.6713794,103.161825 49.572124,108.373537 C44.5081735,113.549166 36.267997,113.518616 31.3184948,108.459959 L3.8112137,78.891075 C-1.25273677,73.7154463 -1.2880417,65.3601778 3.8112137,60.1484655 L31.3184948,33.1943359 Z">
                <svg width="0" height="0">
                    <path fill="#fff"
                          d="M58.9103319,3.8342148C63.9275825,-1.29368407,72.0647057,-1.29107495,77.1639611,3.92063726C82.2279116,9.09626594,82.198021,17.5181678,77.2485188,22.5768242C77.2485188,22.5768242,31.318494,69.5197703,31.318494,69.5197703C31.318494,69.5197703,77.2485188,116.462716,77.2485188,116.462716C82.2657693,121.590615,82.2632165,129.907191,77.1639611,135.118903C72.1000106,140.294532,63.8598341,140.263982,58.9103319,135.205326C58.9103319,135.205326,3.8112137,78.891075,3.8112137,78.891075C-1.25273677,73.7154463,-1.2880417,65.3601778,3.8112137,60.1484655C3.8112137,60.1484655,58.9103319,3.8342148,58.9103319,3.8342148C58.9103319,3.8342148,58.9103319,3.8342148,58.9103319,3.8342148"></path>
                </svg>
            </a>
            <a data-path-hover="M31.3184948,33.1943359 C36.3357454,28.0664371 44.4728686,28.0690462 49.572124,33.2807584 C54.6360745,38.4563871 54.6061839,46.8782889 49.6566817,51.9369454 L31.318494,69.5197703 L49.6566817,89.71735 C54.6739322,94.8452488 54.6713794,103.161825 49.572124,108.373537 C44.5081735,113.549166 36.267997,113.518616 31.3184948,108.459959 L3.8112137,78.891075 C-1.25273677,73.7154463 -1.2880417,65.3601778 3.8112137,60.1484655 L31.3184948,33.1943359 Z">
                <svg width="0" height="0">
                    <path fill="#fff"
                          d="M58.9103319,3.8342148 C63.9275825,-1.29368407 72.0647057,-1.29107495 77.1639611,3.92063726 C82.2279116,9.09626594 82.198021,17.5181678 77.2485188,22.5768242 L31.318494,69.5197703 L77.2485188,116.462716 C82.2657693,121.590615 82.2632165,129.907191 77.1639611,135.118903 C72.1000106,140.294532 63.8598341,140.263982 58.9103319,135.205326 L3.8112137,78.891075 C-1.25273677,73.7154463 -1.2880417,65.3601778 3.8112137,60.1484655 L58.9103319,3.8342148 Z"></path>
                </svg>
            </a>
        </header>
        <div class="about-title">
            <h1>友情链接</h1>
            <p>同门为朋，同志为友</p>
        </div>
    </div>
    <div class="container-fixed">
        <div class="container-inner">
            <article>
                <section>
                    <h1>链接申请说明</h1>
                    <p>
                        <i class="fa fa-close" style="color: red"></i>经常宕机&nbsp;
                        <i class="fa fa-close" style="color: red"></i>不合法规&nbsp;
                        <i class="fa fa-close" style="color: red"></i>插边球站&nbsp;
                        <i class="fa fa-close" style="color: red"></i>红标报毒&nbsp;
                        <i class="fa fa-check" style="color: green"></i>原创优先&nbsp;
                        <i class="fa fa-check" style="color: green"></i>技术优先
                    </p>
                    <p>
                        若交换友链，在吐槽板中留如下格式：<br>
                        名称：往事随风<br>
                        网址：https://www.chao58.top<br>
                        图标：https://www.chao58.top/logo.png<br>
                        描述：山不在高有仙则名，水不在深有龙则灵
                    </p>
                    <p>
                        申请提交后若无其它原因将在24小时内审核，如超过时间还未通过，请私信我。经常光顾本站，友链可以靠前哦
                    </p>
                    <button id="apply" class="layui-btn layui-btn-radius layui-btn-normal">申请</button>
                </section>
            </article>
            <ul class="link-list">
                <c:forEach items="${links}" var="link">
                    <li>
                        <div class="pd-lr-10">
                            <a href="${link.url}" target="_blank">
                                <img src="${link.icon}">
                                <h3>${link.title}</h3>
                                <p>${link.des}</p>
                            </a>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<%@ include file="include-footer.jsp" %>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/yss/gloable.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/nprogress.js"></script>
<script>NProgress.start();</script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="../js/plugins/blogbenoitboucart.min.js"></script>
<script>
    layui.use('layer', function () {
        $("#apply").click(function () {
            var userId = '${sessionScope.user.id}';
            var isApply = '${sessionScope.isApply==null?'0':'1'}';
            if (userId === '') {
                layer.msg("请先登录", {
                    offset: ['300px']
                })
                return;
            }
            if (isApply === '1') {
                layer.msg("该账户已经申请过", {
                    offset: ['300px']
                })
                return;
            }
            layer.open({
                type: 2,
                anim: 1,
                title: '申请友链',
                content: '/linkForm',
                offset: ['200px'],
                area: ['350px', '350px'],
            });
        })
    })
</script>
</body>
</html>
