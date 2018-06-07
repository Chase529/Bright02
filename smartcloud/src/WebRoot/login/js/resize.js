//function bodyScale(){
//var devicewidth=document.documentElement.clientWidth;
//var scale=devicewidth/1024;
//document.body.style.fontSize=scale;
//console.log(scale)
//}
//window.onload=window.onresize=function(){
//	bodyScale();
//}
(function(doc, win, undefined) {
	var docEl = doc.documentElement,
		resizeEvt = 'orientationchange' in win ? 'orientationchange' : 'resize',
		recalc = function() {
			var clientWidth = docEl.clientWidth;
			if(clientWidth === undefined) return;
			docEl.style.fontSize = 20 * (clientWidth / 320) + 'px';
		};
	if(doc.addEventListener === undefined) return;
	win.addEventListener(resizeEvt, recalc, false);
	doc.addEventListener('DOMContentLoaded', recalc, false)
})(document, window);