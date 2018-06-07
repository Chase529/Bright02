<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<!-- jsp文件头和头部 -->
<%@ include file="../index/top.jsp"%>
<!-- 百度echarts -->
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
<script type="text/javascript">
setTimeout("top.hangge()",500);
</script>
<style type="text/css">
	.btn-more {
		border: 1px solid #fff;
		border-radius: 10px;
		padding: 2px 10px;
		margin-top: 10px;
	}
	
	.tab-list {
		margin-top: 5px;
		padding: 4px 0;
	}
	
	.tab-list .tab-item {
		border:1px solid #fff;
		box-sizing: border-box;
		padding: 2px 10px;
		text-align: center;
		color: #fff;
		margin-right: 5px;
		float: left;
		cursor: pointer;
	}
	
	.tab-list .tab-item.active {
		background-color: rgba(255,255,255,.5);
	}
	
	.chart-title {
		color: #fff;
		font-size: 14px;
	}
	
	.chart-title:before {
		content:" ";
		border: 3px solid #3366FF;
		margin-right: 4px;
		border-top: none;
		border-bottom: none;
	}
	
	.border-white {
		border: 1px solid #a7a4a4;
	}
	
	.warn-item {
		padding: 1px;
		box-sizing: border-box;
	}
	
	.warn-item-bd {
		padding: 5px 0;
		background-color: rgba(204,204,204,.5);
	}
	
	.warn-icon {
		text-align: center;
		padding: 5px 0;
	}
	
	.warn-icon img{
		width: 35px;
		height: 35px;
		border-radius: 50%;
		padding: 5px;
	}
	
	.water-bg {
		background-color: #3333FF;
	}
	
	.monitor-bg {
		background-color: #FF0033;
	}
	
	.temp-bg {
		background-color: #FF6600;
	}
	
	.warn-info {
		color: #fff;
	}
	
	.dashboard-item {
		padding: 1px;
	}
	
	.dashboard-item .dashboard-bd {
		background-color: #000 !important;
	}
	
	.dashboard-title {
		color: #fff;
		padding: 4px 0;
		text-align: center;
	}
	
</style>
</head>
<body class="no-skin">

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content" style="background-color: #333;">
					<div class="row" style="padding: 5px;">

					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->

		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../index/foot.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$(top.hangge());
	</script>
<script type="text/javascript" src="static/ace/js/jquery.js"></script>
<script src="https://open.ys7.com/sdk/js/1.3/ezuikit.js"></script>
<script>
	$(function() {
		//地图
		var mapChart = echarts.init(document.getElementById("mapChart"));
		initMapChart(mapChart);
		
		//物资
		var chart2 = echarts.init(document.getElementById("chart2"));
		initChart2(chart2);
		
		//主体工程材料使用情况
		var chart3 = echarts.init(document.getElementById("chart3"));
		initChart3(chart3);
		
		//专业队伍情况
		var chart4 = echarts.init(document.getElementById("chart4"));
		initChart4(chart4);
		
		//警报器情况
		var chart5 = echarts.init(document.getElementById("chart5"));
		initChart5(chart5);
		
		var dash1 = echarts.init(document.getElementById("dashboard1"));
		initDashboard1(dash1);
		
		//温度
		var dash2 = echarts.init(document.getElementById("dashboard2"));
		initDashboard({
			"target": dash2,
			"color": "#33FF99",
			"unit": "℃",
			"value": 0.651
		});
		
		//氧气
		var dash3 = echarts.init(document.getElementById("dashboard3"));
		initDashboard({
			"target": dash3,
			"color": "#33CC33",
			"unit": "%",
			"value": 0.455
		});
		
		//一氧化碳
		var dash4 = echarts.init(document.getElementById("dashboard4"));
		initDashboard({
			"target": dash4,
			"color": "#993399",
			"unit": "ppm",
			"value": 0.305
		});
		
		//一氧化碳
		var dash5 = echarts.init(document.getElementById("dashboard5"));
		initDashboard({
			"target": dash5,
			"color": "#9933FF",
			"unit": "ppm",
			"value": 0.158
		});
		
		//甲烷
		var dash6 = echarts.init(document.getElementById("dashboard6"));
		initDashboard({
			"target": dash6,
			"color": "#FFCC00",
			"unit": "ppm",
			"value": 0.108
		});
		
		//报警统计
		var chartLeft = echarts.init(document.getElementById("chartLeft"));
		initChartLeft(chartLeft);
		
		//报警趋势
		var chartRight = echarts.init(document.getElementById("chartRight"));
		initChartRight(chartRight);
		
		var player = new EZUIPlayer('myPlayer');
	    player.on('error', function(){
	        console.log('error');
	    });
	    player.on('play', function(){
	        console.log('play');
	    });
	    player.on('pause', function(){
	        console.log('pause');
	    });
	    
	    registeEvents();
	});
	
	function registeEvents() {
		$(".tab-list").on("click", ".tab-item", function(){
			$(".tab-item").removeClass("active");
			$(this).addClass("active");
		});
	}
	
	function initMapChart(chart) {
		var provinces = ['shanghai', 'hebei','shanxi','neimenggu','liaoning','jilin','heilongjiang','jiangsu','zhejiang','anhui','fujian','jiangxi','shandong','henan','hubei','hunan','guangdong','guangxi','hainan','sichuan','guizhou','yunnan','xizang','shanxi1','gansu','qinghai','ningxia','xinjiang', 'beijing', 'tianjin', 'chongqing', 'xianggang', 'aomen'];
		var provincesText = ['上海', '河北', '山西', '内蒙古', '辽宁', '吉林','黑龙江',  '江苏', '浙江', '安徽', '福建', '江西', '山东','河南', '湖北', '湖南', '广东', '广西', '海南', '四川', '贵州', '云南', '西藏', '陕西', '甘肃', '青海', '宁夏', '新疆', '北京', '天津', '重庆', '香港', '澳门'];

		function showProvince() {
		    var name = provinces[currentIdx];

		    // myChart.showLoading();

		    $.get('plugins/echarts/map/' + name + '.json', function (geoJson) {

		        // chart.hideLoading();

		        echarts.registerMap(name, geoJson);
		        
		        var geoCoordMap = {
		                '呼伦贝尔': [333.00, 88.21],
		            }
		            var data = [{
		                name: '呼伦贝尔',
		                value: 199
		            }];
		        
		        var max = 480,
		        min = 9; // todo 
			    var maxSize4Pin = 100,
			        minSize4Pin = 20;
	
			    var convertData = function(data) {
			        var res = [];
			        for (var i = 0; i < data.length; i++) {
			            var geoCoord = geoCoordMap[data[i].name];
			            if (geoCoord) {
			                res.push({
			                    name: data[i].name,
			                    value: geoCoord.concat(data[i].value)
			                });
			            }
			        }
			        return res;
			    };

		        chart.setOption({
		            title: {
		                text: '',
		                left: 'center',
		                textStyle: {
		                    color: '#fff'
		                }
		            },
		            geo: {
		                show: true,
		                map: 'hunan',
		                label: {
		                    normal: {
		                        show: false
		                    },
		                    emphasis: {
		                        show: false,
		                    }
		                },
		                roam: true,
		                itemStyle: {
		                    normal: {
		                        areaColor: '#031525',
		                        borderColor: '#3B5077',
		                    },
		                    emphasis: {
		                        areaColor: '#2B91B7',
		                    }
		                }
		            },
		            series: [
		                {
		                    type: 'map',
		                    mapType: name,
		                    label: {
		                    	show: false,
		                        emphasis: {
		                            textStyle: {
		                                color: '#fff'
		                            }
		                        }
		                    },
		                    itemStyle: {
		                        normal: {
		                            borderColor: '#389BB7',
		                            areaColor: '#fff',
		                        },
		                        emphasis: {
		                            areaColor: '#389BB7',
		                            borderWidth: 0
		                        }
		                    },
		                    animation: false
		                    // animationDurationUpdate: 1000,
		                    // animationEasingUpdate: 'quinticInOut'
		                },{
			                name: '点',
			                type: 'scatter',
			                coordinateSystem: 'geo',
			                symbol: 'pin',
			                symbolSize: function(val) {
			                    var a = (maxSize4Pin - minSize4Pin) / (max - min);
			                    var b = minSize4Pin - a * min;
			                    b = maxSize4Pin - a * max;
			                    return a * val[2] + b;
			                },
			                label: {
			                    normal: {
			                        show: true,
			                        textStyle: {
			                            color: '#fff',
			                            fontSize: 9,
			                        }
			                    }
			                },
			                itemStyle: {
			                    normal: {
			                        color: '#F62157', //标志颜色
			                    }
			                },
			                zlevel: 6,
			                data: convertData(data),
			            }
		            ]
		        });
		    });
		}

		var currentIdx = 3;

		var option = {
		    series: []
		};

		showProvince();
		
		if (option && typeof option === "object") {
		    chart.setOption(option, true);
		}
	}
	
	function initChart2(chart) {
		var option = {
		    title: {
		        text: ''
		    },
		    tooltip: {},
		    legend: {
		        data: ['物资使用情况'],
		    	right: 0,
		    	orient: "vertical",
		    	textStyle: {
		    		color: "#fff"
		    	}
		    },
		    grid: {
		    	left: "5%",
		    	top: "5%",
		    	right: "5%",
		    	bottom: "5%"
		    },
		    radar: {
		    	radius: "60%",
		    	// shape: 'circle',
		        name: {
		            textStyle: {
		                color: '#fff',
		                backgroundColor: '#999',
		                borderRadius: 3,
		                padding: [3, 5]
		           }
		        },
		        indicator: [
		           { name: '沙', max: 6500},
		           { name: '外加剂', max: 16000},
		           { name: '5-10', max: 30000},
		           { name: '10-20', max: 38000},
		           { name: '20-25', max: 52000},
		           { name: '粉煤灰', max: 25000}
		        ]
		    },
		    series: [{
		        name: '',
		        type: 'radar',
		        // areaStyle: {normal: {}},
		        itemStyle: {normal: {areaStyle: {type: 'default'}}},
		        data : [
		            {
		                value : [4300, 10000, 28000, 35000, 50000, 19000],
		                name : '物资使用情况'
		            }
		        ]
		    }]
		};
		
		chart.setOption(option);
	}
	
	function initChart3(chart) {
		var option = {
		    title: {
		        text: ''
		    },
		    tooltip: {},
		    legend: {
		        data: ['物资使用情况'],
		    	right: 0,
		    	orient: "vertical",
		    	textStyle: {
		    		color: "#fff"
		    	}
		    },
		    grid: {
		    	left: "5%",
		    	top: "5%",
		    	right: "5%",
		    	bottom: "5%"
		    },
		    radar: {
		    	radius: "60%",
		    	// shape: 'circle',
		        name: {
		            textStyle: {
		                color: '#fff',
		                backgroundColor: '#999',
		                borderRadius: 3,
		                padding: [3, 5]
		           }
		        },
		        indicator: [
		           { name: '沙', max: 6500},
		           { name: '外加剂', max: 16000},
		           { name: '5-10', max: 30000},
		           { name: '10-20', max: 38000},
		           { name: '20-25', max: 52000},
		           { name: '粉煤灰', max: 25000}
		        ]
		    },
		    series: [{
		        name: '',
		        type: 'radar',
		        itemStyle: {normal: {areaStyle: {type: 'default'}}},
		        // areaStyle: {normal: {}},
		        data : [
		            {
		                value : [4300, 10000, 28000, 35000, 50000, 19000],
		                name : '物资使用情况'
		            }
		        ]
		    }]
		};
		
		chart.setOption(option);
	}
	
	function initChart4(chart) {
		var option = {
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        textStyle: {
		        	color: "#fff"
		        },
		        x: 'right',
		        data:['1队','2队','3队','4队','5队','6队','7队','8队','9队','10队']
		    },
		    series: [
		        {
		            name:'访问来源',
		            type:'pie',
		            selectedMode: 'single',
		            radius: [0, '50%'],
		            label: {
		            	show: false
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:[
		            	{value:335, name:'1队'},//, selected: true
		                {value:310, name:'2队'},
		                {value:234, name:'3队'},
		                {value:135, name:'4队'},
		                {value:1048, name:'6队'},
		                {value:251, name:'7队'},
		                {value:147, name:'8队'},
		                {value:102, name:'9队'}
		            ]
		        },
		        {
		            name:'访问来源',
		            type:'pie',
		            radius: ['60%', '75%'],
		            label: {
		            	show: false
		            },
		            data:[
		                {value:335, name:'1队'},
		                {value:310, name:'2队'},
		                {value:234, name:'3队'},
		                {value:135, name:'4队'},
		                {value:1048, name:'5队'},
		                {value:251, name:'6队'},
		                {value:147, name:'7队'},
		                {value:102, name:'8队'}
		            ]
		        }
		    ]
		};
		
		chart.setOption(option);
	}
	
	function initChart5(chart) {
		var option = {
			    title : {
			        text: '',
			        subtext: ''
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['正常','异常'],
			        textStyle: {
			        	color: '#fff'
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : ['1月','2月','3月','4月'],
			            axisLine: {
			            	lineStyle: {
			            		color: '#fff'
			            	}
			            }
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            axisLine: {
			            	lineStyle: {
			            		color: '#fff'
			            	}
			            }
			        }
			    ],
			    series : [
			        {
			            name:'正常',
			            type:'bar',
			            data:[2.0, 4.9, 7.0, 23.2],
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            },
			            itemStyle: {
			            	color: "#00FF99"
			            }
			        },
			        {
			            name:'异常',
			            type:'bar',
			            data:[2.6, 5.9, 9.0, 26.4],
			            markPoint : {
			                data : [
			                    {name : '最高', value : 182.2, xAxis: 3, yAxis: 26.4},
			                ]
			            },
			            itemStyle: {
			            	color: "#EEEEEE"
			            }
			        }
			    ]
			};
		
		chart.setOption(option);
	}
	
	//气压图
	function initDashboard1(chart) {
		option = {
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    series: [
		        {
		        	name: "",
		            type: 'gauge',
		            detail: {formatter:'{value}\nMpa', fontSize:10,padding:[15,0,0,0]},
		            data: [{value: 50, name: ''}],
		            axisLabel: {
		            	show: false
		            },
		            axisLine: {
		                show: true,
		                lineStyle: {
		                    width: 5,
		                    shadowBlur: 0
		                }
		            },
		            splitLine: {
		            	show: false
		            },
		            axisTick: {
		            	show: false
		            },
		            pointer: {
		            	width: 3
		            }
		        }
		    ]
		};
		
		chart.setOption(option);
	}
	
	//仪表盘
	function initDashboard(params) {
		var option = {
		    tooltip: {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    series: [{
		        name: '',
		        type: 'gauge',
		        radius: '70%',
		        startAngle: -90,
		        endAngle: 269.9,
		        clockwise: true,
		        axisLine: {
		            show: false,
		            lineStyle: { // 属性lineStyle控制线条样式
		                color: [
		                    [1-params.value, params.color],
		                    [1, 'rgba(128, 128, 128, 0.5)']
		                ],
		                width: 5
		            }
		        },
		        splitLine: {
		            show: false
		        },
		        axisTick: {
		            show: false
		        },
		        axisLabel: {
		            show: false
		        },
		        pointer: {
		            show: false,
		            length: '0',
		            width: '0'
		        },
		        detail: {
		            formatter: '{value}\n'+params.unit,
		            offsetCenter: [0, '5%'],
		            fontSize:12,
		            color: "#fff"
		        },
		        data: [{
		        	value: parseFloat(params.value*100).toFixed(2),
		            label: {
		                textStyle: {
		                	color: "#fff",
		                    fontSize: 12
		                }
		            }
		        }]
		    }]
		};
		
		params.target.setOption(option);
	}
	
	//报警统计图表
	function initChartLeft(chart) {
		var option = {
			tooltip: {
				show: true
			},
			grid: {
				left: "15%",
				top: "20%",
				right: 0
			},
			xAxis: {
		        type: 'category',
		        data: ['渗漏水', '入侵', '湿度', '气压', 'O2', 'CO'],
		        axisLine: {
		        	lineStyle: {
			        	color: "#fff"
			        }
		        },
		        axisLabel: {
		        	color: "#fff",
		        	interval: 0,
		        	fontSize: 10
		        },
		        axisTick: {
		        	show: false
		        }
		    },
		    yAxis: {
		        name: "报警统计",
		    	type: 'value',
		        axisLine: {
		        	lineStyle: {
			        	color: "#fff"
			        }
		        },
		        axisLabel: {
		        	color: "#fff"
		        },
		        axisTick: {
		        	show: false
		        }
		    },
		    series: [{
		        data: [{
		        	name: "渗漏水",
		        	value: 10,
		        	itemStyle: {
		        		color: "#3399FF"
		        	}
		        },{
		        	name: "入侵",
		        	value: 50,
		        	itemStyle: {
		        		color: "#33FF99"
		        	}
		        },{
		        	name: "湿度",
		        	value: 20,
		        	itemStyle: {
		        		color: "#33CC33"
		        	}
		        },{
		        	name: "气压",
		        	value: 100,
		        	itemStyle: {
		        		color: "#993399"
		        	}
		        },{
		        	name: "O2",
		        	value: 150,
		        	itemStyle: {
		        		color: "#9933FF"
		        	}
		        },{
		        	name: "CO",
		        	value: 220,
		        	itemStyle: {
		        		color: "#FFCC00"
		        	}
		        }],
		        type: 'bar',
		        barWidth: "40%"
		    }]
		};
		
		chart.setOption(option);
	}
	
	function initChartRight(chart) {
		var option = {
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		    	show: false,
		        data:['灾防','环境质量','设备故障']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        top: "20%",
		        bottom: "20%",
		        containLabel: true
		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: ['00','01','02','03','04','05','06'],
		        axisLine: {
		        	lineStyle: {
			        	color: "#fff"
			        }
		        },
		        axisLabel: {
		        	color: "#fff",
		        	interval: 0,
		        	fontSize: 10
		        },
		        axisTick: {
		        	show: false
		        }
		    },
		    yAxis: {
		    	name: "报警趋势",
		    	type: 'value',
		        axisLine: {
		        	lineStyle: {
			        	color: "#fff"
			        }
		        },
		        axisLabel: {
		        	color: "#fff",
		        	interval: 0,
		        	fontSize: 10
		        },
		        axisTick: {
		        	show: false
		        }
		    },
		    series: [
		        {
		            name:'灾防',
		            type:'line',
		            stack: '总量',
		            data:[120, 132, 101, 134, 90, 230, 210]
		        },
		        {
		            name:'环境质量',
		            type:'line',
		            stack: '总量',
		            data:[220, 182, 191, 234, 290, 330, 310]
		        },
		        {
		            name:'设备故障',
		            type:'line',
		            stack: '总量',
		            data:[150, 232, 201, 154, 190, 330, 410]
		        }
		    ]
		};

		chart.setOption(option);
	}
</script>
</body>
</html>