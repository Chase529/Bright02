var h=window.innerHeight
$(".content").css("height",h+"px")
//$(".album-image-md").css("width","100%")
$(".album-image-bd").css({"height":"100%",
	"width":"100%",
})
$("#album-image-md").css({"height":"100%",
	"width":"100%",
})
var move=$("#album-image").pano({  img: "imgs/01.jpg",
	interval:100,
	speed:42
})
var t=setTimeout(function(){
	move.moveRight()
},1000)
var neirong=window.localStorage.getItem("neirong")
if($(window).width()>760){
	$(".album-image-nav").css({
	"position":"fixed",
	"left":"0",
	"right":"0",
	"width":"100%",
	"bottom":"0",
	"top":"0",
	"margin":"auto",
	"height":"200px",
	"zIndex":"9999"
})
	$(".album-image-nav-left-block").css({
		height:"200px"
	})
	$(".album-image-nav-right-block").css({
		height:"200px"
	})
	$("#album-carousel").css({
		"top":"400px",
		"bottom":"0",
		"margin":"auto",
		"height":"200px"
	})
	$("#album-carousel li").css({
		"width":"4em",
		"height":"200px"
	})
}else{
//	$("#album-carousel").css({
//		"top":"0",
//		"bottom":"5em",
//		"margin":"auto"
//	})
}
$.ajax({
	type:"get",
	url:"http://192.168.1:8080/appProject/getProjectPano?projectId=599630481714452cbff456902d1794c4&uid=1",
	async:true
});
var rdata={
    "message": null,
    "total": 0,
    "data": [
        {
            "STATUS": 0,
            "CREATE_USER_ID": "",
            "RESOURCE_ID": "2d7222514dd6421bba375ca25241fc36",
            "RECORD_ID": "599630481714452cbff456902d1794c4",
            "CREATE_TIME": "2018-04-09 14:12:43",
            "TYPE": 1,
            "URL": "/uploadFiles/uploadImgs/20180409/b050465c51b6466d8b5e714b2910bdc3.jpg"
        },
        {
            "STATUS": 0,
            "CREATE_USER_ID": "",
            "RESOURCE_ID": "3200a77e6bd54870b94d3f3b4697555b",
            "RECORD_ID": "599630481714452cbff456902d1794c4",
            "CREATE_TIME": "2018-04-09 14:05:58",
            "TYPE": 1,
            "URL": "/uploadFiles/uploadImgs/20180409/848761229cb9424d84cbd3f3f1e85a49.jpg"
        },
        {
            "STATUS": 0,
            "CREATE_USER_ID": "",
            "RESOURCE_ID": "3822bbc1b3784b21b4511cff9bf6c543",
            "RECORD_ID": "599630481714452cbff456902d1794c4",
            "CREATE_TIME": "2018-04-09 14:05:58",
            "TYPE": 1,
            "URL": "/uploadFiles/uploadImgs/20180409/437bf8f081d541318791bed55879957f.jpg"
        },
        {
            "STATUS": 0,
            "CREATE_USER_ID": "",
            "RESOURCE_ID": "5fa0c06e4b314a14b588c2b0cb88babc",
            "RECORD_ID": "599630481714452cbff456902d1794c4",
            "CREATE_TIME": "2018-04-09 14:12:43",
            "TYPE": 1,
            "URL": "/uploadFiles/uploadImgs/20180409/08d6acb4769f4a8a9f095459dec321eb.jpg"
        },
        {
            "STATUS": 0,
            "CREATE_USER_ID": "",
            "RESOURCE_ID": "9d7186be073c476eb08eee8f9fcb467d",
            "RECORD_ID": "599630481714452cbff456902d1794c4",
            "CREATE_TIME": "2018-04-09 14:05:58",
            "TYPE": 1,
            "URL": "/uploadFiles/uploadImgs/20180409/a5386fe0aca64b65977a1aaf5cbcb61c.jpg"
        },
        {
            "STATUS": 0,
            "CREATE_USER_ID": "",
            "RESOURCE_ID": "de963855d69f4436b9f3dd95a554596d",
            "RECORD_ID": "599630481714452cbff456902d1794c4",
            "CREATE_TIME": "2018-04-09 14:12:43",
            "TYPE": 1,
            "URL": "/uploadFiles/uploadImgs/20180409/0c162fe5900f4a5d905714e91d66ebbc.jpg"
        }
    ],
    "success": true,
    "rows": [],
    "backUrl": null,
    "msg": null,
    "code": "0000",
    "footer": []
}
var htmls=""
runPicuture()
function runPicuture(){
	console.log(rdata)
	for(var i=0;i<rdata.data.length;i++) {
		htmls+=`
			 <li class="album-carousel-thumb"><a href="${"http://192.168.199.144:8080"+rdata.data[i].URL}"><img src="${"http://192.168.199.144:8080"+rdata.data[i].URL}" alt="相册图片-示例图片（6）" width="230" height="144" /></a></li>
		`
	}
	console.log(htmls)
	$("#album-carousel-list").html(htmls)
}
