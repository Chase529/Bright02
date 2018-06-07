<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../index/top.jsp"%>
<!-- 日期框 -->
<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
						<form action="happuser/${msg }.do" name="userForm" id="userForm" method="post" enctype="multipart/form-data">
							<input type="hidden" name="USER_ID" id="user_id" value="${pd.USER_ID }"/>
							<div id="zhongxin" style="padding-top: 13px;">
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">角色:</td>
									<td id="js">
									<select class="chosen-select form-control" name="ROLE_ID" id="role_id" data-placeholder="请选择等级" style="vertical-align:top;"  title="级别" style="width:98%;" >
									<option value=""></option>
									<c:forEach items="${roleList}" var="role">
										<option value="${role.ROLE_ID }" <c:if test="${role.ROLE_ID == pd.ROLE_ID }">selected</c:if>>${role.ROLE_NAME }</option>
									</c:forEach>
									</select>
									</td>
									<td style="width:79px;text-align: right;padding-top: 13px;">姓名:</td>
									<td><input type="text" name="NAME" id="name"  value="${pd.NAME }" placeholder="这里输入姓名" title="姓名" style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">密码:</td>
									<td><input type="password" name="PASSWORD" id="password"  placeholder="输入密码"  title="密码" style="width:98%;" /></td>
									<td style="width:79px;text-align: right;padding-top: 13px;">手机号:</td>
									<td><input type="tel" name="PHONE" id="PHONE" isEdit="${isEdit}" value="${pd.PHONE }" placeholder="这里输入手机号" title="手机号" style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">确认密码:</td>
									<td><input type="password" name="chkpwd" id="chkpwd"  placeholder="确认密码"  title="确认密码" style="width:98%;" /></td>
									<td style="width:79px;text-align: right;padding-top: 13px;">身份证号:</td>
									<td><input type="text" name="SFID" id="SFID" value="${pd.SFID }" placeholder="这里输入身份证号" title="身份证号" style="width:98%;" /></td>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">部门:</td>
									<td>
										<select class="chosen-select form-control" name="DEPARTMENT" id="DEPARTMENT" data-placeholder="部门"  style="vertical-align:top;width:98%;">
											<option value=""></option>
											<c:forEach items='${deparmentList}' var="dictinfo">
						                      	<option value="${dictinfo.BIANMA}" <c:if test="${dictinfo.BIANMA == pd.DEPARTMENT }">selected="selected"</c:if>>${dictinfo.NAME}</option>
						                    </c:forEach>
				                    	</select>
				                    </td>
									<td style="width:75px;text-align: right;padding-top: 13px;">性别:</td>
									<td>
										<select name="GENDER" title="性别">
										<option value="1" <c:if test="${pd.GENDER == '1' }">selected</c:if> >男</option>
										<option value="2" <c:if test="${pd.GENDER == '2' }">selected</c:if> >女</option>
										</select>
									</td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">学历:</td>
									<td>
										<select class="chosen-select form-control" name="EDU" id="EDU"  style="vertical-align:top;width: 120px;">
										<option value=""></option>
										<c:forEach items='${educationList}' var="dictinfo">
					                      	<option value="${dictinfo.BIANMA}" <c:if test="${dictinfo.BIANMA == pd.EDU }">selected="selected"</c:if>>${dictinfo.NAME}</option>
					                    </c:forEach>
				                    	</select>
				                    </td>
									<td style="width:75px;text-align: right;padding-top: 13px;">专业:</td>
									<td>
										<select class="chosen-select form-control" name="MAJOR" id="MAJOR"  style="vertical-align:top;width: 120px;">
										<option value=""></option>
										<c:forEach items='${professionList}' var="dictinfo">
					                      	<option value="${dictinfo.BIANMA}" <c:if test="${dictinfo.BIANMA == pd.MAJOR }">selected="selected"</c:if>>${dictinfo.NAME}</option>
					                    </c:forEach>
				                    	</select>
				                    </td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">直属上级:</td>
									<td>
										<select name="SUPERIOR" id="SUPERIOR" style="vertical-align:top;width: 120px;" >
					                    	<option value=""> </option>
					                    	<c:forEach items='${usersList}' var="user">
						                      	<option value="${user.USER_ID}" <c:if test="${user.USER_ID == pd.SUPERIOR}">selected="selected"</c:if>>${user.NAME}</option>
						                    </c:forEach>
				                   		 </select>
									</td>
									<td style="width:75px;text-align: right;padding-top: 13px;">违规情况:</td>
									<td><input type="text" name="VIOLATION" id="VIOLATION" value="${pd.VIOLATION}" maxlength="21845" placeholder="这里输入违规情况" title="违规情况" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">头像:</td>
									<td><input type="file" name="avatar" id="AVATAR" value="${pd.AVATAR}" maxlength="255" placeholder="这里输入头像" title="头像" style="width:98%;"/></td>
									<td style="width:75px;text-align: right;padding-top: 13px;">职位:</td>
									<td>
										<select class="chosen-select form-control" name="POSITION" id="POSITION"  style="vertical-align:top;width: 120px;">
										<option value=""></option>
										<c:forEach items='${positionList}' var="dictinfo">
					                      	<option value="${dictinfo.BIANMA}" <c:if test="${dictinfo.BIANMA == pd.POSITION }">selected="selected"</c:if>>${dictinfo.NAME}</option>
					                    </c:forEach>
				                    	</select>
				                    </td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">备注:</td>
									<td><input type="text" name="BZ" id="BZ"value="${pd.BZ }" placeholder="这里输入备注" title="备注" style="width:98%;" /></td>
									<td style="width:79px;text-align: right;padding-top: 13px;">状态</td>
									<td>
										<select name="STATUS" title="状态">
										<option value="1" <c:if test="${pd.STATUS == '1' }">selected</c:if> >正常</option>
										<option value="0" <c:if test="${pd.STATUS == '0' }">selected</c:if> >冻结</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="center" colspan="6">
										<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
										<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
									</td>
								</tr>
							</table>
							</div>
							<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
						</form>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../index/foot.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- inline scripts related to this page -->
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
</body>						
<script type="text/javascript">
	$(top.hangge());
	
	$(document).ready(function(){
		if($("#user_id").val()!=""){
			$("#loginname").attr("readonly","readonly");
			$("#loginname").css("color","gray");
		}
	});
	
	function ismail(mail){
		return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
	}

	//保存
	function save(){
		debugger;
		if($("#role_id").val()==""){
			$("#js").tips({
				side:3,
	            msg:'选择角色',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#role_id").focus();
			return false;
		}
		if($("#PHONE").val()=="" || $("#PHONE").val()=="此手机号已存在!"){
			$("#PHONE").tips({
				side:3,
	            msg:'输入手机号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PHONE").focus();
			$("#PHONE").val('');
			$("#PHONE").css("background-color","white");
			return false;
		}else{
			$("#PHONE").val(jQuery.trim($('#PHONE').val()));
		}
		if($("#user_id").val()=="" && $("#password").val()==""){
			$("#password").tips({
				side:3,
	            msg:'输入密码',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#password").focus();
			return false;
		}
		if($("#password").val()!=$("#chkpwd").val()){
			$("#chkpwd").tips({
				side:3,
	            msg:'两次密码不相同',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#chkpwd").focus();
			return false;
		}
		if($("#name").val()==""){
			$("#name").tips({
				side:3,
	            msg:'输入姓名',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#name").focus();
			return false;
		}
		
		if($("#PHONE").val() !=""){
			hasTelephone();
		}else{
			$("#userForm").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
	}
	//判断手机是否存在
	function hasTelephone(){
		var phone = $("#PHONE").val();
		var isEdit = PHONE.getAttribute("isEdit");
		if (isEdit != "edit") {
            $.ajax({
                type: "POST",
                url: '<%=basePath%>happuser/hasPhone.do',
                data: {PHONE:phone,tm:new Date().getTime()},
                dataType:'json',
                cache: false,
                success: function(data){
                    if("success" == data.result){
                        $("#userForm").submit();
                        $("#zhongxin").hide();
                        $("#zhongxin2").show();
                    }else{
                        $("#PHONE").css("background-color","#D16E6C");
                        setTimeout("$('#PHONE').val('此手机号已存在!')",500);
                    }
                }
            });
        }else {
            $("#userForm").submit();
            $("#zhongxin").hide();
            $("#zhongxin2").show();
		}
	}
	//判断用户名是否存在
	function hasU(){
		var USERNAME = $("#loginname").val();
		$.ajax({
			type: "POST",
			url: '<%=basePath%>happuser/hasU.do',
	    	data: {USERNAME:USERNAME,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.result){
					$("#userForm").submit();
					$("#zhongxin").hide();
					$("#zhongxin2").show();
				 }else{
					$("#loginname").css("background-color","#D16E6C");
					setTimeout("$('#loginname').val('此用户名已存在!')",500);
				 }
			}
		});
	}
	
	//判断邮箱是否存在
	function hasE(USERNAME){
		var EMAIL = $("#EMAIL").val();
		$.ajax({
			type: "POST",
			url: '<%=basePath%>happuser/hasE.do',
	    	data: {EMAIL:EMAIL,USERNAME:USERNAME,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" != data.result){
					 $("#EMAIL").tips({
							side:3,
				            msg:'邮箱'+EMAIL+'已存在',
				            bg:'#AE81FF',
				            time:3
				        });
					$('#EMAIL').val('');
				 }
			}
		});
	}
	
	//判断编码是否存在
	function hasN(USERNAME){
		var NUMBER = $("#NUMBER").val();
		$.ajax({
			type: "POST",
			url: '<%=basePath%>happuser/hasN.do',
	    	data: {NUMBER:NUMBER,USERNAME:USERNAME,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" != data.result){
					 $("#NUMBER").tips({
							side:3,
				            msg:'编号'+NUMBER+'已存在',
				            bg:'#AE81FF',
				            time:3
				        });
					 $('#NUMBER').val('');
				 }
			}
		});
	}
	
	//判断用户名是否存在
	function hasPhone(){
		var phone = $("#PHONE").val();
		$.ajax({
			type: "POST",
			url: '<%=basePath%>happuser/hasPhone.do',
	    	data: {PHONE:phone,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" != data.result){
					 $("#PHONE").tips({
							side:3,
				            msg:'手机'+phone+'已存在',
				            bg:'#AE81FF',
				            time:3
				        });
					 $('#PHONE').val('');
				 }
			}
		});
	}
	
	
	$(function() {
		//日期框
		$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		//下拉框
		if(!ace.vars['touch']) {
			$('.chosen-select').chosen({allow_single_deselect:true}); 
			$(window)
			.off('resize.chosen')
			.on('resize.chosen', function() {
				$('.chosen-select').each(function() {
					 var $this = $(this);
					 $this.next().css({'width': $this.parent().width()});
				});
			}).trigger('resize.chosen');
			$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
				if(event_name != 'sidebar_collapsed') return;
				$('.chosen-select').each(function() {
					 var $this = $(this);
					 $this.next().css({'width': $this.parent().width()});
				});
			});
			$('#chosen-multiple-style .btn').on('click', function(e){
				var target = $(this).find('input[type=radio]');
				var which = parseInt(target.val());
				if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
				 else $('#form-field-select-4').removeClass('tag-input-style');
			});
		}
	});
</script>
</html>