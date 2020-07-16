

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width" />
    <meta name="author" content="林强,www.fallinlovemy.cn,930054439@qq.com" />
    <meta name="robots" content="all" />
    <title>燕十三&#183;NPOI导入导出Excel</title>
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

    <meta name="keywords" content="NPOI导入导出Excel" />
    <meta name="description" content="NPOI是指构建在POI 3.x版本之上的一个程序，NPOI可以在没有安装Office的情况下对Word或Excel文档进行读写操作。
NPOI是一个开源的C#读写Excel、WORD等微软OLE2组件文档的项目。">
    <meta property="og:type" content="article" />
    <meta property="og:image" content="https://www.fallinlovemy.cn/image/cover/201842913738408.jpg" />
    <meta property="og:title" content="NPOI导入导出Excel" />
    <meta property="og:description" content="NPOI是指构建在POI 3.x版本之上的一个程序，NPOI可以在没有安装Office的情况下对Word或Excel文档进行读写操作。
NPOI是一个开源的C#读写Excel、WORD等微软OLE2组件文档的项目。" />
    <meta property="og:author" content="燕十三" />
    <meta property="og:release_date" content="2018/4/24 15:05:14" />
    <link href="/css/blog?v=6aU7D_vZn5XKW4Lk492-p_uOGg5sw4vrxjY107lXg9Y1" rel="stylesheet"/>

    <link href="/css/read?v=h0_DFEYh1fmUuyJgrTj_4-uVafBiPRiIVqB4d5vDtTc1" rel="stylesheet"/>


    <script src="/js/xss.js"></script>
    <script src="/js/plugins/highlight/highlight.pack.js"></script>
    <link href="/js/plugins/highlight/styles/monokai-sublime.css" rel="stylesheet" />
    <script>hljs.initHighlightingOnLoad();</script>

</head>
<body>
<div class="header">
</div>
<header class="gird-header">
    <div class="header-fixed">
        <div class="header-inner">
            <a href="javascript:void(0)" class="header-logo" id="logo">Chao.Sir</a>
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
        <div class="col-content" style="width:100%">
            <div class="inner">
                <article class="article-list">
                    <input type="hidden" value="4" id="blogtypeid" />
                    <section class="article-item">
                        <aside class="title" style="line-height:1.5;">
                            <h4 class="mt05 f-fwn">
                                NPOI导入导出Excel
                                <button class="layui-btn layui-btn-primary layui-btn-xs" id="btn-volume" title="静音"><i class="fa fa-volume-up fa-fw"></i></button>
                            </h4>
                            <p class="fc-grey fs-14">
                                <small>
                                    作者：<a href="javascript:void(0)" target="_blank" class="fc-link">燕十三</a>
                                </small>
                                <small class="ml10">围观群众：<i class="readcount">416</i></small>
                                <small class="ml10">更新于 <label>2018/4/24 15:05:14</label> </small>
                            </p>
                        </aside>
                        <div class="time mt10" style="padding-bottom:0;">
                            <span class="day">24</span>
                            <span class="month fs-18">4<small class="fs-14">月</small></span>
                            <span class="year fs-18">2018</span>
                        </div>
                        <div class="content artiledetail" style="border-bottom: 1px solid #e1e2e0; padding-bottom: 20px;">

                            <p>最近在项目上有需求要自定义导出列，想起以前用过的NPOI做过的导入导出就拿来做了些修改然后记录一下，声明全局静态方法：</p><p>1.导入excel到DataTable</p><pre><code>        /// &lt;summary&gt;<br>        /// 将excel导入到datatable<br>        /// &lt;/summary&gt;<br>        /// &lt;param name="filePath"&gt;excel路径&lt;/param&gt;<br>        /// &lt;param name="isColumnName"&gt;第一行是否是列名&lt;/param&gt;<br>        /// &lt;returns&gt;返回datatable&lt;/returns&gt;<br>        public static DataTable ExcelToDataTable(string filePath, bool isColumnName)<br>        {<br>            DataTable dataTable = null;<br>            FileStream fs = null;<br>            DataColumn column = null;<br>            DataRow dataRow = null;<br>            IWorkbook workbook = null;<br>            ISheet sheet = null;<br>            IRow row = null;<br>            ICell cell = null;<br>            int startRow = 1;//从下标为1的行开始填充<br>            try<br>            {<br>                using (fs = File.OpenRead(filePath))<br>                {<br>                    // 2007版本<br>                    if (filePath.IndexOf(".xlsx") &gt; 0)<br>                        workbook = new XSSFWorkbook(fs);<br>                    // 2003版本<br>                    else if (filePath.IndexOf(".xls") &gt; 0)<br>                        workbook = new HSSFWorkbook(fs);<br>                    if (workbook != null)<br>                    {<br>                        sheet = workbook.GetSheetAt(0);//读取第一个sheet，也能循环读取每个sheet<br>                        dataTable = new DataTable();<br>                        if (sheet != null)<br>                        {<br>                            int rowCount = sheet.LastRowNum + 1;//+1 包括列名的行，总行数<br>                            if (rowCount &gt; 0)<br>                            {<br>                                IRow firstRow = sheet.GetRow(0);//第一行<br>                                int cellCount = firstRow.LastCellNum;//列数<br>                                //构建datatable的列<br>                                if (isColumnName)<br>                                {<br>                                    for (int i = firstRow.FirstCellNum; i &lt; cellCount; ++i)<br>                                    {<br>                                        cell = firstRow.GetCell(i);<br>                                        if (cell != null)<br>                                        {<br>                                            if (cell.StringCellValue != null)<br>                                            {<br>                                                column = new DataColumn(cell.StringCellValue);<br>                                                dataTable.Columns.Add(column);<br>                                            }<br>                                        }<br>                                    }<br>                                }<br>                                else<br>                                {<br>                                    for (int i = firstRow.FirstCellNum; i &lt; cellCount; ++i)<br>                                    {<br>                                        column = new DataColumn("column" + (i + 1));<br>                                        dataTable.Columns.Add(column);<br>                                    }<br>                                }<br>                                //填充行（要读取列名则i&lt;rowCount 否则i &lt;= rowCount）<br>                                for (int i = startRow; i &lt; rowCount; ++i) <br>                                {<br>                                    row = sheet.GetRow(i);<br>                                    if (row == null) continue;<br><br>                                    dataRow = dataTable.NewRow();<br>                                    for (int j = row.FirstCellNum; j &lt; cellCount; ++j)<br>                                    {<br>                                        cell = row.GetCell(j);<br>                                        if (cell == null)<br>                                        {<br>                                            dataRow[j] = "";<br>                                        }<br>                                        else<br>                                        {<br>                                            switch (cell.CellType)<br>                                            {<br>                                                case CellType.Blank:<br>                                                    dataRow[j] = "";<br>                                                    break;<br>                                                case CellType.Numeric:<br>                                                    short format = cell.CellStyle.DataFormat;<br>                                                    if (format == 14 || format == 31 || format == 57 || format == 58)<br>                                                        dataRow[j] = cell.DateCellValue;<br>                                                    else<br>                                                        dataRow[j] = cell.NumericCellValue;<br>                                                    break;<br>                                                case CellType.String:<br>                                                    dataRow[j] = cell.StringCellValue;<br>                                                    break;<br>                                            }<br>                                        }<br>                                    }<br>                                    dataTable.Rows.Add(dataRow);<br>                                }<br>                            }<br>                        }<br>                    }<br>                }<br>                return dataTable;<br>            }<br>            catch (Exception ex)<br>            {
                LogRecord.LogCatch(ex, "导入excel到DataTable");//写日志<br>                if (fs != null)<br>                {<br>                    fs.Close();<br>                }<br>                return null;<br>            }<br>        }
</code></pre><p>2.导出DataTable到Excel（自定义列）</p><pre><code>        /// &lt;summary&gt;  <br>        /// 批量导出需要导出的列表
        /// &lt;param name="reqModel"&gt;查询条件&lt;/param&gt;
        /// &lt;param name="excelColunms"&gt;自定义的列名&lt;/param&gt;
        /// &lt;/summary&gt;  <br>        /// &lt;returns&gt;&lt;/returns&gt;  <br>        public FileResult UserExport(ReqQueryUsers reqModel, string excelColunms)<br>        {<br>            try<br>            {
                //获取数据
                var userlist = _bUser.SelectUsersExcel(reqModel);<br>                //创建Excel文件的对象  <br>                NPOI.HSSF.UserModel.HSSFWorkbook book = new NPOI.HSSF.UserModel.HSSFWorkbook();<br>                //添加一个sheet  <br>                NPOI.SS.UserModel.ISheet sheet1 = book.CreateSheet("Sheet1");<br>                NPOI.SS.UserModel.IRow row1 = sheet1.CreateRow(0);<br>                //可以设置各种样式字体颜色背景等，这里就不设置<br>                if (excelColunms != "")<br>                {<br>                    string[] colunmslist = excelColunms.Split(',');<br>                    //设置列名<br>                    for (var i = 0; i &lt; colunmslist.Count(); i++)<br>                    {<br>                        row1.CreateCell(i).SetCellValue(colunmslist[i].Trim());<br>                    }<br>                }
                //遍历列表
                for (int i = 0; i &lt; userlist.Count; i++)<br>                {<br>                    NPOI.SS.UserModel.IRow rowtemp = sheet1.CreateRow(i + 1);<br>                    for (var item = 0; item &lt; row1.Cells.Count(); item++)<br>                    {
                            //填充数据，根据实际情况处理       <br>                            //rowtemp.CreateCell(item).SetCellValue(userlist[i].UserName.ToString());    <br>                    }<br>                }<br>                // 写入到客户端   <br>                System.IO.MemoryStream ms = new System.IO.MemoryStream();<br>                book.Write(ms);<br>                ms.Seek(0, SeekOrigin.Begin);<br>                DateTime dt = DateTime.Now;<br>                string dateTime = dt.ToString("yyMMddHHmmssfff");<br>                string fileName = "查询结果" + dateTime + ".xls";<br><br>                return File(ms, "application/vnd.ms-excel", fileName);<br>            }<br>            catch (Exception ex)<br>            {<br>                LogRecord.LogCatch(ex,"自定义导出excel");<br>                throw ex;<br>            }<br>        }</code></pre>
                            <div class="copyright mt20">
                                <p class="f-toe fc-black">
                                    非特殊说明，本文版权归 燕十三 所有，转载请注明出处.
                                </p>
                                <p class="f-toe">
                                    本文标题：
                                    <a href="javascript:void(0)" class="r-title">燕十三</a>
                                </p>
                                <p class="f-toe">
                                    本文网址：
                                    <a href="https://www.yanshisan.cn/Blog/Read/8">https://www.yanshisan.cn/Blog/Read/8</a>
                                </p>
                            </div>
                            <div id="aplayer" style="margin:5px 0"></div>
                            <h6>延伸阅读</h6>
                            <ol class="b-relation"></ol>
                        </div>
                        <div class="bdsharebuttonbox share" data-tag="share_1">
                            <ul>
                                <li class="f-praise"><span><a class="s-praise"></a></span></li>
                                <li class="f-weinxi"><a class="s-weinxi" data-cmd="weixin"></a></li>
                                <li class="f-sina"><a class="s-sina" data-cmd="tsina"></a></li>
                                <li class="f-qq" href="https://www.yanshisan.cn/Blog/Read/8" title="NPOI导入导出Excel" desc="NPOI是指构建在POI 3.x版本之上的一个程序，NPOI可以在没有安装Office的情况下对Word或Excel文档进行读写操作。
NPOI是一个开源的C#读写Excel、WORD等微软OLE2组件文档的项目。" cover="https://www.yanshisan.cn//image/cover/201842913738408.jpg"><i class="fa fa-qq"></i></li>
                                <li class="f-qzone"><a class="s-qzone" data-cmd="qzone"></a></li>
                            </ul>
                        </div>
                        <div class="f-cb"></div>
                        <div class="mt20 f-fwn fs-24 fc-grey comment" style="border-top: 1px solid #e1e2e0; padding-top: 20px;">
                        </div>
                        <fieldset class="layui-elem-field layui-field-title">
                            <legend>发表评论</legend>
                            <div class="layui-field-box">
                                <div class="leavemessage" style="text-align:initial">
                                    <form class="layui-form blog-editor" action="">
                                        <input type="hidden" name="articleid" id="articleid" value="8">
                                        <div class="layui-form-item">
                                            <textarea name="editorContent" lay-verify="content" id="remarkEditor" placeholder="请输入内容" class="layui-textarea layui-hide"></textarea>
                                        </div>
                                        <div class="layui-form-item">
                                            <button class="layui-btn" lay-submit="formLeaveMessage" lay-filter="formLeaveMessage">提交留言</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </fieldset>
                        <ul class="blog-comment" id="blog-comment" page-count="1"></ul>
                    </section>
                </article>
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

<script src="/bundles/read?v=gP3tqgTWPgNg5vxu9L8KKyir3WfbwzEg3LRdbixQWwk1"></script>




<script>
    layui.use(['jquery'], function () {
        var $ = layui.jquery;
        var audio = new Array();
        var musicobj = [{"ID":12,"BlogID":8,"SongName":"锦鲤抄","SingerName":"银临","Url":"28138493","Cover":"http://p1.music.126.net/Lytc68amE03j7AbmoLQPUA==/19063332602423891.jpg","MusicType":2}];
        var playerurl;
        const ap = new APlayer({
            container: document.getElementById('aplayer'),
            autoplay: false,
            volume: 0.5
        });
        if (musicobj.length > 0) {
            playerurl = sessionStorage.getItem("songmid-" + musicobj[0].Url);
            if (playerurl == null) {
                if (musicobj[0].MusicType == 1) {//qq
                    var url = 'https://c.y.qq.com/base/fcgi-bin/fcg_music_express_mobile3.fcg?format=json&platform=yqq&cid=205361747&songmid=' + musicobj[0].Url + '&filename=C400' + musicobj[0].Url + '.m4a&guid=1684916857';
                    $.ajax({
                        url: url,
                        type: "get",
                        dataType: 'jsonp',
                        success: function (data) {
                            console.log(data.data.items[0].vkey);
                            playerurl = 'http://ws.stream.qqmusic.qq.com/' + data.data.items[0].filename + '?fromtag=66&guid=1684916857&vkey=' + data.data.items[0].vkey;
                            audio.push({
                                name: musicobj[0].SongName,
                                artist: musicobj[0].SingerName,
                                url: playerurl,
                                cover: musicobj[0].Cover
                            });
                            ap.list.clear();
                            ap.list.add(audio);
                            ap.play();
                            sessionStorage.setItem("songmid-" + musicobj[0].Url, playerurl);
                        },
                        error: function (e) {
                            console.log(e);
                        }
                    });
                } else if (musicobj[0].MusicType == 2) {//wy
                    playerurl = "http://music.163.com/song/media/outer/url?id=" + musicobj[0].Url + ".mp3";
                    audio.push({
                        name: musicobj[0].SongName,
                        artist: musicobj[0].SingerName,
                        url: playerurl,
                        cover: musicobj[0].Cover
                    });
                    ap.list.clear();
                    ap.list.add(audio);
                    ap.play();
                    sessionStorage.setItem("songmid-" + musicobj[0].Url, playerurl);
                }
            } else {
                audio.push({
                    name: musicobj[0].SongName,
                    artist: musicobj[0].SingerName,
                    url: playerurl,
                    cover: musicobj[0].Cover
                });
                ap.list.clear();
                ap.list.add(audio);
                ap.play();
            }
        }
        $("#btn-volume").click(function () {
            if ($(this).attr("title") == "开启") {
                ap.play();
                $(this).attr("title", "静音");
                $(this).children().attr("class", "fa fa-volume-up fa-fw");
            } else {
                ap.pause();
                $(this).attr("title", "开启");
                $(this).children().attr("class", "fa fa-volume-off fa-fw");
            }
        })
    });
</script>

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
