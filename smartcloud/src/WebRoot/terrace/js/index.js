// (function($) {
  	$.ajax({
  		type:"get",
  		url:"http://localhost:8080/appProject/getInfoByMap?uid=1&mapBianma=0030101",
  		success:function(rdata){
  			console.log(rdata)
  		},
  		error:function(err){
  			console.log(err)
  		}
  	});


  	$(".floatBox").click(function(){
  			if(floa){
  				$(".floatBox .box").css("display","block")
  			}else{
  				$(".floatBox .box").css("display","none")
  			}
  			floa=!floa
  		})
  var data =[
      {
        "projectList": [
          {
            "LOCATION_BIANMA": "0030101",
            "LONGITUDE": "111",
            "PROJECT_URL": "http://192.168.199.133:8080",
            "LATITUDE": "222",
            "PROJECT_NAME": "大浩浩",
            "PROJECT_ID": "c2db0562ce5f4bd9a3794c7c56fc59c4"
          }, {
            "LOCATION_BIANMA": "0030101",
            "LONGITUDE": "111",
            "PROJECT_URL": "http://192.168.199.133:8080",
            "LATITUDE": "111",
            "PROJECT_NAME": "小浩浩",
            "PROJECT_ID": "f5fb8606d45e43dfb141d9c89b2522b2"
          }
        ]
      }, {
        "depPersonCount": 0,
        "environmentCount": 60,
        "destroyCount": 28,
        "projectCount": 2,
        "outputValue": 0,
        "sumProgress": 0,
        "securityHasCount": 0,
        "outStaffCount": 0,
        "securityDoCount": 0,
        "securityNotCount": 0,
        "securityCount": 0,
        "outCompanyCount": 0,
        "qualityNotCount": 0,
        "qualityHasCount": 0,
        "qualityDoCount": 0,
        "recoverCount": 32,
        "qualityCount": 0
      }
    ]
  var bianma="0030607"
function run() {
	repeat()
//  cityBianMa(cityName)
// 	$.ajax({
// 		type:"get",
// 		url:"",
// 		data:{
// 			uid:"",
// 			"bianma":bianma
// 		},
// 		success:function(rdata){
// 			data=rdata.data
// 			repeat()
// 		},
// 		error:function(err){
// 			console.log(err)
// 		}
// 	});
}
//function cityBianMa(city){
//	$.ajax({
//		type:"post",
//		url:"http://192.168.199.146:8080/appDictionary/getCities",
//		data:{"city":city},
//		success:function(rdata){
//			var data=rdata.data
//			bianma=data
//		},
//		error:function(err){
//			console.log(err)
//			bianma="0030607"
//		}
//	});
//}
var titleHtml=""
var show=""
function repeat(){
		var projectList=data[0].projectList	
		var projectNumber=data[1]
		titleHtml=""
	for (i in projectList) {
		titleHtml+=`
		<div class="box-title btn" id="" num="${i}">
            			项目${i/1+1}:${projectList[i].PROJECT_NAME}
        </div>
        `
	}

	show=`
	<ul class="show">
		<li>项目总数：${projectNumber.projectCount}个</li>
		<li>项目部人员：${projectNumber.depPersonCount}人</li>
		<li>施工班组：${projectNumber.outCompanyCount}个</li>
		<li>施工班组人员：${projectNumber.outStaffCount}人</li>
	</ul>
	<ul class="show">
		<li>项目总进度：${projectNumber.sumProgress}%</li>
		<li>项目总产值：${projectNumber.outputValue}亿</li>
	</ul>
	<ul class="show">
		<li>安全控制点：${projectNumber.securityCount}处</li>
		<li>已完成：${projectNumber.securityDoCount}处</li>
		<li>正在进行中：${projectNumber.securityHasCount}处</li>
		<li>未开始：${projectNumber.securityNotCount}处</li>
	</ul>
	<ul class="show">
		<li>质量控制点：${projectNumber.qualityCount}处</li>
		<li>已完成：${projectNumber.qualityDoCount}处</li>
		<li>正在进行中	：${projectNumber.qualityHasCount}处</li>
		<li>未开始：${projectNumber.qualityNotCount}处</li>
	</ul>
	<ul class="show">
		<li>环保监控点：${projectNumber.environmentCount}处</li>
		<li>已破坏监控点：${projectNumber.destroyCount}处</li>
		<li>正在恢复监控点：${projectNumber.recoverCount}处</li>
	</ul>
	`
	document.querySelector(".showBox").innerHTML=show
}




























  //	var isRemember = appcan.locStorage.getVal("isRemember");
  //	var serialNumber = appcan.locStorage.getVal("serialNumber");
  //  var userId = appcan.locStorage.getVal(Common.USER_ID);
  //  appcan.button("#nav-left", "btn-act",
  //  function() {});
  //  appcan.button("#nav-right", "btn-act",
  //  function() {});
  //获取项目信息
  //  var h=getClientHeight()-((document.querySelector("#Footer").offsetHeight+10)*100)
  //  	function getClientHeight()
  //			{
  //			  var clientHeight=0;
  //			  if(document.body.clientHeight&&document.documentElement.clientHeight)
  //			  {
  //			  var clientHeight = (document.body.clientHeight<document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
  //			  }
  //			  else
  //			  {
  //			  var clientHeight = (document.body.clientHeight>document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
  //			  }
  //			  return clientHeight;
  //			}
  //  $(".bigBox").css("height",h+"px")
  // var project = projectsInfo[appcan.locStorage.getVal(PROJECT_CODE)];
  // console.log(projectsInfo)
  // if(project) {
  // $("#billboardImg").attr("src", project.img);
  // $(".desc-content").html(project.desc);
  // console.log($("#projectDesc"))
  // $("#projectDesc div").html(project.name)
  //  var totalSec = 6;
  //  var timer = setInterval(function() {
  //  if(totalSec > 0) {
  //  totalSec--;
  //  $("#secTxt").text(totalSec);
  //  } else {
  //  $(".billboard").addClass("uhide");
  //  clearInterval(timer);
  //  }
  //  }, 1000);
  //
  // 隐藏展示信息
  // appcan.button(".btn-gohome", "", function() {
  // $(".billboard").addClass("uhide");
  //  if(timer) {
  //  clearInterval(timer);
  //  }
  // });
  //
  // appcan.button("#btnDesc", "", function() {
  // $("#projectDesc").removeClass("uhide");
  // });
  //
  // appcan.button("#projectDesc", "", function() {
  // $("#projectDesc").addClass("uhide");
  // });
  // } else {
  // $(".billboard").addClass("uhide");
  // }

  //定义应用展示模块默认字号大小
  //  var fontClass = "ulev2";
  //      首页菜单
  //       var menuItems = [{
  //            "urlName": "safe",
  //            "title": "施工安全",
  //            "icon": "imgs/color/index-02.png",
  //            "isShowIndex": 1//1显示 0不显示
  //        }, {
  //            "urlName": "quality2",
  //            "title": "施工质量",
  //            "icon": "imgs/color/index-03.png",
  //            "isShowIndex": 1//1显示 0不显示
  //        }, {
  //            "urlName": "schedule",
  //            "title": "施工进度",
  //            "icon": "imgs/color/index-04.png",
  //            "isShowIndex": 1//1显示 0不显示
  //        }, {
  //            "urlName": "",
  //            "title": "成本分析",
  //            "icon": "imgs/color/index-01.png",
  //            "isShowIndex": 1//1显示 0不显示
  //        }, {
  //            "urlName": "examinationApproval",
  //            "title": "审批",
  //            "icon": "imgs/color/index-22.png",
  //            "isShowIndex": 1//1显示 0不显示
  //        }, {
  //            "urlName": "personnel",
  //            "title": "人员管理",
  //            "icon": "imgs/color/index-05.png",
  //            "isShowIndex": 1
  //        }, {
  //            "urlName": "device",
  //            "title": "设备管理",
  //            "icon": "imgs/color/index-06.png",
  //            "isShowIndex": 1//1显示 0不显示
  //        }, {
  //            "urlName": "materials",
  //            "title": "物资管理",
  //            "icon": "imgs/color/index-07.png",
  //            "isShowIndex": 1//1显示 0不显示
  //        }, {
  //            "urlName": "girderReport",
  //            "title": "日报",
  //            "icon": "imgs/color/index-08.png",
  //            "isShowIndex": 1//1显示 0不显示
  //        }];
  //
  //  应用菜单
  //
  //
  //  var menuCateItems = [
  //  {
  //     "cate": "数据采集",
  //     "items": [{
  //         "urlName": "materials/mixStation",
  //         "title": "拌合站生产",
  //         "icon": "imgs/color/mixstation.png",
  //         "isShowIndex": 1
  //     },{
  //         "urlName": "materials/weighbridgeIndex",
  //         "title": "地磅数据",
  //         "icon": "imgs/color/weighbridge.png",
  //         "isShowIndex": 1
  //     },{
  //         "urlName": "monitoringVideo/videoList",
  //         "title": "视频监控",
  //         "icon": "imgs/color/monitor.png",
  //         "isShowIndex": 1
  //     },{
  //         "urlName": "",
  //         "title": "智能张拉",
  //         "icon": "imgs/color/line.png",
  //         "isShowIndex": 1
  //     }]
  //  },{
  //     "cate": "数据采集",
  //     "items": [{
  //         "urlName": "materials/mixStation",
  //         "title": "拌合站生产",
  //         "icon": "imgs/color/mixstation.png",
  //         "isShowIndex": 1
  //     },{
  //         "urlName": "materials/weighbridgeIndex",
  //         "title": "地磅数据",
  //         "icon": "imgs/color/weighbridge.png",
  //         "isShowIndex": 1
  //     },{
  //         "urlName": "monitoringVideo/videoList",
  //         "title": "视频监控",
  //         "icon": "imgs/color/monitor.png",
  //         "isShowIndex": 1
  //     },{
  //         "urlName": "",
  //         "title": "智能张拉",
  //         "icon": "imgs/color/line.png",
  //         "isShowIndex": 1
  //     }]
  //  },{
  //      "cate": "动态考勤",
  //      "items": [{
  //          "urlName": "",
  //          "title": "人员考勤",
  //          "icon": "imgs/color/index-17.png",
  //          "isShowIndex": 1
  //      },{
  //          "urlName": "",
  //          "title": "隧道定位",
  //          "icon": "imgs/color/index-16.png",
  //          "isShowIndex": 1//1显示 0不显示
  //      },{
  //          "urlName": "",
  //          "title": "特种设备",
  //          "icon": "imgs/color/index-23.png",
  //          "isShowIndex": 1//1显示 0不显示
  //      },{
  //          "urlName": "",
  //          "title": "高危施工",
  //          "icon": "imgs/color/work.png",
  //          "isShowIndex": 1
  //      }]
  //  },{
  //      "cate": "综合办公",
  //      "items": [{
  //          "urlName": "means",
  //          "title": "资料管理",
  //          "icon": "imgs/color/index-11.png",
  //          "isShowIndex": 1//1显示 0不显示
  //      },{
  //          "urlName": "technicalDifficulties",
  //          "title": "技术难点",
  //          "icon": "imgs/color/index-09.png",
  //          "isShowIndex": 1//1显示 0不显示
  //      }, {
  //          "urlName": "changeParameter",
  //          "title": "变更台账",
  //          "icon": "imgs/color/index-15.png",
  //          "isShowIndex": 1//1显示 0不显示
  //      },{
  //          "urlName": "",
  //          "title": "竣工资料",
  //          "icon": "imgs/color/index-10.png",
  //          "isShowIndex": 1
  //      },{
  //          "urlName": "examinationApproval",
  //          "title": "审批",
  //          "icon": "imgs/color/index-21.png",
  //          "isShowIndex": 1//1显示 0不显示
  //      },{
  //          "urlName": "contract",
  //          "title": "合同",
  //          "icon": "imgs/color/index-19.png",
  //          "isShowIndex": 1//1显示 0不显示
  //      },{
  //          "urlName": "engineeringSettlement",
  //          "title": "结算",
  //          "icon": "imgs/color/index-18.png",
  //          "isShowIndex": 1//1显示 0不显示
  //      },{
  //          "urlName": "",
  //          "title": "任务",
  //          "icon": "imgs/color/index-20.png",
  //          "isShowIndex": 1//1显示 0不显示
  //      },]
  //  },{
  //      "cate": "其他",
  //      "items": [{
  //          "urlName": "video",
  //          "title": "影像资料",
  //          "icon": "imgs/color/index-13.png",
  //          "isShowIndex": 1
  //      },{
  //          "urlName": "",
  //          "title": "天气",
  //          "icon": "imgs/color/index-12.png",
  //          "isShowIndex": 1//1显示 0不显示
  //      },{
  //          "urlName": "girder",
  //          "title": "智慧梁场",
  //          "icon": "imgs/color/index-14.png",
  //          "isShowIndex": 1
  //      },{
  //          "urlName": "",
  //          "title": "无人机观测",
  //          "icon": "imgs/color/plane.png",
  //          "isShowIndex": 1
  //      }]
  //  }];
  //  获取应用菜单
  //  get_menuCateItems()
  //  function get_menuCateItems(){
  //              $.ajax({
  //                  type:"get",
  //                  url:apiHost()+"/appMenu/showMenus",
  //                  url:apiHost()+"/appEngineering/getProjectCategoryList",
  //                  async:true,
  //                  data:{
  //                      "uid":userId
  //                  },
  //                  dataType:"json",
  //                  success:function(data){
  //                      var menuCateItems_ = data.data||menuCateItems;
  //                      var menuItems_ = data.data?data.data[0].items:menuItems;
  //                      _initHomePage(menuCateItems_,menuItems_)
  //
  //
  //                  },
  //                  error:function(e){
  //                      _initHomePage(menuCateItems,menuItems)
  //                      alert(data.message);
  //                  }
  //              });
  //  }
  //  appcan.ready(function() {
  //
  //      在IOS平台上使用小字号兼容
  //      if(uexWidgetOne.platformName == "iOS") {
  //            fontClass = "ulev0";
  //      }
  //
  //
  //      $.scrollbox($("body")).on("releaseToReload",
  //      function() { After Release or call reload function,we reset the bounce
  //          $("#ScrollContent").trigger("reload", this);
  //      }).on("onReloading",
  //      function(a) { if onreloading status, drag will trigger this event
  //      }).on("dragToReload",
  //      function() { drag over 30% of bounce height,will trigger this event
  //      }).on("draging",
  //      function(status) { on draging, this event will be triggered.
  //      }).on("release",
  //      function() { on draging, this event will be triggered.
  //      }).on("scrollbottom",
  //      function() { on scroll bottom,this event will be triggered.you should get data from server
  //          $("#ScrollContent").trigger("more", this);
  //      }).hide();
  //
  //       var c1c = 0;
  //
  //      uexWindow.setReportKey(0,1);  阻止返回键触发
  //      uexWindow.onKeyPressed = function(){
  //          if (c1c > 0) {
  //              uexWidgetOne.exit(0);
  //          }
  //          else {
  //              uexWindow.toast(0, 5, '再按一次退出应用', 1000);
  //              c1c=1; setTimeout(function(){ c1c=0; }, 2000);
  //          }
  //      };
  //
  //      tabview = appcan.tab({
  //          selector : $("#Footer"),
  //          hasIcon : true,
  //          hasAnim : false,
  //          hasLabel : true,
  //          hasBadge : false,
  //          index: 0,
  //          data : [{
  //              label : "主页",
  //              icon: "fa-home"
  //          }, {
  //              label : "应用",
  //              icon: "fa-th-large"
  //          }, {
  //              label : "通知",
  //              icon: "fa-comment"
  //          }, {
  //              label : "我的",
  //              icon: "fa-user"
  //          }]
  //      });
  //
  //      var initFlag = {
  //          "0": true,
  //          "1": false,
  //          "2": false,
  //          "3": false
  //      };
  //      _initHomePage();
  //      tabview.on('click',function(obj,index){
  //          if(!initFlag[index]) {
  //              switch(index) {
  //                  case 0:
  //                      _initHomePage();
  //                      break;
  //                  case 1:
  //                      _initWorkPage();
  //                      break;
  //                  case 2:
  //                      _initMessagePage();
  //                      break;
  //                  case 3:
  //                      _initMinePage();
  //                      break;
  //              }
  //              initFlag[index] = true;
  //          }
  //          $('.tab_pane').removeClass('active');
  //          $($('.tab_pane')[index]).addClass('active');
  //          $('.header').addClass('uhide');
  //          $($('.header')[index]).removeClass('uhide');
  //      });
  //
  //      获取技术创新数据
  //     getInnovateData();
  //
  //  });
  //
  //  初始化主页
  //  function _initHomePage(menuCateItems,menuItems) {
  //
  //        初始化进度看板数据
  //        getHomeCarouselList()
  //        getNewestProgress();
  //        setInterval(function() {
  //            getNewestProgress();
  //        }, 10000);
  //
  //        init进度看板
  //        mySwiper = new Swiper('#boardSlider', {
  //            autoplay: 1000,//可选选项，自动滑动
  //            direction: 'vertical'
  //        });
  //        进度看板end*/
  //
  //       首页菜单
  //       var menuItems_old = [{
  //            "urlName": "safe",
  //            "title": "施工安全",
  //            "icon": "imgs/color/index-02.png",
  //            "isShowIndex": 1//1显示 0不显示
  //        }, {
  //            "urlName": "quality2",
  //            "title": "施工质量",
  //            "icon": "imgs/color/index-03.png",
  //            "isShowIndex": 1//1显示 0不显示
  //        }, {
  //            "urlName": "schedule",
  //            "title": "施工进度",
  //            "icon": "imgs/color/index-04.png",
  //            "isShowIndex": 1//1显示 0不显示
  //        }, {
  //            "urlName": "",
  //            "title": "成本分析",
  //            "icon": "imgs/color/index-01.png",
  //            "isShowIndex": 0//1显示 0不显示
  //        }, {
  //            "urlName": "examinationApproval",
  //            "title": "审批",
  //            "icon": "imgs/color/index-22.png",
  //            "isShowIndex": 0//1显示 0不显示
  //        }, {
  //            "urlName": "personnel",
  //            "title": "人员管理",
  //            "icon": "imgs/color/index-05.png",
  //            "isShowIndex": 0
  //        }, {
  //            "urlName": "device",
  //            "title": "设备管理",
  //            "icon": "imgs/color/index-06.png",
  //            "isShowIndex": 1//1显示 0不显示
  //        }, {
  //            "urlName": "materials",
  //            "title": "物资管理",
  //            "icon": "imgs/color/index-07.png",
  //            "isShowIndex": 1//1显示 0不显示
  //        }, {
  //            "urlName": "girderReport",
  //            "title": "日报",
  //            "icon": "imgs/color/index-08.png",
  //            "isShowIndex": 1//1显示 0不显示
  //        }];
  //
  //        var html = "";
  //        首页应用图标
  //        for(var i = 0; i < menuItems.length; i++) {
  ////            if(menuItems[i].isShowIndex)
  //            html += '<div class="menu-item" data-title="'+menuItems[i].title+'" data-name="'+menuItems[i].urlName+'">' +
  //                      '<div class="home-menu-icon menu-icon-1">' +
  //                          '<img src="'+menuItems[i].icon+'" style="width:100%;"/>' +
  //                      '</div>' +
  //                      '<div class="menu-item-name">'+(menuItems[i].title || "&nbsp;")+'</div>' +
  //                    '</div>';
  //        }
  //
  //        html += '<div class="menu-item btn-more">' +
  //                  '<div class="home-menu-icon menu-icon-1"><img src="imgs/color/more.png" style="width:100%;"/></div>' +
  //                  '<div class="menu-item-name">更多</div>' +
  //                '</div>';
  //
  //        $("#appSlider").append(html);
  //        init app slider
  //        var appSwiper = new Swiper('#appSlider', {
  //            autoplay: 1000//可选选项，自动滑动
  //        });
  //
  //        初始化应用页面列表
  //        var menuHtml = "";
  //            console.log(menuCateItems)
  //
  //        for(var i = 1; i < menuCateItems.length; i++) {
  //            menuHtml += '<div class="menu-list bc-white uinn umar-t">';
  //            menuHtml += '<div class="menu-title">'+menuCateItems[i].cate+'</div>';
  //            var menuItems = menuCateItems[i].items;
  //                for(var j = 0; j < menuItems.length; j++) {
  //                    if(menuItems[j].isShowIndex){
  //                        menuHtml += '<div class="menu-item" data-title="'+menuItems[j].title+'" data-name="'+menuItems[j].urlName+'">' +
  //                                      '<div class="home-menu-icon menu-icon-1"><img src="'+menuItems[j].icon+'" style="width:100%;"/></div>' +
  //                                      '<div class="menu-item-name">'+menuItems[j].title+'</div>' +
  //                                '</div>';
  //                    }
  //
  //                }
  //                menuHtml+='</div>';
  //
  //
  //        }
  //
  //        $("#menuList").append(menuHtml);
  //
  //        菜单
  //        $('.menu-item').on("click", function(){
  //          var $self = $(this);
  //
  //          if($self.hasClass("btn-more")) {//跳转至应用界面
  //              tabview.moveTo(1);
  //              $('.tab_pane').removeClass('active');
  //              $($('.tab_pane')[1]).addClass('active');
  //              $('.header').addClass('uhide');
  //              $($('.header')[1]).removeClass('uhide');
  //              return;
  //          }
  //
  //          var name = $self.attr("data-name");
  //          var title = $self.attr("data-title");
  //          if(Common.util.isNullStr(name)){
  //              if(title != "") {
  //                  Common.tip("没有权限");
  //              }
  //              return;
  //          }
  //          var url = name + ".html";
  //          appcan.window.open({
  //              name: name,
  //              type: 1024,
  //              aniId: 2,
  //              data: url,
  //              animDuration: 90
  //          });
  //        });
  //
  //      注册去视频列表页面
  //      appcan.button("#goVideoList", "", function() {
  //          appcan.window.open({
  //              name: "video",
  //              type: 1024,
  //              aniId: 2,
  //              data: "video.html",
  //              animDuration: 90
  //          });
  //      });
  //
  //      初始化百度地图
  //      var map = new BMap.Map("allmap");     创建Map实例
  //      map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);   初始化地图,设置中心点坐标和地图级别
  //      添加地图类型控件
  //      map.addControl(new BMap.MapTypeControl({
  //          mapTypes:[
  //              BMAP_NORMAL_MAP,
  //              BMAP_HYBRID_MAP
  //          ]}));
  //      map.setCurrentCity("北京");// 设置地图显示的城市 此项是必须设置的
  //  }
  //
  //  查询最新进度看板数据
  //  function getNewestProgress() {
  //       var params = {
  //           "uid": userId
  //       };
  //
  //       Common.ajaxRequest({
  //         url: Common.apiList.getNewestProgress,
  //         type: "GET",
  //         data: params,
  //         before: function() {},
  //         success: function(rdata) {
  //             var data = rdata.data;
  //
  //             if(data != null && data.nameStr) {
  //                 var id = data.dailyRecordId;//日常ID
  //                 var isExistSlide = $(".swiper-slide[data-id='"+id+"']").length>0?true:false;
  //                 if(isExistSlide) return;//已存在相同数据
  //
  //                 var html = '<div class="swiper-slide" data-id="'+id+'">' +
  //                                  '<div class="ub schedule-item umar-t">' +
  //                                      '<div class="ub-f1 ub ub-ver tx-c ub-ac ulev-1">' +
  //                                          '<div class="ub-f1">'+(data.nameStr || "")+'</div>' +
  //                                          '<div class="ub-f1">完成数量&nbsp;<span class="sc-text-danger">1</span>&nbsp;</div>' +
  //                                          '<div class="ub-f1">'+(data.completeTime || "")+'</div>' +
  //                                      '</div>' +
  //                                      '<div class="ub-f2" style="padding-left: 0.2em;">' +
  //                                          '<div class="progress-rate tx-c" id="progressbar1"></div>' +
  //                                      '</div>' +
  //                                      '<div class="ub-f2 ulev-1 ub-ac umar-t" style="padding-left: 0.4em;">' +
  //                                          '<div>总数量：'+data.statisticsData.totalNumber+'</div>' +
  //                                          '<div>已完成：'+data.statisticsData.completedNumber+'</div>' +
  //                                          '<div>未完成：'+data.statisticsData.unfinishedNumber+'</div>' +
  //                                      '</div>' +
  //                                  '</div>' +
  //                             '</div>';
  //
  //                 mySwiper.appendSlide(html);
  //
  //                 var itemNum = $("#boardSlider .swiper-slide").length;
  //                 计算完成率
  //                 var rate = parseFloat(data.statisticsData.completedNumber/data.statisticsData.totalNumber).toFixed(2)*100;
  //                 if(rate > 100) rate = 100;
  //                 初始化进度条
  //                 showProgress('#progressbar'+itemNum, rate);
  //             }else{
  //                 var html = '<div class="swiper-slide" data-id="'+id+'">' +
  //                                  '<div class="ub schedule-item umar-t">' +
  //                                      '<div class="ub-f1 ub ub-ver tx-c ub-ac ulev-1">' +
  //                                          '<div class="ub-f1">桥涵工程-系梁</div>' +
  //                                          '<div class="ub-f1">完成数量&nbsp;<span class="sc-text-danger">1</span>&nbsp;</div>' +
  //                                          '<div class="ub-f1">2018-03-25</div>' +
  //                                      '</div>' +
  //                                      '<div class="ub-f2" style="padding-left: 0.2em;">' +
  //                                          '<div class="progress-rate tx-c" id="progressbar1"></div>' +
  //                                      '</div>' +
  //                                      '<div class="ub-f2 ulev-1 ub-ac umar-t" style="padding-left: 0.4em;">' +
  //                                          '<div>总数量：2000</div>' +
  //                                          '<div>已完成：1000</div>' +
  //                                          '<div>未完成：1000</div>' +
  //                                      '</div>' +
  //                                  '</div>' +
  //                             '</div>';
  //
  //                 mySwiper.appendSlide(html);
  //
  //                 var itemNum = $("#boardSlider .swiper-slide").length;
  //                 计算完成率
  //                 var rate = parseFloat(1000/2000).toFixed(2)*100;
  //                 if(rate > 100) rate = 100;
  //                 初始化进度条
  //                 showProgress('#progressbar'+itemNum, rate);
  //             }
  //         }
  //       });
  //  }
  //  查询轮播图图片
  // function getHomeCarouselList() {
  //    var params = {
  //        "uid": userId,
  //        "status":"1"
  //    };
  //    Common.ajaxRequest({
  //      url: Common.apiList.getHomeCarouselList,
  //      type: "GET",
  //      data: params,
  //      before: function() {},
  //      success: function(rdata) {
  //
  //          var data = rdata.data;
  //          var str=""
  //          for (var i=0; i < data.length; i++) {
  //              str+=`
  //            <div class="swiper-slide" style="background-image:url(${apiHost()+"/"+data[i].FILEPATH});background-position:center;background-size: cover;background-repeat: no-repeat;background-position:top;"></div>
  //           `
  //          str+='<div class="swiper-slide"><img style="width:100%;height:100%" src="'+apiHost()+"/"+data[i].FILEPATH+'" /></div>';
  //          };
  //          document.querySelector("#carouselSlider>.swiper-wrapper").innerHTML+=str
  //           var carouselSwiper = new Swiper("#carouselSlider", {
  //                 autoplay: 1000,
  //                 loop : true,
  //                 autoplayDisableOnInteraction : false,
  //                 pagination: {
  //                   el: '.swiper-pagination',
  //                 }
  //             });
  //             $(".swiper-slide").css("height","100%")
  //      }
  //    });
  // }
  //  通过进度看板进入施工进度页面
  //  appcan.button("#progressBoard", "", function() {
  //      appcan.window.open({
  //          name: "scheduleProgress",
  //          type: 1024,
  //          aniId: 2,
  //          data: "schedule/scheduleProgress.html",
  //          animDuration: 90
  //      });
  //  });
  //
  //  初始化工作界面
  //  function _initWorkPage(){
  //
  //  }
  //
  //  初始化通知界面
  //  var messageList = null;
  //  function _initMessagePage(){
  //      messageList = appcan.listview({
  //          selector: "#messageList",
  //          type: "thickLine",
  //          hasIcon:true,
  //          hasAngle: false,
  //          hasSubTitle: true,
  //          multiLIne: 1
  //      });
  //
  //      loadMessage([]);
  //
  //      messageList.on("click", function(obj, data, subObj){
  //          appcan.window.open({
  //              name: "msgDetail",
  //              type: 1024,
  //              aniId: 2,
  //              data: "message/msgDetail.html",
  //              animDuration: 90
  //          });
  //      });
  //  }
  //
  //  function loadMessage(data) {
  //      messageList.set([]);
  //
  //  }
  //
  //  初始化“我的”界面
  //  function _initMinePage() {
  //      var lv1 = appcan.listview({
  //          selector : "#listview1",
  //          type : "thickLine",
  //          hasIcon : true,
  //          hasAngle : true,
  //          hasSubTitle : false,
  //          multiLine : 1
  //      });
  //
  //      getUserInfo(function(data){
  //      	console.log(data.AVATAR)
  //          lv1.set([{
  //              name : 'userinfo',
  //              url : 'userinfo.html',
  //              icon : data.AVATAR?(apiHost()+"/"+data.AVATAR):'css/res/appcan_s.png',
  //              title : '<div class="ub"><div class="umar-ar3">'+(data.PHONE || "")+'</div></div>',
  //              describe : (data.DEPARTMENT_NAME || "无部门")+'，'+(data.NAME || "")
  //          }]);
  //      });
  //
  //      lv1.on("click", function(obj, data, subObj){
  //          if(data.name != null && data.url != null) {
  //              appcan.window.open({
  //                  name: data.name,
  //                  type: 1024,
  //                  aniId: 2,
  //                  data: data.url,
  //                  animDuration: 90
  //              });
  //          }
  //      });
  //
  //      var lv2 = appcan.listview({
  //          selector : "#listview2",
  //          type : "thinLine",
  //          hasIcon : false,
  //          hasAngle : true,
  //          hasSubTitle : true,
  //          multiLine : 1
  //      });
  //      lv2.set([{
  //          name: 'attendance',
  //          url: 'attendance.html',
  //          icon : 'info/css/myImg/myImg1.png',
  //          title : '<i class="fa fa-map-marker tx-c mine-list-icon"></i>考勤',
  //          subTitle : ''
  //      }, {
  //          name: 'settings',
  //          url: 'settings.html',
  //          icon : 'info/css/myImg/myImg2.png',
  //          title : '<i class="fa fa-cogs tx-c mine-list-icon"></i>设置',
  //          subTitle : ''
  //      }]);
  //
  //      lv2.on("click", function(obj, data, subObj){
  //          if(data.name != null && data.url != null) {
  //              appcan.window.open({
  //                  name: data.name,
  //                  type: 1024,
  //                  aniId: 2,
  //                  data: data.url,
  //                  animDuration: 90
  //              });
  //          }
  //      });
  //
  //      退出登录
  //      appcan.button("#btnLogout", "ani-act", function(){
  //              appcan.locStorage.remove(Common.USER_ID);
  //              appcan.locStorage.remove(API_HOST_KEY);
  //              appcan.locStorage.remove(Common.PROJECT_CODE);
  //              appcan.locStorage.remove()
  //          window.location.href="login.html"
  //      });
  //  }
  //
  //  获取用户信息
  //  function getUserInfo(callback) {
  //      var userId = appcan.locStorage.getVal(Common.USER_ID);
  //
  //      Common.ajaxRequest({
  //          url: Common.apiList.getUserInfo,
  //          data: {"USER_ID": userId},
  //          success: function(rdata) {
  //              var data = rdata.data;
  //              callback(data);
  //          }
  //      });
  //  }
  //
  //  进度看板环形控件
  //  function showProgress(target, precent) {
  //      var circle = new ProgressBar.Circle(target, {
  //        color: '#888',
  //         This has to be the same size as the maximum width to
  //         prevent clipping
  //        strokeWidth: 12,
  //        trailWidth: 8,
  //        trailColor: "#FC7800",
  //        easing: 'easeInOut',
  //        duration: 1400,
  //        text: {
  //          autoStyleContainer: false
  //        },
  //        from: { color: '#1400ff', width: 10 },
  //        to: { color: '#1400ff', width: 10 },
  //         Set default step function for all animate calls
  //        step: function(state, circle) {
  //          circle.path.setAttribute('stroke', state.color);
  //          circle.path.setAttribute('stroke-width', state.width);
  //
  //          var value = precent;
  //          circle.setText("<span style='display:block;'>已完成%</span><span>"+value+"</span>");
  //        }
  //      });
  //
  //      circle.text.style.fontFamily = 'sans-serif';
  //      circle.text.style.fontSize = '0.5em';
  //
  //      circle.animate(precent/100);   Number from 0.0 to 1.0
  //  }
  //
  //  跳转到技术创新列表
  //  appcan.button("#newTech", "", function() {
  //      appcan.window.open({
  //            name:"innovate",
  //            type: 1024,
  //            aniId: 2,
  //            data: "innovate.html",
  //            animDuration: 90
  //      });
  //  });
  //  获取技术创新数据
  //  function getInnovateData(){
  //       var params = {
  //           "uid": userId,
  //           "pageCode": 1,
  //           "pageSize": 2
  //       };
  //       Common.ajaxRequest({
  //         url: Common.apiList.getInnovateData,
  //         type: "GET",
  //         data: params,
  //         success: function(rdata) {
  //             var list = rdata.data || [];
  //             var html="";
  //             console.log(rdata.data)
  //             for(var i = 0; i < list.length; i++) {
  //                 html += '<div class="ub-f1 ub innovate-item umar-b" data-id="'+list[i].TECHNOLOGY_INNOVATION_ID+'">'+
  //                              '<div class="ufl ub-box-4" >'+
  //                                  '<img src="'+(apiHost()+"/"+list[i].URL)+'" style="width: 100%;"/>'+
  //                              '</div>'+
  //                              '<div class="ufl ulev-1 umar-l ub-box-4x">'+
  //                                  '<div class="umh3" >'+
  //                                      '<strong>'+list[i].TITLE+'</strong>'+
  //                                  '</div>'+
  //                                  '<div class="ub umar-t umh3">'+
  //                                      '<div class="ub-f1 tx-l ub-box-2" >'+(list[i].SUBTITLE || "")+'</div>'+
  //                                      '<div class="ub-f1 tx-r ub-box-2" >'+list[i].CREATE_TIME.split(" ")[0]+'</div>'+
  //                                  '</div>'+
  //                                  '<div class="umar-t">关键词：'+list[i].ABSTRACTS+'</div>'+
  //                              '</div>'+
  //                          '</div>';
  //             }
  //
  //             if(html == "") {
  //                 html = "<p class='tx-c'>暂无数据</p>";
  //             }
  //
  //             $("#innovate").append(html);
  //
  //            $(".innovate-item").on("click",function(){
  //                var id = $(this).attr("data-id");
  //                appcan.locStorage.setVal("id", id);
  //                appcan.window.open({
  //                    name:"innovateDetail",
  //                    type: 1024,
  //                    aniId: 2,
  //                    data: "innovate/innovateDetail.html",
  //                    animDuration: 90
  //                });
  //            });
  //         }
  //
  //       });
  //  }
  //getProjectInfo()
  //  初始化过渡页的项目信息
  //  function getProjectInfo(){
  //      var params = {
  //          "uid":userId
  //      };
  //      Common.ajaxRequest({
  //          url:Common.apiList.getProjectInfo,
  //          type:"GET",
  //          data:params,
  //          success: function(rdata){
  //              var data = rdata.data || {};
  //              console.log(rdata)
  //              $(".desc-content").html(data.DESCRIPTION);
  //              $("#projectTitle").html(data.NAME);
  //          }
  //      });
  //  }

// })($);
