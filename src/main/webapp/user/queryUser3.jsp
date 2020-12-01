<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/19
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">

    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));


        $.get("${pageContext.request.contextPath}/echars/city", function (datas) {
            var series = [];

            for (var i = 0; i < datas.length; i++) {

                var data = datas[i];

                series.push(
                    {
                        name: '用户分布情况',
                        type: 'map',
                        mapType: 'china',
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: data.citys
                    }
                )
            }

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '用户分布情况',
                    subtext: '纯属虚构',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['用户分布情况']
                },
                visualMap: {
                    min: 0,
                    max: 2000,
                    left: 'left',
                    top: 'bottom',
                    text: ['高', '低'],           // 文本，默认为数值文本
                    calculable: true
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                series: series
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

        }, "json");

    });
</script>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h2 class="panel-title">用户分布</h2>
    </div>
    <br>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>


</div>

