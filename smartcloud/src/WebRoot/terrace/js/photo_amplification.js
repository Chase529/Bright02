

function photo_amplification(){
    $('img').click(function(){
        
        disabledMouseWheel()
        
        var url = $(this).attr('src');
        add_mask(url);
    })
}

//页面增加一个弹出层

function add_mask(url){
    var oDiv = document.createElement('div');
    oDiv.className="photo_mask";
    
    
    // var oSpan = document.createElement('span');
    // oSpan.innerText="X";
    // oSpan.className="close_photo";
    // oDiv.appendChild(oSpan);
    
    
    var oImg = document.createElement('img');
    oImg.src = url;
    oImg.className="photo_img";
    oDiv.appendChild(oImg);
    
    $('body').append(oDiv);
    close_photo();
}

//退出图片全屏
function close_photo(){
    $('.photo_mask').click(function(){
        $('.photo_mask').remove('div');
        allowMouseWheel();
        
    })
    
}
//禁用滚轮事件
function disabledMouseWheel() {  
  
   $(document.body).css({
           "overflow-x":"hidden",
           "overflow-y":"hidden"
         });
}  

//启用滚轮事件
function allowMouseWheel(){
    
      $(document.body).css({
           "overflow-x":"auto",
           "overflow-y":"auto"
         });
}
