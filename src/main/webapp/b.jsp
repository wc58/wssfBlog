

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width" />
    <meta name="author" content="林强,www.fallinlovemy.cn,930054439@qq.com" />
    <meta name="robots" content="all" />
    <title>燕十三&#183;文章列表</title>
    <link rel="ICON" href="/logo.png">
    <link rel="SHORTCUT ICON" href="/logo.png">
    <!--[if lt IE 11]><script>window.location.href="https://www.yanshisan.cn/DisableAccess.html";</script><![endif]-->
    <link rel="stylesheet" href="/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/layui/css/layui.css" />
    <link href="/css/gloable?v=5vBhdKLCuNARQnnIXBH8-ihOZ_vTOyVALRB1AO8lGtA1" rel="stylesheet"/>

    <script>
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?db61211eab38025ea7cb1aa72611175a";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

    <meta name="keywords" content="燕十三,一个人的江湖" />
    <meta name="description" content="一个人的江湖">

    <link href="/css/blog?v=6aU7D_vZn5XKW4Lk492-p_uOGg5sw4vrxjY107lXg9Y1" rel="stylesheet"/>


</head>
<body>
<div class="header">
</div>
<header class="gird-header">
    <div class="header-fixed">
        <div class="header-inner">
            <a href="javascript:void(0)" class="header-logo" id="logo">Mr.Yss</a>
            <nav class="nav" id="nav">
                <ul>
                    <li><a href="/Blog/Index">首页</a></li>
                    <li><a href="/Blog/Article">博客</a></li>
                    <li><a href="/Message/Index">留言</a></li>
                    <li><a href="/Diary/Diarys">日记</a></li>
                    <li><a href="/Link/Links">友链</a></li>
                    <li><a href="/About/Index">关于</a></li>
                </ul>
            </nav>
            <a href="/User/LogOut" class="blog-user">
                <img src="https://thirdqq.qlogo.cn/g?b=oidb&amp;k=SQA2w3pejXhCbf0YRJ9icEw&amp;s=100&amp;t=1585984285" alt="往事随风" title="往事随风" />
            </a>
            <a class="phone-menu">
                <i></i>
                <i></i>
                <i></i>
            </a>
        </div>
    </div>
</header>
<div class="doc-container" id="doc-container">


    <div class="container-fixed">
        <input type="hidden" value="0" id="blogtypeid" />
        <div class="col-content">
            <div class="inner">
                <article class="article-list bloglist" id="LAY_bloglist" data-count="15" data-pagesize="10" data-pagecount="2" data-type="0"></article>
            </div>
        </div>
        <div class="col-other">
            <div class="inner">
                <div class="other-item" id="categoryandsearch">
                    <div class="search">
                        <label class="search-wrap">
                            <input type="text" id="searchtxt" placeholder="输入关键字搜索" />
                            <span class="search-icon">
                <i class="fa fa-search"></i>
            </span>
                        </label>
                        <ul class="search-result"></ul>
                    </div>
                    <ul class="category mt20" id="category">
                        <li data-index="0" class="slider"></li>
                        <li data-index="1"><a href="/Blog/Article">全部文章</a></li>

                        <li data-index="2"><a href="/Blog/Article/1/">个人日记</a></li>
                        <li data-index="3"><a href="/Blog/Article/2/">HTML5&amp;CSS3</a></li>
                        <li data-index="4"><a href="/Blog/Article/3/">JavaScript</a></li>
                        <li data-index="5"><a href="/Blog/Article/4/">ASP.NET MVC</a></li>
                        <li data-index="6"><a href="/Blog/Article/5/">其它</a></li>
                    </ul>
                </div>
                <!--右边悬浮 平板或手机设备显示-->
                <div class="category-toggle"><i class="layui-icon">&#xe603;</i></div>
                <div class="article-category">
                    <div class="article-category-title">分类导航</div>
                    <a href="/Blog/Article/1/">个人日记</a>
                    <a href="/Blog/Article/2/">HTML5&amp;CSS3</a>
                    <a href="/Blog/Article/3/">JavaScript</a>
                    <a href="/Blog/Article/4/">ASP.NET MVC</a>
                    <a href="/Blog/Article/5/">其它</a>
                    <div class="f-cb"></div>
                </div>
                <!--遮罩-->
                <div class="blog-mask animated layui-hide"></div>
                <div class="other-item">
                    <h5 class="other-item-title">热门文章</h5>
                    <div class="inner">
                        <ul class="hot-list-article">
                            <li> <a href="/Blog/Read/12">模板分享</a></li>
                            <li> <a href="/Blog/Read/21">新增QQ音乐与网易云音乐搜索</a></li>
                            <li> <a href="/Blog/Read/20">我的第一个C/S项目</a></li>
                            <li> <a href="/Blog/Read/9">2018最新版QQ音乐api调用</a></li>
                            <li> <a href="/Blog/Read/18">MUI动态加载组件</a></li>
                            <li> <a href="/Blog/Read/13">逆水寒</a></li>
                            <li> <a href="/Blog/Read/19">MUI上拉加载和下拉刷新出现双滚动条BUG解决办法</a></li>
                            <li> <a href="/Blog/Read/4">序章</a></li>
                        </ul>
                    </div>
                </div>
                <div class="other-item">
                    <h5 class="other-item-title">置顶推荐</h5>
                    <div class="inner">
                        <ul class="hot-list-article">
                            <li> <a href="/Blog/Read/12">模板分享</a></li>
                        </ul>
                    </div>
                </div>
                <div class="other-item">
                    <h5 class="other-item-title">最近访客</h5>
                    <div class="inner">
                        <dl class="vistor">
                            <dd><a href="javasript:;"><img src="https://thirdqq.qlogo.cn/g?b=oidb&amp;k=c7h2D2p8ddZ0YGtVcCoudQ&amp;s=100&amp;t=1589087042"><cite>雨下一整晚</cite></a></dd>
                            <dd><a href="javasript:;"><img src="https://thirdqq.qlogo.cn/g?b=oidb&amp;k=k0ju84485icdKkibKzAibFgxw&amp;s=100&amp;t=1497926500"><cite>Echo</cite></a></dd>
                            <dd><a href="javasript:;"><img src="https://thirdqq.qlogo.cn/g?b=oidb&amp;k=Stzev8cIOV0T9voXP0V98g&amp;s=100&amp;t=1592353329"><cite>敲代码的帅boy</cite></a></dd>
                            <dd><a href="javasript:;"><img src="https://thirdqq.qlogo.cn/g?b=oidb&amp;k=SQA2w3pejXhCbf0YRJ9icEw&amp;s=100&amp;t=1585984285"><cite>往事随风</cite></a></dd>
                            <dd><a href="javasript:;"><img src="https://thirdqq.qlogo.cn/g?b=oidb&amp;k=dKRsrr08HLDuXMkYm3qc5g&amp;s=100&amp;t=1563374683"><cite>o</cite></a></dd>
                            <dd><a href="javasript:;"><img src="https://thirdqq.qlogo.cn/g?b=oidb&amp;k=icFT9tah5EPz3ddEYKZwmRw&amp;s=100&amp;t=1571214851"><cite>艾瑞</cite></a></dd>
                            <dd><a href="javasript:;"><img src="https://thirdqq.qlogo.cn/g?b=oidb&amp;k=JtoUUsbqInDMd47SwTVerw&amp;s=100&amp;t=1555470601"><cite>咑醬油の</cite></a></dd>
                            <dd><a href="javasript:;"><img src="https://thirdqq.qlogo.cn/g?b=oidb&amp;k=bdT0teP6ia3qGNmoLwYEpRA&amp;s=100&amp;t=1591578068"><cite>1118</cite></a></dd>
                            <dd><a href="javasript:;"><img src="https://thirdqq.qlogo.cn/g?b=oidb&amp;k=mKM27axzxBh8exibsUTLCoQ&amp;s=100&amp;t=1579400607"><cite>921125</cite></a></dd>
                            <dd><a href="javasript:;"><img src="https://thirdqq.qlogo.cn/g?b=oidb&amp;k=mvyJBPWXENHo46gePfpfEA&amp;s=100&amp;t=1555558322"><cite>流年不悔</cite></a></dd>
                            <dd><a href="javasript:;"><img src="https://thirdqq.qlogo.cn/g?b=oidb&amp;k=89BMkWhXbIKBn7QPEUbEsw&amp;s=100&amp;t=1587916783"><cite>DirtyShady</cite></a></dd>
                            <dd><a href="javasript:;"><img src="https://thirdqq.qlogo.cn/g?b=oidb&amp;k=opjicMiclWXoVGyXdlWiaKvRg&amp;s=100&amp;t=1483337508"><cite>风移影动</cite></a></dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
    </div>



</div>
<footer class="grid-footer">
    <div class="footer-fixed">
        <div class="copyright">
            <div class="info">
                <div class="contact">
                    <a href="javascript:void(0)" class="github" target="_blank"><i class="fa fa-github"></i></a>
                    <a href="https://wpa.qq.com/msgrd?v=3&uin=930054439&site=qq&menu=yes" class="qq" target="_blank" title="930054439"><i class="fa fa-qq"></i></a>
                    <a href="https://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=gbiysbG0tbWyuMHw8K-i7uw" class="email" target="_blank" title="930054439@qq.com"><i class="fa fa-envelope"></i></a>
                    <a href="javascript:void(0)" class="weixin"><i class="fa fa-weixin"></i></a>
                </div>
                <p class="mt05">
                    Copyright &copy; 2018-2020 燕十三 All Rights Reserved V.1.0.0 备案号:<a style="color:white" href="http://www.beian.miit.gov.cn/">蜀ICP备18008600号</a>
                </p>
            </div>
        </div>
    </div>
</footer>
<script src="/layui/layui.js"></script>
<script src="/bundles/gloable?v=Tx8E_jlefc-qHqMr9nEx3RqfoLEiCOebu1R4XsPneBk1"></script>

<script>NProgress.start();</script>

<script src="/bundles/blog?v=9nVRiS3vtrfwYv26Br5kedImco8uKcU1A7X5Ltvmyfc1"></script>

<script src="/bundles/article?v=8Fm7gGLTyLjEEC69gUBqjTGpF7TGMnAo_HNtz2TPalo1"></script>



<script>
    window.onload = function () {
        NProgress.done();
    };
    (function () {
        var bp = document.createElement('script');
        var curProtocol = window.location.protocol.split(':')[0];
        if (curProtocol === 'https') {
            bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
        }
        else {
            bp.src = 'http://push.zhanzhang.baidu.com/push.js';
        }
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(bp, s);
    })();
</script>
</body>
</html>
