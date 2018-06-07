<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
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
					
					<form action="project/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="PROJECT_ID" id="PROJECT_ID" value="${pd.PROJECT_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">项目名称:</td>
								<td><input type="text" name="PROJECT_NAME" id="PROJECT_NAME" value="${pd.PROJECT_NAME}" maxlength="100" placeholder="这里输入项目名称" title="项目名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">项目公网地址:</td>
								<td><input type="text" name="PROJECT_URL" id="PROJECT_URL" value="${pd.PROJECT_URL}" maxlength="200" placeholder="这里输入项目公网地址" title="项目公网地址" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">项目省份:</td>
								<td>
									<select class="chosen-select" name="PROJECT_PROVINCE" id="PROJECT_PROVINCE"  maxlength="100"  style="width:98%;" onchange="showCity()" >
										<option value="">-请选择省份-</option>
										<c:forEach items="${provinceList}" var="pro">
											<option value="${pro.DICTIONARIES_ID }" id="${pro.DICTIONARIES_ID }" <c:if test="${pro.NAME == pd.NAME}">selected</c:if>>${pro.NAME }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">项目地区:</td>
								<td>
									<select class="chosen-select" name="LOCATION_BIANMA" id="LOCATION_BIANMA"  maxlength="100"  style="width:98%;"  >
										<option value="">-先选择省份-</option>
									</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">经度:</td>
								<td><input type="number" name="LONGITUDE" id="LONGITUDE" value="${pd.LONGITUDE}" maxlength="100" placeholder="这里输入经度" title="经度" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">纬度:</td>
								<td><input type="number" name="LATITUDE" id="LATITUDE" value="${pd.LATITUDE}" maxlength="100" placeholder="这里输入纬度" title="纬度" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="text-align: center;" colspan="10">
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


	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">

            //发送异步进行城市二级联动
            function showCity(){
                var parentId = $("#PROJECT_PROVINCE>option:selected").attr("value");
                $.ajax({
                    type : "GET",
                    url : "dictionaries/listSubDictByParentId.do",//路径
                    data : {
                        "parentId" : parentId
                    },
                    success : function(result) {

                        $("#LOCATION_BIANMA").empty();
                        for (var i = 0; i < result.length; i++) {
                            $("#LOCATION_BIANMA").append("<option value="+result[i].bianma+">"+result[i].name+"</option>"); //为Select追加一个Option(下拉项)
                        }
                    }
                });
            }




		$(top.hangge());
		//保存
		function save(){
			if($("#PROJECT_NAME").val()==""){
				$("#PROJECT_NAME").tips({
					side:3,
		            msg:'请输入项目名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PROJECT_NAME").focus();
			return false;
			}
			if($("#PROJECT_URL").val()==""){
				$("#PROJECT_URL").tips({
					side:3,
		            msg:'请输入项目公网地址',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PROJECT_URL").focus();
			return false;
			}
			if($("#LONGITUDE").val()==""){
				$("#LONGITUDE").tips({
					side:3,
		            msg:'请输入经度',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LONGITUDE").focus();
			return false;
			}
			if($("#LATITUDE").val()==""){
				$("#LATITUDE").tips({
					side:3,
		            msg:'请输入纬度',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LATITUDE").focus();
			return false;
			}
			if($("#CREATE_USER_ID").val()==""){
				$("#CREATE_USER_ID").tips({
					side:3,
		            msg:'请输入创建人id',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CREATE_USER_ID").focus();
			return false;
			}
			if($("#CREATE_TIME").val()==""){
				$("#CREATE_TIME").tips({
					side:3,
		            msg:'请输入创建时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CREATE_TIME").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>