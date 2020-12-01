<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<script>

    $(function () {
        pageInit();
    });

    function pageInit() {
        $("#cateTable").jqGrid({
            url: "${pageContext.request.contextPath}/category/selectAllLevelsOne",
            datatype: "json",
            rowNum: 8,
            rowList: [8, 10, 20, 30],
            pager: '#catePage',
            sortname: 'id',
            viewrecords: true,
            styleUI: "Bootstrap",
            autowidth: true,
            height: "auto",
            colNames: ['id', '名称', '级别', '父类别id'],
            colModel: [
                {name: 'id', index: 'id', width: 55},
                {name: 'cateName', index: 'invdate', width: 90, editable: true, search: false},
                {name: 'levels', index: 'name', width: 100},
                {name: 'parentId', index: 'amount', width: 80, align: "right"},
            ],
            editurl: "${pageContext.request.contextPath}/category/edit",
            subGrid: true,  //开启子表格
            // subgrid_id:是在创建表数据时创建的div标签的ID
            //row_id是该行的ID
            subGridRowExpanded: function (subgrid_id, row_id) {
                addSubGrid(subgrid_id, row_id);
            }
        });
        $("#cateTable").jqGrid('navGrid', '#catePage', {add: true, edit: true, del: true},
            {
                closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,
            },  //对编辑配置
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
            },  //对添加配置
            {
                closeAfterDelete: true, //不生效
                afterSubmit: function (data) {

                    console.log(data)

                    //设置提示信息
                    $("#message").html(data.responseJSON.message);

                    //展示警告框
                    $("#showMsg").show();

                    setTimeout(function () {
                        $("#showMsg").hide();
                    }, 3000);

                    return "hello";
                }
            }  //对删除配置
        );
    }


    //开启子表格的样式
    function addSubGrid(subgridId, rowId) {

        var subgridTableTd = subgridId + "Table";
        var pagerId = subgridId + "Page";


        $("#" + subgridId).html("" +
            "<table id='" + subgridTableTd + "' />" +
            "<div id='" + pagerId + "' />"
        );


        $("#" + subgridTableTd).jqGrid({
            url: "${path}/category/queryByTwoCategory?cateId=" + rowId,
            datatype: "json",
            rowNum: 20,
            pager: "#" + pagerId,
            sortname: 'num',
            sortorder: "asc",
            styleUI: "Bootstrap",
            autowidth: true,
            height: "auto",
            editurl: "${pageContext.request.contextPath}/category/edit?parentId=" + rowId,
            colNames: ['id', '名称', '级别', '父类别id'],
            colModel: [
                {name: "id", index: "num", width: 80, key: true},
                {name: "cateName", index: "item", width: 130, editable: true},
                {name: "levels", index: "qty", width: 70, align: "right"},
                {name: "parentId", index: "unit", width: 70, align: "right"}
            ]
        });

        $("#" + subgridTableTd).jqGrid('navGrid', "#" + pagerId, {add: true, edit: true, del: true},
            {
                closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,
            },  //对编辑配置
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
            },  //对添加配置
            {
                closeAfterDelete: true, //不生效
                // reloadAfterSubmit: true,
                afterSubmit: function (data) {
                    console.log(data)

                    //设置提示信息
                    $("#message").html(data.responseJSON.message);

                    //展示警告框
                    $("#showMsg").show();

                    setTimeout(function () {
                        $("#showMsg").hide();
                    }, 3000);

                    return "hello";
                }
            }  //对删除配置
        );
    }


</script>


<%--设置面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>类别信息</h2>
    </div>
    <%--警告框--%>
    <div id="showMsg" style="width:300px;display: none" class="alert alert-warning alert-dismissible" role="alert">
        <span id="message"/>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">类别管理</a></li>
    </ul>

    <%--表单--%>
    <table id="cateTable"/>

    <%--分页工具栏--%>
    <div id="catePage"/>

</div>

<%--
    删除要有提示信息
--%>
