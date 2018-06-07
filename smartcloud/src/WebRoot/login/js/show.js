var h=window.innerHeight
var w=$(window).width()
$(".content").css("height",h+"px")
//$(".album-image-md").css("width","100%")
$(".album-image-bd").css({"height":"100%",
	"width":"100%",
})
$("#album-image-md").css({"height":"100%",
	"width":"100%",
})
var move=$("#album-image").pano({  img: "img/01.jpg",
	interval:100,
	speed:60
})

var neirong=window.localStorage.getItem("neirong")
console.log(neirong)
var t=setTimeout(function(){
	move.moveRight()
},200)

$(".album-image-nav").css({
	"position":"fixed",
	"left":"0",
	"right":"0",
	"width":"100%",
	"bottom":"20em",
	"height":"1.5em",
	"zIndex":"9999"
})
var cons=["","personnel","progress","security","quality",""]
$(".album-carousel-thumb>a").each(function(index,val){
	$(val).attr("href","http://shfawo.cn:8080/"+neirong+"#pingtai="+cons[index])
})
change()
$(".btn").attr("href","http://shfawo.cn:8080/"+neirong+"#pingtai="+cons[1])
//$(".btn").click(function(){
//	$(this).attr("href","http://shfawo.cn:8080/"+neirong+"#pingtai="+cons[index])
//	console.log(texts)
////	$("#album-image").css("background-image","url("+texts+")")
////	$(this).css({
////		"display":"none"
////	})
//})
var alolNumber=0
$("#album-carousel-btn-next").click(function(){
	alolNumber+=w
	if(alolNumber>$("#album-carousel-list").width()-w/2){
		$("#album-carousel-list").animate({left:"0px"})
		alolNumber=0
		return
	}
	$("#album-carousel-list").animate({left:"-"+alolNumber+"px"})
})
$("#album-carousel-btn-prev").click(function(){
	alolNumber-=w
	if(alolNumber>0-w+1&&alolNumber<0){
		$("#album-carousel-list").animate({left:"0px"})
	}else if(alolNumber<0-w+1){
		alolNumber=$("#album-carousel-list").width()-w
		$("#album-carousel-list").animate({left:"-"+alolNumber+"px"})
	}else{
		$("#album-carousel-list").animate({left:"-"+alolNumber+"px"})
	}
})
window.onresize=function(){
	change()
	w=$(window).width()
	window.location.reload()
}
function change(){
	$("html").css({
		"height":"100%",
		"width":"100%"
	})
if($(window).width()>$(window).height()){
		$(".album-image-nav").css({
		"position":"fixed",
		"left":"0",
		"right":"0",
		"width":"100%",
		"bottom":"6em",
		"top":"0",
		"margin":"auto",
		"height":"1.5em",
		"zIndex":"9999"
	})
		$("#album-carousel").css({
			"bottom":"1rem",
			"height":"2em"
		})
//		$("#album-carousel li").css({
//			"width":"4em",
//			"height":"2em"
//		})
//		$("#album-carousel li img").css({
//			"width":"4em",
//		})
	}else{
	//	$("#album-carousel").css({
	//		"top":"0",
	//		"bottom":"5em",
	//		"margin":"auto"
	//	})
		$("#album-carousel").css({
			"bottom":"1rem",
			"height":"15%"
		})
	}
}
window.onload=function(){
	$("#album-carousel-list").css({
		"width":$(".album-carousel-thumb").width()*$(".album-carousel-thumb").length+(w/2)+"px"
	})
}
