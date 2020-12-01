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
        url: "${pageContext.request.contextPath}/video/selectAll",  //分页查询   page  rows  total recoreds  page  rows
        editurl: "${pageContext.request.contextPath}/video/edit",
        datatype: "json",
        rowNum: 3,  //每页展示是条数
        rowList: [3, 5, 10, 20, 30],
        pager: '#pager',
        styleUI: "Bootstrap",
        height: "auto",
        autowidth: true,
        colNames: ["编号", "名称", "视频", "上传时间", "描述", "所属类别", "类别id", "用户id"],
        colModel: [
            {name: "id"},
            {name: "title", editable: true},
            {
                name: 'videoPath',
                editable: true,
                index: 'name asc, invdate',
                width: 200,
                align: "center",
                edittype: "file",
                formatter: function (cellvalue, options, rowObject) {
                    return "<video controls=\"controls\" src='" + cellvalue + "' width='300px' height='200px'>";
                }
            },
            {name: "uploadTime", editable: false},
            {name: "brief", editable: true},
            {name: "category.cateName"},
            {name: "category.id"},
            {name: "user.id", editable: false}
        ]

    });
    //处理曾删改查操作   工具栏
    $("#userTable").jqGrid('navGrid', '#pager',
        {edit: true, add: true, del: true, edittext: "修改", addtext: "添加", deltext: "删除"},
        {
            closeAfterEdit: true,  //关闭对话框
            beforeShowForm: function (obj) {
                obj.find("#videoPath").attr("disabled", true);//禁用input
            }
        }, //执行修改之后的额外操作
        {
            closeAfterAdd: true, //关闭添加的对话框
            afterSubmit: function (data) {
                $.ajaxFileUpload({
                    fileElementId: "videoPath",    //需要上传的文件域的ID，即<input type="file">的ID。
                    url: "${pageContext.request.contextPath}/video/uploadVideo", //后台方法的路径
                    type: 'post',   //当要提交自定义参数时，这个参数要设置成post
                    data: {id: data.responseText},  //responseText: "74141b4c-f337-4ab2-ada2-c1b07364adee"
                    success: function () {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                        //刷新页面
                        $("#userTable").trigger("reloadGrid");
                    }
                });
                //必须要有返回值
                return "hello";
            }
        }, //执行添加之后的额外操作
        {
            closeAfterDel: true, //关闭添加的对话框
        } //执行删除之后的额外操作
    );

</script>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h2 class="panel-title">视频展示</h2>
    </div>
    <br>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">视频管理</a></li>
    </ul>
    <br>

    <br>
    <%--表单--%>
    <table id="userTable"></table>

    <%--指定分页工具栏--%>
    <div id="pager"></div>


</div>

