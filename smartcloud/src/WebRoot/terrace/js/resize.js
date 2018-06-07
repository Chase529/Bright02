var t
var cityName
var h=window.innerHeight
		$(".pano").css("height",h+"px")
		$(".sikuais").css("z-index","9999999")

		var floa=true;
		(function ($) {
			var move=$("#myPano").pano({  img: "imgs/timg.jpg"})
			move.stopMoving=function(){

			}
			move.moveRight();
		}(jQuery));
	$('img').on("mousedown",function(e){
			e.preventDefault()
	});
		//地图
	echarts.registerMap('gansu', gansuJson);//hennanJson名称取自henan.js里的var  henanJson变量名
	var chart = echarts.init(document.querySelector("#mapMain"));
	window.onresize =function(){
		Resize()
	}
	Resize()
function Resize(){
// if($(window).width()>1530){
		var option = {
      title: {
       text : '',
       subtext : '',
       top:'20',
       textStyle: {
            fontSize: 18,
            color: '#333'          // 主标题文字颜色
        }
      },
      series: [
        {
        name: '数据名称',
        type: 'map',
        map: 'gansu',
        mapType: '甘肃',
        top:'65',
        zoom:1.1,
        selectedMode : 'single',
            label: {
                        normal: {
                            show: true,//显示省份标签
                            textStyle:{color:"#fbfdfe",
                            	fontSize:18,
                            	fontWeight : 'normal',
                            }//省份标签字体颜色
                        },
                        emphasis: {//对应的鼠标悬浮效果
                            show: true,
                            textStyle:{color:"white",
                            	fontSize:40,
                            	fontWeight : 'normal',
                            }
                        }
                    },
										data:[
											{name:'兰州市', value: 1800},
											{name:'张掖市', value: 1700},
											{name:'金昌市', value: 1600},
											{name:'武威市', value: 1400},
											{name:'酒泉市', value: 1200},
											{name:'嘉峪关市', value: 1000},
											{name:'白银市', value: 800},
											{name:'临夏回族自治州', value: 600},
											{name:'甘南藏族自治州', value: 400},
											{name:'天水市', value: 200},
											{name:'陇南市', value: 100},
											{name:'天水市', value: 300},
											{name:'平凉市', value: 500},
											{name:'陇南市', value: 700},
											{name:'庆阳市', value: 900},
											{name:'定西市', value: 1100}
                                ],
                    itemStyle: {
                        normal: {
                            borderWidth: 1,//区域边框宽度
                            borderColor: 'rgba(0,0,0,.9)',//区域边框颜色
                            areaColor:"rgba(0,0,0,.8)",//区域颜色

                        },

                        emphasis: {
                            borderWidth: 1,
                            borderColor: 'red',
                            areaColor:"red",
                        }
                    },

        }]
      };
	chart.setOption(option);
	chart.on('click', function (params) {//点击事件
		cityName=params.name
		run()
    if (params.componentType === 'series') {
    	$(".sikuaiBox").css({
    		"opacity":"1"
    	})
    	$(".showBox").css({
    		"display":"flex"
    	})
    	$(".floatBox").css({
		    		"display":"block"
		    	})
         var provinceName =params.name;
         $('#box').css('display','flex');
         $('#box').css({
         	"left":params.event.offsetX-$(window).width()/4+"px",
         	"top":params.event.offsetY-$(window).height()/8+"px"
         })
         $("#box").html(titleHtml);
        }
		 $(".btn").each(function(index,val){
					$(val).click(function(){
						if($(this).attr("data-name")==""){
							Common.alert("没有权限");
							return;
						}
						var index=$(this).attr("num")
						window.localStorage.setItem("projectMenu",JSON.stringify(data[0].projectList[index]))
			            window.location.href="show.html"
				})
			})
		makeBox()
		})
	makeBox()
	function makeBox(){
		

		$(".floatBox").css({
			"left":"12px",
			"right":"auto",
			"height":"80px",
			"width":"80px",
			"fontSize":"30px",
			"lineHeight":"80px"
		})
		$("#billboardImg").css({
            "height":"100%"
        })
		$("#mapMain").css({
			"width": "80%",
    		"position": "fixed",
    		"top": "200px",
    		"bottom": "0",
    		"left": "100px",
    		"height": "1000px",
    		"margin":"auto 0",
    		"z-index": "100",
    		"overflow":"hidden"
		})
		$("#mapMain canvas").css({
			"width":"100%",
			"left":"-450px",
			"height":"100%"
		})
		$(".showBox").css({
			"width":"22%",
			"right":"100px",
			"top":"0",
			"bottom":"0",
			"height":"80%",
			"margin":"auto 0",
			"flex-direction": "column",
		})
		$(".show").css({
			"width":"100%",
			"right":"15%",
			"top":"0",
			"bottom":"0",
			"margin":"auto 0",
			"height":"15%",
			"padding":"0",
			"display":"block",
		})
		$(".show li").css({
			"writing-mode":"horizontal-tb",
			"display":"block",
			"width":"100%",
			"fontSize":"25px",

		})
		$(".sikuaiBox").css({
			"width":"16%",
			"right":"23%",
			"top":"0",
			"bottom":"0",
			"margin":"auto",
			"flex-direction": "column",
			"height":"80%",
		})
		$(".sikuai").css({
			"height": "14%",
    		"width": "50%",
    		"margin": "auto",
    		"textAlign": "center",
    		"fontSize":"40px",
    		"lineHeight": "1.3",
    		"backgroundColor": "rgba(0,0,0,.5)",
    		"border": "1px solid black",
		})
	}
		
		chart.on("dblclick",function(parms){
			console.log(parms.name)
    		var projectList=data[0].projectList
    		window.localStorage.setItem("map",JSON.stringify(projectList))
			window.location.href="showindex.html"
		})
//	}else{
//		$(".sikuaiBox").css("display","none")
//		$("#billboardImg").css({
//          "background":"url("+project.img+".jpg"+") no-repeat center",
//          "backgroundSize":"cover",
//          "height":"100%"
//      })
//		$("#mapMain").css({
//			"top":"0",
//			"bottom":"0",
//			"margin":"auto"
//		})
//		$("#box-title").css({
//			"fontsize":".800px"
//		})
//		chart.on('click', function (params) {//点击事件
//		    if (params.componentType === 'series') {
//		    	$(".sikuaiBox").css({
//		    		"opacity":"1"
//		    	})
//		    	$(".floatBox").css({
//		    		"display":"block"
//		    	})
//		         var provinceName =params.name;
//		         $('#box').css('display','flex');
//		         $('#box').css({
//		         	"left":params.event.offsetX-$(window).width()/8+"px",
//       			"top":params.event.offsetY-$(window).height()/11+"px",
//		         	"height":"3.500px",
//		         	"width":"3.500px",
//		         	"fontSize":"100px",
//		         	"overflow":"hidden"
//		         })
//		         $(".box-title").css("font-size","100px")
//		         $("#box").html(`
//		         		<div class="box-title btn" id="">
//		            			项目1:景中八标
//		        		</div>
//		        		<div class="box-title btn" id="">
//		        			项目2:景中九标
//		        		</div>
//		        		<div class="box-title btn" id="">
//		        			项目3:两徽项目
//		        		</div>
//		         `);
//		        }
//			    $(".btn").each(function(index,val){
//						$(val).click(function(){
//							if($(this).attr("data-name")==""){
//								Common.alert("没有权限");
//								return;
//							}
//						appcan.locStorage.setVal("xiangMuMing",$(this).attr("data-name"));
//			            appcan.window.open({
//			                name: name,
//			                type: 1024,
//			                aniId: 2,
//			                data: "show.html",
//			                animDuration: 90
//			            });
//					})
//				})
//			})
//		chart.on("dblclick",function(){
//			appcan.window.open({
//              name: name,
//              type: 1024,
//              aniId: 2,
//              data: "showindex.html",
//              animDuration: 90
//          });
//		})
//
//
//		var option = {
//			      title: {
//			       text : '',
//			       subtext : '',
//			       top:'20',
//			       textStyle: {
//			            fontSize: 18,
//			            color: '#333'          // 主标题文字颜色
//			        }
//			      },
//			      series: [
//			        {
//			        name: '数据名称',
//			        type: 'map',
//			        map: 'gansu',
//			        mapType: '甘肃',
//			        top:'65',
//			        zoom:1.1,
//			        selectedMode : 'single',
//			            label: {
//	                        normal: {
//	                            show: true,//显示省份标签
//	                            textStyle:{color:"#fbfdfe",
//	                            	fontSize:22,
//	                            	fontWeight : 'normal',
//	                            }//省份标签字体颜色
//	                        },
//	                        emphasis: {//对应的鼠标悬浮效果
//	                            show: true,
//	                            textStyle:{color:"white",
//	                            	fontSize:20,
//	                            	fontWeight : 'normal',
//	                            }
//	                        }
//	                    },
//	                    itemStyle: {
//	                        normal: {
//	                            borderWidth: 2,//区域边框宽度
//	                            borderColor: 'rgba(0,0,0,.9)',//区域边框颜色
//	                            areaColor:"rgba(0,0,0,.5)",//区域颜色
//
//	                        },
//
//	                        emphasis: {
//	                            borderWidth: .5,
//	                            borderColor: 'red',
//	                            areaColor:"red",
//	                        }
//	                    },
//			        }]
//			      };
//				chart.setOption(option);
//				$("canvas").css({
//					"-webkit-transform": "scale(1)",
//				})
//		}
}
