

//获取视频路径
$(document).ready(function() {
            // var userId="1e89e6504be349a68c025976b3ecc1d1";
            var userId = appcan.locStorage.getVal(Common.USER_ID);
            // var number="http://shfawo.cn:8000";
            // var number = appcan.locStorage.getVal('serialNumber');
            // alert(userId);
            // alert(number);
            $.ajax({
                type:"get",
                //url:"http://192.168.199.155:8081/FHADMINM3/appMonitor/findByArea?uid=43ad74c88e0f45a5a497d25b21400463",
                url:""+apiHost()+"/appMonitor/findByArea?uid="+userId+"",
                async:true,
                dataType:"json",
                success:function(data){
                    // alert(data.data[0]);
                    console.log(data.data[0]);
                    //addVideo(data);
                    videoPlay(data);
                },
                error:function(e){
                    //alert('error');
                }
            });
    })
    
    
//点击播放跳转
function videoPlay(data){
    var allPlayBtn = document.getElementsByClassName('play_btn');
    var allVideo = document.getElementsByTagName('video');
    // var arrSrc = [{
            // URL: 'http://hls.open.ys7.com/openlive/c6a2ab5a328e4955ace5125a1fbbf786.m3u8'
        // }, {
            // URL: 'http://hls.open.ys7.com/openlive/313a587705f045339f72809d5c52007a.m3u8'
        // }, {
            // URL: 'http://hls.open.ys7.com/openlive/8b36c9fee89b45eaaf2b81bd4d807e51.m3u8'
        // }]
     var arrSrc = data.data[0]||[];  
     console.log(arrSrc)
     for(var i=0;i<allPlayBtn.length;i++){
         if(arrSrc.length==0||arrSrc[i].imageUrl==undefined||arrSrc[i].imageUrl==''){
             allVideo[i].poster="video/css/images/video"+i+".jpeg";
             
         }else{
             allVideo[i].poster=apiHost()+'/'+arrSrc[i].imageUrl;
         }
         
         allPlayBtn[i].onclick = function(){
            
            var index = this.getAttribute('index');
            if(arrSrc[index]){
                var url = arrSrc[index].URL;
                appcan.locStorage.setVal("VIDEO_URL",url);
                //跳转到播放页面
                appcan.window.open({
                          name:"videoPlay",
                          type: 1024,
                          aniId: 2,
                          data: "monitoringVideo/videoPlay.html",
                          animDuration: 90
                      });
            }else{
                Common.tip('没有视频链接~')
            }
            
            }
     }
    
}
//点击更多跳转到视频列表
more_click()
function more_click(){
    var video_list = document.getElementById('video_list');
    video_list.onclick = function(){
        //跳转到视频列表页面
            appcan.window.open({
                      name:"video",
                      type: 1024,
                      aniId: 2,
                      data: "monitoringVideo/videoList.html",
                      animDuration: 90
                  });
    }
}

