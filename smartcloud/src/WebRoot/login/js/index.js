var chart

if($(window).width() < $(window).height()) {
	//竖屏
	
	$(".xia").css({
		"bottom": "0",
	})
	$(".you").css({
		"right": "100%",
	})
	$(".bigBox>.left").css({
		"height": "70%"
	})
	$(".left #mainmap>img").css({
		"width": "98%"
	})
    $(".left #mainmap").css({
        "bottom": "17%"
    })
	$(".boxTitle").css({
		"fontSize":".9rem"
	})
    $(".left .clickBox").css({
        "bottom":"27%"
    })
	// alert($(window).height())
	if($(window).height()<770){
        $(".left .floatBox").css({
            "width": "58%",
            "height":"125%"
        })
		$(".bigBox .xia .conBox .con .cons .right ul").css({
        	"padding-left":"5%",
				"list-style":"none",
		})
        $(".bigBox .xia .conBox .con .cons .right ul li").css({
                "list-style":"none",
        })
	}else{
        $(".bigBox>.left .floatBox").css({
            "height":"110%",
	    "top":"-134%"
        });
	}
} else if($(window).width()>$(window).height()) {
	//横屏
	$(".bigBox > .right").css({
		"display":"block"
	})
    $(".bigBox > .left").css({
        "width":"70%"
    })
	$(".xia").css({
		"bottom": "-100%"
	})
	$(".you").css({
		"right": "0"
	})
	$(".left #mainmap>img").css({
		"width": "100%"
	})

    $(".left .floatBox").css({
        "width": "88%",
		"bottom":"30%"
    })
    $(".bigBox>.left .floatBox").css({
        "height":"75%"
    });
}
$(".bigBox > .left .mapBox #mainmap .clickBox").css({
    "left":"auto",
    "right":"25%",
    "height":"1.5rem",
    " width":"1.5rem",
})
function makeNumber(){
	var  num1=Math.floor(Math.random()*50)
	var  num2=Math.floor(Math.random()*20)
	var  num3=Math.floor(Math.random()*100)
	var  num4=Math.floor(Math.random()*80)
	var  num5=Math.floor(Math.random()*80)
	return [num1,num2,num3,num4,num5]
}
var st=setInterval(function(){
	for (var i=0;i<makeNumber().length;i++) {
		$(".numbers").each(function(index,val){
			$(val).text(makeNumber()[i])
		})
	}
	$(".personnel").each(function (index,val) {
		if(Math.floor(Math.random()*100)%2==1){
            $(val).text($(val).text()/1-Math.floor(Math.random()*80));
		}else{
            $(val).text($(val).text()/1+Math.floor(Math.random()*80));
        }
    })
    $(".jindu").each(function (index,val) {
        $(val).text($(val).text()/1+1)
    })

},2000)
var data = [{
		name: '成都市',
		value: [{
				'name': '景中九标',
				'data': 'jiubiao'
			},
			{
				'name': '景中八标',
				'data': 'babiao'
			},
			{
				'name': '两徽项目',
				'data': 'lianghui'
			}
		]
	},
]
var move = $(".mapbg").pano({
	img: "img/mapindex.jpg",
	interval: 100,
	speed: 42
})
var t = 0
move.moveRight()
var c=0
document.querySelector(".clickBox").addEventListener("click", function(e) {
	c++
	console.log(c)
	if(c>2){
			window.location.href="showindex.html"
	}
	$(".left #mainmap>img").attr("src", "img/click.png")
	var htmls=""
	for (var j=0;j<data[0].value.length;j++) {
		if(data[0].value.length>0){
		htmls+=`
			<div class="title" val=${data[0].value[j].data}><span>${data[0].value[j].name}</span></div>
		`
		}
	}
			
	// htmls+=`
	// 		<!--<img src="img/three.png" alt="" class="three" />-->
	// `
	$(".bigBox>.left .floatBox").html(htmls)
	$(".three").css({
		"bottom":"-"+$(".three").height()+"px"
	})
	$(".floatBox").css({
		"display": "flex"
	})
//			var h=$('.floatBox').height()*1.5
//	if(h<104){
//			h=24
//			w=$('.floatBox').width()/2
//		}else{
//			w=$('.floatBox').width()/2
//			
//		}
//		console.log(h)
	$('.floatBox').css({
		"right": "0%",
		"left":e.offsetX-$('.clickBox').width()*0.6/2,
		"top": "-110%",
		"opacity":"1"
	})

	$('.floatBox .title').each(function(index,val){
		console.log(val,index);
		$(val).on("click",function(){
    		window.localStorage.setItem("neirong",$(this).attr("val"))
			window.location.href="show.html"
		})
	})
})
function map() {
	echarts.registerMap('gansu', chengduJson); //hennanJson名称取自henan.js里的var  henanJson变量名
	chart = echarts.init(document.querySelector("#mainmap"));
	var option = {
		title: {
			text: '',
			subtext: '',
			top: '20',
			textStyle: {
				fontSize: 18,
				color: '#333' // 主标题文字颜色
			}
		},
		series: [{
			name: '数据名称',
			type: 'map',
			map: 'gansu',
			mapType: '四川',
			top: '65',
			zoom: 1.1,
			clickable: false,
			selectedMode: 'single',
			label: {
				normal: {
					show: true, //显示省份标签
					textStyle: {
						color: "white",
//						fontSize: 18,
						fontWeight: 'normal',
					} //省份标签字体颜色
				},
				emphasis: { //对应的鼠标悬浮效果
					show: true,
					textStyle: {
						color: "white",
						fontSize: 30,
						fontWeight: 'normal',
					}
				}
			},
			data: [{
					name: '成都市',
					selected: true,
					value: [{
							'name': '景中九标',
							'data': '数据'
						},
						{
							'name': '景中八标',
							'data': '数据'
						},
						{
							'name': '两徽',
							'data': '数据'
						}
					]
				},
				{
					name: '巴中市',
					value: []
				},
				{
					name: '达州市',
					value: []
				},
				{
					name: '南充市',
					value: []
				},
				{
					name: '广安市',
					value: []
				},
				{
					name: '遂宁市',
					value: []
				},
				{
					name: '资阳市',
					value: []
				},
				{
					name: '绵阳市',
					value: []
				},
				{
					name: '德阳市',
					value: []
				},
				{
					name: '自贡市',
					value: []
				},
				{
					name: '泸州市',
					value: []
				},
				{
					name: '攀枝花市',
					value: []
				},
				{
					name: '内江市',
					value: []
				},
				{
					name: '乐山市',
					value: []
				},
				{
					name: '宜宾市',
					value: []
				},
				{
					name: '眉山市',
					value: []
				},
				{
					name: '雅安市',
					value: []
				},
				{
					name: '都江堰市',
					value: []
				},
				{
					name: '彭州市',
					value: []
				},
				{
					name: '邛崃市',
					value: []
				},
				{
					name: '崇州市',
					value: []
				},
				{
					name: '什邡市',
					value: []
				},
				{
					name: '绵竹市',
					value: []
				},
				{
					name: '峨眉山市',
					value: []
				},
				{
					name: '阆中市',
					value: []
				},
				{
					name: '万源市',
					value: []
				},
				{
					name: '江油市',
					value: []
				},
				{
					name: '华蓥市',
					value: []
				},
				{
					name: '简阳市',
					value: []
				},
				{
					name: '西昌市',
					value: []
				},
				{
					name: '甘孜藏族自治州',
					value: []
				},
				{
					name: '阿坝藏族羌族自治州',
					value: []
				},
				{
					name: '凉山彝族自治州',
					value: []
				}
			],
			itemStyle: {
				normal: {
					borderWidth: 1.8, //区域边框宽度
					borderColor: 'rgba(22, 22, 22,.6)', //区域边框颜色
					areaColor: "rgba(46, 46, 46,.6)", //区域颜色

				},
				emphasis: {
					borderWidth: 1,
					borderColor: 'rgba(219, 82, 70,.6)',
					areaColor: "rgba(243, 15, 33,.6)",
				}
			},
		}]
	};
	chart.setOption(option);
}
//	map()
var w = $(window).width()
window.onresize = function() {
	w = $(window).width()
	window.location.reload()
	//		map()
}
var cityName = ""
$(".bigBox>.right").css({
	"right": "0"
})
$(".bigBox>.left").css({
	"right": "30%"
})
//	chart.on('click', function(params) { //点击事件
//		if(params.name==cityName&&cityName=="成都市"){
//			window.location.href="showindex.html"
//		}
//		var provinceName = params.name;
//		var h
//		var  w
////		if(cityName==""){
//			h=104
//			w=$('.floatBox').width()/2
////		}else{
////			w=$('.floatBox').width()/2
////			h=$('.floatBox').height()*1.5
////			
////		}
//		cityName = params.name
//		chart.dispatchAction({  
//          	type: 'downplay'  
//      });
//		if(params.componentType === 'series') {
//			if (cityName=="成都市") {
//				$(".floatBox").css({
//					"display": "flex"
//				})
//				$('.floatBox').css({
//					"left": params.event.offsetX-w+ "px",
//					"top": params.event.offsetY-h*2+ "px",
//					"opacity":"1"
//				})
//				$(".bigBox>.left .floatBox").css({
//					"height":"20%"
//				})
//			} else{
//				
//				$('.floatBox').css({
//					"display":"none"
//				})
//			};
//			var htmls=""
//			for (var i=0;i<data.length;i++) {
//				if(data[i].name==params.name){
//					var val=data[i].value
//					if(val.length==0){
//						htmls=`
//								<div class="title"><span>无项目</span></div>
//							`
//					}
//					for (var j=0;j<val.length;j++) {
//						if(val.length>0){
//						htmls+=`
//							<div class="title"><span>${val[j].name}</span></div>
//						`
//						}
//					}
//					
//				}
//			}
//			htmls+=`
//					<img src="img/three.png" alt="" class="three" />
//			`
//			console.log(htmls)
//			$(".bigBox>.left .floatBox").html(htmls)
//			$(".three").css({
//				"bottom":"-"+$(".three").height()+"px"
//			})
//		}
//		$(".btn").each(function(index, val) {
//			$(val).click(function() {
//				if($(this).attr("data-name") == "") {
//					Common.alert("没有权限");
//					return;
//				}
//				var index = $(this).attr("num")
//				window.localStorage.setItem("projectMenu", JSON.stringify(data[0].projectList[index]))
//				window.location.href = "show.html"
//			})
//		})
//	})
var floa = true
$('.flotBox').css({
	'width':$(".flotBox img").width()+"px"
})
//	var leng=$(".boxTitle").length*.2*1.5
$(".flotBox").click(function() {
	console.log($(".box"))
	if(floa) {
		
		$(".box").css("left", "0")
	} else {
		$(".box").css("left", "-100%")
	}
	floa = !floa
})
window.onload=function(){
	// if($(".bgBOX").width()>980){
	// 	$(".bgBOX").css({
	// 		"width":"60%"
	// 	})
	// }
}
