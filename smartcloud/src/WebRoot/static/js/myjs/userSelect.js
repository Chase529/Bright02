
/**
 * 添加select选项和选中项
 * values:option列表的值集合
 * names：options列表的名称集合
 * select_value：选中项的值
 */
$.fn.addOptions = function(values,names,select_value){
	for(var i=0;i<names.length;i++){
		var option = $("<option value='"+values[i]+"'>"+names[i]+"</option>");
		if(select_value==values[i]){
			option.attr("selected","selected");
		}
		this.append(option);
	}
}


/**
 * date:年月日
 * date_time:年月日-时分秒
 */
$.fn.initDate = function(type){
	if(type=="date"){
		this.datepicker({format:'yyyy-mm-dd',autoclose: true,todayHighlight: true});
	}else if(type=="date_time"){
		this.datetimepicker({
			format: 'yyyy-mm-dd hh:ii:ss',//显示格式
			language: 'zh-CN',
			weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
	        showMeridian: false,
	        initialDate: new Date()
		});
	}
	this.attr("readonly","readonly");
}

//设置close位置
function setClosePosition(dom){
    var width = $(dom).prev().outerWidth();
    var height = $(dom).prev().outerHeight();
    $(dom).css("left",width-8);
    $(dom).css("top",-8);
}


$(function(){
	//设置上传按钮
	setUploadBtn();
	//人员选择
	$(".users").attr("readonly","readonly");
	$(".users").on("click",function(){
		var jObj = $(this);
		usersClick(jObj);
	})
	//设置close位置
	$(".close").each(function(){
        setClosePosition(this);
    })
	//回显文件列表
	var file_urls = $("#FILE_URLS").val();
	if(isNotNull(file_urls)){
		var FILE_URLS = $("#FILE_URLS").val().split(",");
		var FILE_NAMES = $("#FILE_NAMES").val().split(",");
		var FILE_IDS = $("#FILE_IDS").val().split(",");
		if(FILE_URLS!=''){
			addImg($("#imgs"),FILE_URLS,FILE_NAMES,FILE_IDS);
		}
	}
	
})

var mark;
function usersClick(jObj){
	top.jzts();
	var url = '../appDeparment/departAndUserView.do';
	 if(jObj.attr("select_more")){
		 url += "?select_more=true";
	 }
	 
	 var diag = new top.Dialog();
	 diag.Drag=true;//是否允许拖动窗口
	 diag.Title ="人员选择";
	 diag.URL = url;
	 diag.Width = 550;
	 diag.Height = 355;
	 diag. ShowMaxButton = true;	//最大化按钮
     diag.ShowMinButton = true;		//最小化按钮
     diag.CancelEvent = function(){ //关闭事件
    	 
    	var input = $(diag.innerFrame.contentWindow.document.getElementById('userIds'));
	 	var type = input.attr("type");
	 	if(type=="save"){
	 		var id = input.val();
	 		if(id!=null&&id!=""){
	 			var res = jObj.attr("rel");
	 			if(res!=null){
	 				$("#"+jObj.attr("rel")).val(id);
    	 			jObj.val(input.attr("name"));
	 			}else{
	 				jObj.attr("user_id",id);
    	 			jObj.attr("user_name",input.attr("name"));
    	 			jObj.text(input.attr("name"));
	 			}
	 		}
	 	}
		diag.close();
		showMork();
	 };
	 diag.show();
}

function showMork(){
	setTimeout(function(){
		var mork = $(window.parent.document.getElementById("_DialogBGDiv"));
		mork.css("display","block");
		mork.css("z-index","900");
	},10);
}

/**
 * 初始化费用列表：
 * 	all_fee_types_bianma:所有类型编码
 * 	all_types_names：所有类型名称
 * 	types：类型列表
 * 	amounts：费用列表
 * 	causes：事由列表
 */
var btn;
var baseDiv;
var all_fee_types_bianma ;
var all_fee_types_name ;
var have_select_fee_type_bianma ;//所有编码复制

$.fn.initFee = function(all_types_bianma,all_types_names,types,amounts,causes){
	baseDiv = $(this);
	
	all_fee_types_bianma = all_types_bianma;
	all_fee_types_name = all_types_names;
	have_select_fee_type_bianma = all_fee_types_bianma.slice(0);
	
	var div = $("<div class='hidden' hidden><a></a></div>");
	this.append(div);
	
	var btnDiv ;
	var div = $("<div class='detail-add-btn'></div>");
	if(causes!=null){
		btnDiv = "<a class='btn btn-xs btn-success' title='费用内容' onclick=\"addTr(this,\'\',\'\',\'\')\">"+
		  "添加费用内容<i title='费用内容'></i>"+
		  "</a>";
	}else{
		btnDiv = "<a class='btn btn-xs btn-success' title='费用内容' onclick=\"addTr(this,\'\',\'\')\">"+
		  "添加费用内容<i title='费用内容'></i>"+
		  "</a>";
	}
	div.append($(btnDiv));
	this.append(div);
	if(isNotNull(amounts)){
		baseDiv.children(".detail-add-btn").removeAttr("hidden");
		addTr(div.find("a"),types,amounts,causes);
	}
}


//删除费用明细
function delTr(dom){
	have_select_fee_type_bianma.push($($(dom).parent().children("select")[0]).val());
	$(dom).parent().remove();
	$(".add_btn").each(function(index,item){
		$(item).removeAttr("hidden");
	});
	
	var size = baseDiv.children(".detail-unit").length;
	if(size==0){//没有明细，显示添加按钮
		baseDiv.children(".detail-add-btn").removeAttr("hidden");
	}
	count();
}

//添加费用明细
function addTr(dom,types,amounts,causes){
	var currentLength = $("#details").children(".detail-unit").length;
	//执行添加,
	if(types==""){
		types = [""];
	}
	if(amounts==""){
		amounts = [""];
	}
	if(causes==""){
		causes = [""];
	}
	for(var i=0;i<types.length;i++){
		if(causes!=null){
			$(dom).parent().after(getFeeJq(types[i],amounts[i],causes[i]));
		}else{
			$(dom).parent().after(getFeeJq(types[i],amounts[i]));
		}
	}
	//判断当前是否已有费用项，如果没有，删除添加按钮
	if(currentLength==0){
		baseDiv.children(".detail-add-btn").attr("hidden","hidden");
	}
	//验证类型是否都已存在，已存在，则隐藏添加图标
	if(have_select_fee_type_bianma.length==0){
		$(".add_btn").each(function(index,item){
			$(item).attr("hidden","hidden");
		});
	}
	count();
}

//创建费用项
function getFeeJq(type,amount,cause){
	var divStr = "<div style='margin-bottom:4px' class='detail-unit'>"+
					"<select class='detail_type' style='height:25px'>"+
						setOptions(type)+
					"</select>"+	 //费用类型
					setAmountAndCause(amount,cause)+
					setAddAndDelBtn(amount,cause);
				"</div>";
	return $(divStr);
}
//创建费用和事由jquery对象
function setAmountAndCause(amount,cause){
	if(cause==null||cause=="null"){
		return "<input name='FEE_AMOUNT' id='FEE_AMOUNT' value='"+amount+"' maxlength='100'"+
					"placeholder='这里输入金额' class='amount_unit'  onblur='count()' style='width:65%;margin-left:3px'/>";
	}else{
		return "<input name='FEE_AMOUNT' id='FEE_AMOUNT' value='"+amount+"' maxlength='100'"+
					"placeholder='这里输入金额' class='amount_unit'  onblur='count()' style='width:15%;margin-left:3px'/>"+//金额
				"<input name='FEE_CAUSE' id='FEE_CAUSE' value='"+cause+"'"+"placeholder='这里输入明细' style='width:50%;margin-left:3px'/>"
	}
}
//创建添加删除图标jquery对象
function setAddAndDelBtn(amount,cause){
	var btn ;
	if(cause!=null){
		btn = "<a style='cursor:pointer;' onclick='addTr(this,\"\",\"\",\"\")' class='tooltip-error add_btn' data-rel='tooltip'>"+
				"<span class='blue'>"+
					"<i class='ace-icon fa fa-plus bigger-120'></i>"+
				"</span>"+
			"</a>"+//添加
			"<a style='cursor:pointer;' onclick='delTr(this)' class='tooltip-error del_btn' data-rel='tooltip'>"+
				"<span class='red' style='margin-left:3px'>"+
					"<i class='ace-icon fa fa-times bigger-120'></i>"+
				"</span>"+
			"</a>";//删除
	}else{
		btn = "<a style='cursor:pointer;' onclick='addTr(this,\"\",\"\")' class='tooltip-error add_btn' data-rel='tooltip'>"+
				"<span class='blue'>"+
					"<i class='ace-icon fa fa-plus bigger-120'></i>"+
				"</span>"+
			"</a>"+//添加
			"<a style='cursor:pointer;' onclick='delTr(this)' class='tooltip-error del_btn' data-rel='tooltip'>"+
				"<span class='red' style='margin-left:3px'>"+
					"<i class='ace-icon fa fa-times bigger-120'></i>"+
				"</span>"+
			"</a>";//删除
	}
	return btn;
}


//设置费用类型选项
function setOptions(type){
	//费用类型
	var options = "";
	//设置选中
	if(type==null||type==""){
		type = have_select_fee_type_bianma[0];
	}
	have_select_fee_type_bianma.splice($.inArray(type,have_select_fee_type_bianma),1);
	
	for(var i=0;i<all_fee_types_bianma.length;i++){
		options += "<option value='"+all_fee_types_bianma[i]+"'";
		if(all_fee_types_bianma[i]==type){
			options += "selected='selected'";
		}
		options += ">"+all_fee_types_name[i]+"</option>";
	}
	return options;
}


//计算总费用(各费用项之和)
function count(){
	var amount = 0;
	$(".amount_unit").each(function(index,unit){
		var val = $(unit).val();
		if(val!=""){
			amount += parseFloat(val);
		}
	})
	$(".total_amount").val(amount);
}

//校验
function validate(){
	var msg = true;
	$("*[require]").each(function(index,item){
		if($(item).val()==""){
			$(item).tips({
				side:3,
	            msg:$(item).parent().prev().html()+"不能为空！",
	            bg:'#AE81FF',
	            time:2
	        });
			$(item).focus();
			msg = false;
			return false;
		}
	});
	$("*[number]").each(function(index,item){
		if(isNaN($(item).val())){
			$(item).tips({
				side:3,
	            msg:$(item).parent().prev().html()+"必须为数字！",
	            bg:'#AE81FF',
	            time:2
	        });
			$(item).focus();
			msg = false;
			return false;
		}
	})
	return msg;
}

//添加图片
var imgTypes = ["jpg","jpeg","png"];
//var imgUrl = "";
var vedioTypes = ["dwg","avi","mp4","rmvb","rm","wmv","3gp"];
var vedioUrl = "static/images/extension/vedio.png"
var docTypes = ["text","txt","doc","excel","docx"];
var docUrl = "static/images/extension/wenben.png";
//图片事件
function setEven(){
	var children = $(".imgs").children();
	for(var i=0;i<children.length;i++){
		$(children[i]).on("click",function(){
			var haveClick = $(this).attr("haveClick");
			if(!haveClick){
				$(this).css("background-color","#bdafaf");
				$(this).attr("haveClick","haveClick");
			}else{
				$(this).css("background-color","rgba(0,0,0,0)");
				$(this).removeAttr("haveClick");
			}
		});
		$(children[i]).on({  
            mouseover : function(){  
            		var haveClick = $(this).attr("haveClick");
            		if(!haveClick){
            			$(this).css("background-color","#e0dbdb");
            		}
            },  
            mouseout : function(){  
            		var haveClick = $(this).attr("haveClick");
	            	if(!haveClick){
	            		$(this).css("background-color","rgba(0,0,0,0)");
	        		}
            }   
        }) ;  
	}
}


function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

function endWith(str,endstr){
	var contains = false;
	var endStr=[];
	if(endstr instanceof Array){
		endStr = endstr;
	}else{
		endStr.push(endstr);
	}
	for(var i=0;i<endStr.length;i++){
		var end = str.substring(str.length-endStr[i].length,str.length);
		if(end==endStr[i]){
			contains = true;
		}
	}
	return contains;
}



//上传文件按钮--样式
function setUploadBtn(){
	var a = $("<a class='btn btn-mini btn-primary' style='cursor:pointer;border:1px;padding:5px;border-radius:0px;font-size:12px'>点击上传</a>");
	var file = $(".myfile");
	file.css("opacity","0");
	file.css("position","absolute");
	file.css("left","0px");
	file.css("top","0px");
	file.before(a);
	a.append(file);
	
	file.css("width",a.outerWidth(true));
	file.css("height",a.outerHeight(true));
}



//添加图片
function addImg(target,URLs,NAMEs,IDs){
	var urls = [];
	var names = []
	var ids = [];
	if(URLs instanceof Array){
		urls = URLs;
		names = NAMEs;
		ids = IDs;
	}else{//如果是数组，创建为数组
		urls.push(URLs);
		names.push(NAMEs);
		ids.push(IDs);
	}
	
	for(var i=0;i<names.length;i++){
		var tempUrl = "";
		if(endWith(names[i],docTypes)){
			tempUrl = docUrl;
		}
		if(endWith(names[i],vedioTypes)){
			tempUrl = vedioUrl;
		}
		if(endWith(names[i],imgTypes)){
			tempUrl = urls[i];
		}
		var colDiv = $("<div class='col-xs-3' style='text-align:center;margin-right:20px;margin-bottom:5px'></div>");
		var imgDiv =  $("<div style='text-align:center;margin-top:10px;'></div>");
			var img = $("<img src='"+tempUrl+"' width='130' style='margin-right:20px' height='110'/>");
			imgDiv.append(img);    
			
		var span = $("<span style='margin-left: 60px;margin-top:2px;position: absolute;' class='close btn-close'>&times</span>");
		if(IDs!=null){
			span.attr("id",ids[i]);
			colDiv.append(span);
		}else{
			var spanTemp = $("<span style='margin-left: 60px;margin-top:2px;position: absolute;' onclick='remove_temp_img(this)' class='close btn-close'>&times</span>");
			spanTemp.attr("id",ids[i]);
			colDiv.append(spanTemp);
			if(endWith(names[i],imgTypes)){
				img.css("opacity","0.5");
			}
		}
		//设置文件名
		var maxLength = 15;
		var name='';
		if(names[i].length>maxLength){
			name += names[i].substring(0,maxLength)+"<br/>";
			name += names[i].substring(maxLength,names[i].length);
		}else{
			name = names[i];
		}
		var nameDiv = $("<div style='text-align:center;font-size:8px' >"+name+"</div>");
		colDiv.append(imgDiv);
		colDiv.append(nameDiv);
		target.append(colDiv);
		span.on("click",function(){
			var id = $(this).attr("id");
			$.get(getRootPath()+"/photo/delete",{"RESOURCE_ID":id},function(){
			});
			$(this).parent().remove();
		})
	}
	if(target.hasClass("imgs")){
		setEven();
	}
}

//删除临时文件
function remove_temp_img(target){
	var name = $(target).next().next().text();
	curFiles = curFiles.filter(function(file){
		return file.name != name;
	})
	$(target).parent().remove();
}

//上传文件---改变事件
var curFiles = []; 
function fileChange(target) {
	var files = target.files;
	for(var i=0;i<files.length;i++){
		var name = files[i].name;
        var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
        //校验格式
        if(fileName !="jpg" && fileName !="jpeg" && fileName !="pdf" && fileName !="png" && fileName !="dwg"
            && fileName !="avi" && fileName !="mp4" && fileName !="rmvb" && fileName !="rm" && fileName != "wmv"
            && fileName !="3gp"){
            alert("请选择正确格式上传! 图片:jpg,png,dwg,pdf,jpeg 视频 rm、wmv、avi、3GP、MP4");
            target.value="";
            return;
        }
        //校验重名
        for(var j=0;j<curFiles.length;j++){
        		if(curFiles[j].name==files[i].name){
        			alert("文件‘"+files[i].name+"’已存在！");
        			return ;
        		}
        }
	}
	Array.prototype.push.apply(curFiles, files);
	
	for(var i=0;i<files.length;i++){
        loadImg(files[i]);
	}
}

function loadImg(file){
	var freader = new FileReader(); 
	if(endWith(file.name,imgTypes)){//图片
		freader.readAsDataURL(file);
		freader.onload = function(e) {
            addImg($("#temps"),e.target.result,file.name);
		}
	}
    if(endWith(name,docTypes)){//文档
		freader.readAsText(file);
		freader.onload = function(e) {    
            addImg($("#temps"),e.target.result,name);
		}
	}
}

//判断非空
function isNotNull(str){
	if(str!=null&&str!=""&&str!="null"&&str!="undefined"&&str.length!=0){
		return true;
	}else{
		return false;
	}
}
