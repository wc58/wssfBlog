<%--
  Created by IntelliJ IDEA.
  User: ChaoSir
  Date: 2020/7/15
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width"/>
    <meta name="author" content="www.yanshisan.cn"/>
    <meta name="robots" content="all"/>
    <title>关于</title>
    <link rel="icon" href="${pageContext.request.contextPath}/logo.png" type="image/x-icon"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/master.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gloable.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/about.css"/>
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
<div class="doc-container" id="doc-container">
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
            <h1>关于我</h1>
            <p>生于忧患，死于安乐</p>
        </div>
    </div>
    <div class="container-fixed">
        <div class="container-inner">
            <article>
                <section>
                    <h1>关于我</h1>
                    <p>
                        本人是一个02年的毛头小子，17年接触编程，之后就爱上了编程
                        <br/>
                        到了18年正式踏入编程生涯，直到现在一个人跌跌撞撞走到了今天，虽说不是什么大佬，但也可以做点小玩意
                        <br/>
                        最后给自己立个flag，如果学习10年编程，还有没有什么成就，那就彻底改行，再也不碰编程
                        <br/>
                        加油吧，骚年！！！
                    </p>
                    <span>
                    可以通过以下方式联系到我：
                </span>
                    <ul>
                        <li>邮 箱 ：<a href="javascript:;">2258354832@qq.com</a></li>
                        <li>github：<a href="javascript:;">https://github.com/wc58</a></li>
                    </ul>

                </section>
                <section>
                    <h1>关于本站</h1>
                    <p>
                        前台开发周期：2020年7月9号 ~ 2020年7月15号完成<br/>
                        后台开发周期：2020年7月16号 ~ 2020年7月25号完成<br/>
                        项目正式上线：2020年7月25号
                    </p>
                    <span>
                    本站结构：
                </span>
                    <ul>
                        <li>前 端 ：<code>Layui</code>+<code>jquery</code>+<code>燕十三模板</code>+<code>X-Admin</code></li>
                        <li>后 端 ：<code>SSM</code>+<code>Mybatis-plus</code></li>
                        <li>数据库：<code>Mysql</code></li>
                        <li>存 储 ：<code>阿里轻量级服务器</code>+<code>OSS</code></li>
                    </ul>
                </section>
                <section>
                    <h1>关于版权</h1>
                    <p>
                        本站采用「 <a href="https://creativecommons.org/licenses/by-nc/4.0/deed.zh" target="_blank">署名-非商业性使用
                        4.0 国际 (CC BY-NC 4.0)</a>」创作共享协议。
                        只要在使用时注明出处，那么您可以可以对本站所有原创内容进行转载、节选、二次创作，但是您不得对其用于商业目的。
                    </p>
                </section>
                <section>
                    <h1>特别说明</h1>
                    <ul>
                        <li>本站文章仅代表个人观点，和任何组织或个人无关。</li>
                        <li>本站前端开发代码没有考虑对IE浏览器的兼容。</li>
                        <li>用户邮箱仅作回复消息用，不对外使用。</li>
                    </ul>
                    <br><br>
                    <div>
                        <img src="/image/about.jpg"
                             style="width:100%;height:800px;">
                    </div>
                </section>
            </article>

        </div>
    </div>
</div>
<%@ include file="include-footer.jsp" %>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/yss/gloable.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/nprogress.js"></script>
<script>NProgress.start();</script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<%--<script src="../js/plugins/blogbenoitboucart.min.js"></script>--%>
<script>
    window.onload = function () {
        NProgress.done();
    };
</script>
</body>
</html>

