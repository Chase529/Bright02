(function($) {

    // appcan.button("#nav-left", "btn-act",
    // function() {
        // appcan.window.close(-1);
    // });
    // appcan.button("#nav-right", "btn-act",
    // function() {
    // });

    // appcan.ready(function() {
        // $.scrollbox($("body")).on("releaseToReload",
        // function() { //After Release or call reload function,we reset the bounce
            // $("#ScrollContent").trigger("reload", this);
        // }).on("onReloading",
        // function(a) { //if onreloading status, drag will trigger this event
        // }).on("dragToReload",
        // function() { //drag over 30% of bounce height,will trigger this event
        // }).on("draging",
        // function(status) { //on draging, this event will be triggered.
        // }).on("release",
        // function() { //on draging, this event will be triggered.
        // }).on("scrollbottom",
        // function() { //on scroll bottom,this event will be triggered.you should get data from server
            // $("#ScrollContent").trigger("more", this);
        // }).hide();
//         
    // });
    
    // var messageList = appcan.listview({
        // selector: "#listview",
        // type: "thickLine",
        // hasIcon:true,
        // hasAngle: false,
        // hasSubTitle: true,
        // multiLIne: 1
    // });
    
    var cons
    var msn=""
    var add=document.querySelector(".add-news")
    var push=document.querySelector(".pushBox")
    var close=document.querySelector(".goBox .no")
    var news=document.querySelectorAll(".new .row")
    var peopleName=document.querySelector(".people")
    var attenBox=document.querySelector(".attenBox")
    var mask=document.querySelector(".pushBox>.mask")
    var go=document.querySelector(".goBox .go")
    //用户id
    var id=appcan.locStorage.getVal(Common.USER_ID)
    //服务器IP
    var IP=apiHost()
    var flage=true;
    var newFlage=[true,false]
    var unread=document.querySelector(".coning")
    number()
    statusAjax()
    for (var i=0;i<news.length;i++) {
        news[i].onclick=function(){
            for (var j=0;j<news.length;j++) {
                news[j].style.backgroundColor="#F9F9F9"
                news[j].style.color="#F16412"
            }
            this.style.backgroundColor="#F16412"
            this.style.color="#F9F9F9"
            // //console.log($(this).attr("flag"))
            if("true"==$(this).attr("flag")){
                // //console.log(1)
                $(".news-row").css("display","block")
                 document.querySelector(".bigBox>.coning").style.display="none"
            }else if($(this).attr("flag")=="false"){
                $(".news-row").css("display","block")
                // //console.log(2)
                 document.querySelector(".bigBox>.con").style.display="none"
            }
        }
    }
    //更改状态
    unread.onclick=function(e){
        //console.log(e)
        //console.log(e.path[0].className)
        for (var i=0; i < e.path.length; i++) {
          if(e.path[i].className=="row"){
              var that=e.path[i]
          }
        };
        var FHSMS_ID=$(that).attr("uid")
         $.ajax({
                type:"post",
                url:IP+"/appMessage/setHaveRead?",
                data:{
                    "uid":id,
                    "FHSMS_ID":FHSMS_ID,
                },
                success: function(msg){
                    //console.log(msg)
                    statusAjax()
                    number()
                }
             })
    }
    add.onclick=function(){
        push.style.bottom="0"
    }
    close.onclick=function(){
        push.style.bottom="400%"
    }
    peopleName.onclick=function(){
        mask.style.width="100%"
        attenBox.style.right="0"
    }
    mask.onclick=function(){
        attenBox.style.right="-100%"
        mask.style.width="0"
        var name=""
        $("input[type='checkbox']:checked").each(function() {
            name += "<span>"+$(this).val() + "</span>、";
            msn+=$(this).attr("id")+ ","
            poeplesAjax()
            document.querySelector(".pushBox>.people>.name").innerHTML=name
        });
        //console.log(name)
        //console.log(msn)
    }
    go.onclick=function(){
        //发送信息
        //console.log(IP+"/appMessage/save?"+"uid="+id)
        var userid=msn
        //console.log(msn)
        var con=$("textarea").val()
        //console.log(con)
        $.ajax({
                type:"post",
                url:IP+"/appMessage/save?",
                data:{
                    "uid":id,
                    "TO_USERIDS":userid,
                    "CONTENT":con
                },
                success: function(msg){
                    //console.log(msg)
                    alert("发送成功")
                    push.style.bottom="400%"
                    statusAjax()
                }
             })
    }
    function statusAjax(){
        //console.log(IP+"/appMessage/findReciveMesssage?"+"uid="+id)
        var parms={
        	"uid":id
        }
        $.ajax({
                type:"post",
                url:IP+"/appMessage/findReciveMesssage",
                data:parms,
                success: function(msg){
                    console.log(msg)
                    cons=read(msg.data)
                    
                }
             })
    }
    //已读保存数据
    function read(data){
        //console.log(data)
        //已读
        var coning=""
        //未读
        var con=""
        //console.log(data)
        for(var i=0;i<data.length;i++){
            if(data[i].STATUS=="1"){
                                //console.log(1)

                coning+=`
                 <div class="row">
                    <div class="imgBox">
                       <div class="img iconfont icon-lianxiren">
                            
                        </div>
                    </div>
                    <div class="name">
                        ${data[i].FROM_USERNAME}   
                        <div class="date">
                           ${data[i].SEND_TIME}
                        </div>
                    </div>
                    <div class="con text">
                        ${data[i].CONTENT}
                    </div>
                </div>
                `
            }else if(data[i].STATUS=="2"){
                //console.log(2)
                con+=`
                      <div class="row"  uid="${data[i].FHSMS_ID}">
                    <div class="imgBox">
                        <div class="img iconfont icon-lianxiren">
                            
                        </div>
                    </div>
                    <div class="name">
                        ${data[i].FROM_USERNAME}
                        <div class="date">
                           ${data[i].SEND_TIME}
                        </div>
                    </div>
                    <div class="con text">
                        ${data[i].CONTENT}
                    </div>
                </div>               
                `
            }
        }
        document.querySelector(".bigBox>.con").innerHTML=coning
        document.querySelector(".bigBox>.coning").innerHTML=con
    } 
    function number(){
        //console.log(IP+"/appMessage/findNoReadCount??"+"uid="+id)
        $.ajax({
            type:"post",
            url:IP+"/appMessage/findNoReadCount?",
            data:"uid="+id,
            success: function(msg){
                if(msg.data<=0||msg.data==undefined){
                    document.querySelector(".no>.ball").style.display="none"
                }else{
                    document.querySelector(".no>.ball").innerText=msg.data
                }
            }
         })
    }
    // 联系人
    poeplesAjax()
    function poeplesAjax(){
         //console.log(IP+"/appMessage/findReciveMesssage?"+"uid="+id)
        $.ajax({
                type:"get",
                url:IP+"/appuser/getAllDepartmentUsers?uid="+id,
                success: function(msg){
                    //console.log(msg.data)
                    peoples(msg.data)
                }
             })
    }
    function peoples(data){
        var con=`
            <div class="title">
                                        所有联系人
            </div>        
        `
        for (var i=0; i < data.length; i++) {
            con+=`
                <div class="depar">${data[i].NAME}</div>
            `
            //console.log(data[i].users.length)
          for (var j=0; j < data[i].users.length; j++) {
              if(data[i].users[j].USER_ID==id){
                  break;
              }
              con+=`
                <label for="${data[i].users[j].USER_ID}" class="depar-row"><input type="checkbox" name="people" id="${data[i].users[j].USER_ID}" value="${data[i].users[j].NAME}"/>${data[i].users[j].NAME}</label>
              `
          };
        };
        //console.log(con)
        document.querySelector(".attenBox").innerHTML=con
    }
    document.querySelector("textarea").onkeydown=function(){
        //console.log("change")
        if($("textarea").val().length>18){
            alert("最多可输入18个字符")
        }
    }
})($);