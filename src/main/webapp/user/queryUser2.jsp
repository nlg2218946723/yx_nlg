<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/19
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">

    /*初始化GoEasy对象*/
    var goEasy = new GoEasy({
        host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-2d098726607b42fbaf0e7cb536cedc4a", //替换为您的应用appkey
    });

    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        /*接收消息*/
        goEasy.subscribe({
            channel: "2005-nlg", //替换为您自己的channel
            onMessage: function (message) {
                var datas = message.content;

                //将json字符串转换为javascript对象
                var data = JSON.parse(datas);

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '用户注册信息'//标题
                    },
                    tooltip: {},//鼠标提示
                    legend: {
                        data: ['人数']//选项卡
                    },
                    xAxis: {  //横坐标
                        data: data.month
                    },
                    yAxis: {},
                    series: [{
                        name: '人数',
                        type: 'bar',
                        data: data.num
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        });
    });
</script>


<script type="text/javascript">


    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        $.get("${pageContext.request.contextPath}/echars/getUserData", function (data) {
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '用户注册信息'//标题
                },
                tooltip: {},//鼠标提示
                legend: {
                    data: ['人数']//选项卡
                },
                xAxis: {  //横坐标
                    data: data.month
                },
                yAxis: {},
                series: [{
                    name: '人数',
                    type: 'bar',
                    data: data.num
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }, "JSON");

    });
</script>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h2 class="panel-title">用户统计</h2>
    </div>
    <br>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>


</div>

