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
					
					<form action="projectpano/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
						<!-- 文件路径 -->
						<input hidden="hidden" name="FILE_URLS" id="FILE_URLS" value="${pd.FILE_URLS}"/>
						<!-- 文件名称 -->
						<input hidden="hidden" name="FILE_NAMES" id="FILE_NAMES" value="${pd.FILE_NAMES}"/>
						<!-- 文件id -->
						<input hidden="hidden" name="FILE_IDS" id="FILE_IDS" value="${pd.FILE_IDS}"/>

						<input type="hidden" name="PANO_ID" id="PANO_ID" value="${pd.PANO_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">项目:</td>
								<td>
									<select class="chosen-select" name="PROJECT_ID" id="PROJECT_ID"  maxlength="100"  style="width:98%;"  >
										<option value="">-请选择项目-</option>
										<c:forEach items="${proList}" var="pro">
											<option value="${pro.PROJECT_ID }"  <c:if test="${pro.PROJECT_ID == pd.PROJECT_ID}">selected</c:if>>${pro.PROJECT_NAME }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">资源:</td>
								<td>
									<input class="myfile" type="file" multiple  name="files" id="files" onchange="fileChange(this);"/>
									<div  class="container-fluid" style="height:150px;overflow-y:scroll;overflow-x:hidden;">
										<div class="row" id="temps"></div>
										<div class="row imgs" id="imgs"></div>
									</div>
								</td>
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
	<script type="text/javascript" src="static/js/myjs/userSelect.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#PROJECT_ID").val()==""){
				$("#PROJECT_ID").tips({
					side:3,
		            msg:'请输入项目id',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PROJECT_ID").focus();
			return false;
			}
			if($("#RESOURCE_ID").val()==""){
				$("#RESOURCE_ID").tips({
					side:3,
		            msg:'请输入资源id',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#RESOURCE_ID").focus();
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