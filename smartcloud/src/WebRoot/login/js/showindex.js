//var project=appcan.locStorage.getVal("xiangMuMing");
//初始化百度地图
var h=window.innerHeight
var maps=[{
	name:"景中九标",
	PROJECT_NAME:"jiubiao"
},{
	name:"两徽项目",
	PROJECT_NAME:"lianghui"
},{
	name:"景中八标",
	PROJECT_NAME:"babiao"
},]
$("#allMap").css("height",h+"px")
        var map = new BMap.Map("allMap");    // 创建Map实例
        map.centerAndZoom(new BMap.Point(103.836209,36.068402),18);  // 初始化地图,设置中心点坐标和地图级别
        //添加地图类型控件
        map.addControl(new BMap.MapTypeControl({
            mapTypes:[
                BMAP_NORMAL_MAP, BMAP_HYBRID_MAP
            ]}));     
        map.setCurrentCity("成都");// 设置地图显示的城市 此项是必须设置的
        var points = [  
			{"lng":103.744223,"lat":36.13419,"url":"http://www.baidu.com","id":1,"name":"p1"},  
			{"lng":103.642463,"lat":36.097337,"url":"http://www.mi.com","id":2,"name":"p2"},  
			{"lng":103.836209,"lat":36.068402,"url":"http://www.mi.com","id":2,"name":"p2"},  
			]; 
			addMarker(points)
		function addMarker(points) {
    		//循环建立标注点
    		var con=[]
		    for(var i=0, pointsLen = points.length; i<pointsLen; i++) {
		        var point = new BMap.Point(points[i].lng, points[i].lat); //将标注点转化成地图上的点
		        var marker = new BMap.Marker(point); //将点转化成标注点
		        map.centerAndZoom(point, 13);

		        map.addOverlay(marker);  //将标注点添加到地图上
		        var label = new BMap.Label(`<p>${maps[i].PROJECT_NAME}</p><p>现场照片:<a href='#' class='run' onclick='run()' >点击查看</p>`, {
			        offset : new BMap.Size(-85, -120)
			    });
			    console.log(maps[i].PROJECT_NAME)
			    con.push(`<p>${maps[i].name}</p><p>现场照片:<a href='#' class='run' onclick='run()' >点击查看</p>`);
		        //添加监听事件
		          (function() {
		          	var index=i
		            var thePoint = points[i];
		            marker.addEventListener("click",
		            //显示信息的方法
		                function() {
		                	console.log(con);
		                	var index=
		                	console.log(i)
		                	window.localStorage.setItem("neirong",maps[i-2].PROJECT_NAME)
		                showInfo(this,thePoint,con[i-2]);
		            });
		         })();
		    }
		}
		function clickpath(){
			$(".BMap_Marker BMap_noprint").each(function(index,val){
				console.log()
			})
			
		}
		function showInfo(thisMarker,point,text) {
		    //获取点的信息
		    var sContent = text
		    var infoWindow = new BMap.InfoWindow(sContent); //创建信息窗口对象
		    thisMarker.openInfoWindow(infoWindow); //图片加载完后重绘infoWindow
		}
//		    var label = new BMap.Label(text, {
//		        offset : new BMap.Size(-85, -120)
//		    });
//		    //设置label(标注的样式)
//		    label.setStyle({
//		        color : "black",
//		        fontSize : "1px",
//		        height : "80px",
//		        //lineHeight : "20px",
//		        fontFamily : "微软雅黑",
//		        maxWidth : "none",
//		        border : "none",display:"none"
//		    });
//		    marker.setLabel(label); 
			function run(){
//				appcan.window.open({
//	                name: name,
//	                type: 1024,
//	                aniId: 2,
//	                data: "show.html",
//	                animDuration: 90
//	            });
	            window.location.href="show.html#"
			}
		    //给标记添加点击事件以及显示窗口信息
//		     var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + text + "</p>");
//		     marker.addEventListener("click", function () { this.openInfoWindow(infoWindow);});