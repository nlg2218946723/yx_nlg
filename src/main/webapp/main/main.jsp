<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>
    <script src="${path}/bootstrap/js/echarts.js"></script>
    <script src="${path}/bootstrap/js/china.js"></script>
    <script src="${path}/bootstrap/js/goeasy-1.0.5.js"></script>
</head>
<body>

<%--<h1 align="center">什么都没有了,全靠你们造啦O(∩_∩)O哈哈~</h1>--%>
<!--顶部导航-->
<nav class="navbar navbar-default navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <a class="navbar-brand" href="#">应学视频APP后台管理系统</a>

            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#" style="color: #2aabd2">欢迎：${sessionScope.admin.username}</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/logout">退出登录</a></li>
                <span class="glyphicon glyphicon-share" style="font-size: 40px"></span>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>


<div class="container-fluid">
    <!--栅格系统-->
    <div class="row">
        <!--左边手风琴部分-->
        <div class="col-sm-2">
            <!--菜单-->
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

                <!--面板-->
                <div class="panel panel-default panel-danger">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                               aria-expanded="true" aria-controls="collapseOne">
                                <span class="glyphicon glyphicon-apple"></span> 用户管理
                            </a>
                        </h4>
                    </div>
                    <!--面板内容-->
                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group text-center">
                                <li class="list-group-item"><a
                                        href="javascript:$('#mainId').load('${pageContext.request.contextPath}/user/queryUser.jsp')">用户展示</a>
                                </li>
                                <li class="list-group-item"><a
                                        href="javascript:$('#mainId').load('${pageContext.request.contextPath}/user/queryUser2.jsp')">用户统计</a>
                                </li>
                                <li class="list-group-item"><a
                                        href="javascript:$('#mainId').load('${pageContext.request.contextPath}/user/queryUser3.jsp')">用户分布</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <hr>
                <!--面板-->
                <div class="panel panel-default panel-info">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"
                               aria-expanded="true" aria-controls="collapseOne">
                                <span class="glyphicon glyphicon-apple"></span> 分类管理
                            </a>
                        </h4>
                    </div>
                    <!--面板内容-->
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a
                                        href="javascript:$('#mainId').load('${pageContext.request.contextPath}/category/queryCategory.jsp')">分类展示</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <hr>
                <!--面板-->
                <div class="panel panel-default panel-success">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree"
                               aria-expanded="true" aria-controls="collapseOne">
                                <span class="glyphicon glyphicon-apple"></span> 视频管理
                            </a>
                        </h4>
                    </div>
                    <!--面板内容-->
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a
                                        href="javascript:$('#mainId').load('${pageContext.request.contextPath}/video/queryVideo.jsp')">视频展示</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <hr>
                <!--面板-->
                <div class="panel panel-default panel-warning">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour"
                               aria-expanded="true" aria-controls="collapseOne">
                                <span class="glyphicon glyphicon-apple"></span> 日志管理
                            </a>
                        </h4>
                    </div>
                    <!--面板内容-->
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item">日志信息</li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!--巨幕开始-->
        <div class="col-sm-10" id="mainId">
            <div class="jumbotron">
                <h1>欢迎来到应学视频APP后台管理系统！</h1>
            </div>

            <!--创建轮播图-->
            <div id="carousel-example-generic" style="width: 100%;" class="carousel slide text-center"
                 data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="4"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="5"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="../foodimgs/1.jpg" style="height: 500px" alt="...">

                    </div>
                    <div class="item">
                        <img src="../foodimgs/2.jpg" style="height: 500px" alt="...">

                    </div>
                    <div class="item">
                        <img src="../foodimgs/3.jpg" style="height: 500px" alt="...">

                    </div>
                    <div class="item">
                        <img src="../foodimgs/4.jpg" style="height: 500px" alt="...">

                    </div>
                    <div class="item">
                        <img src="../foodimgs/5.jpg" style="height: 500px" alt="...">
                    </div>
                    <div class="item">
                        <img src="../foodimgs/6.jpg" style="height: 500px" alt="...">
                    </div>

                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                </a>

                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                </a>

            </div>

        </div>


    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <div class="row footer-bottom">
        <ul class="list-inline text-center">
            <li>@百知教育 nlg@168.com</li>
        </ul>
    </div>
</div>


<!--右边轮播图部分-->
<!--页脚-->
<!--栅格系统-->

</body>
</html>
