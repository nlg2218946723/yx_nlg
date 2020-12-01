<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/19
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<script>
    //初始化jqgrid


    $("#userTable").jqGrid({
        rowList: [3, 5, 10, 20, 30],
        styleUI: "Bootstrap",//使用bootstrap主题
        url: "${pageContext.request.contextPath}/user/selectAll",//指定服务器端地址
        edit: "${pageContext.request.contextPath}/user/edit",
        datatype: "json",//指定返回数据类型
        colNames: ["编号", "头像", "名字", "状态", "手机号", "注册时间"],
        colModel: [
            {name: "id"},
            {
                name: 'picImg', index: 'name asc, invdate', width: 200, align: "center",
                formatter: function (cellvalue, options, rowObject) {
                    return "<img src='" + cellvalue + "' width='300px' height='200px'>";
                }
            },
            {name: "nickName", editable: true},
            {
                name: 'status', index: 'total', width: 80, align: "center",
                formatter: function (cellvalue, options, rowObject) {
                    if (cellvalue == 0) {
                        return "<button class='btn btn-danger' onclick='changeStatus(\"" + rowObject.id + "\",\"" + rowObject.status + "\")'>解除冻结</button>";
                    } else {
                        return "<button class='btn btn-success' onclick='changeStatus(\"" + rowObject.id + "\",\"" + rowObject.status + "\")'>冻结</button>";
                    }
                }
            },
            {name: "phone", editable: true, search: false},
            {name: "createDate"},

        ],
        height: "auto",
        pager: "#pager",
        rowNum: 2,
        viewrecords: true,
        autowidth: true,
    });

    function changeStatus(id, status) {

        if (status == 0) {
            $.post("${pageContext.request.contextPath}/user/edit", {
                "id": id,
                "status": "1",
                "oper": "edit"
            }, function (data) {
                //刷新页面
                $("#userTable").trigger("reloadGrid");
            }, "text");
        } else {
            $.post("${pageContext.request.contextPath}/user/edit", {
                "id": id,
                "status": "0",
                "oper": "edit"
            }, function (data) {
                //刷新页面
                $("#userTable").trigger("reloadGrid");
            }, "text");
        }
    }

    $(function () {
        $.post("${pageContext.request.contextPath}/user/easyPoi", function (data) {

        }, "JSON");
    });
</script>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h2 class="panel-title">用户展示</h2>
    </div>
    <br>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">用户管理</a></li>
    </ul>
    <br>

    <div>
        <button class="btn btn-info" type="button" id="btn1">导出用户信息</button>
        <button class="btn btn-success" type="button">导入用户</button>
        <button class="btn btn-danger" type="button">测试按钮</button>
    </div>
    <br>
    <%--表单--%>
    <table id="userTable"></table>

    <%--指定分页工具栏--%>
    <div id="pager"></div>


</div>

