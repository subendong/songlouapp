<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>新增/修改权限</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- common css -->
<%@ include file="/WEB-INF/views/commoncss.jsp"%>
<!-- /common css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/js/icheck/skins/minimal/grey.css" />
</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<!-- page content -->
			<div class="right_col" role="main">
				<div>
					<div class="clearfix"></div>
					<div class="row">


						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_content">
									<br>
									<form name="myForm" id="myForm" method="post"
										data-parsley-validate class="form-horizontal form-label-left">
										<div class="form-group">
											<label for="username"
												class="control-label col-md-3 col-sm-3 col-xs-2">用户名<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6 col-xs-10">
												<input type="text" name="username" id="username"
													value="${model.getUsername()}" required="required"
													class="form-control col-md-7 col-xs-12">
											</div>
										</div>
										<div class="form-group">
											<label for="password"
												class="control-label col-md-3 col-sm-3 col-xs-2">密码<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6 col-xs-10">
												<input type="text" name="password" id="password"
													value="${model.getPassword()}" required="required"
													class="form-control col-md-7 col-xs-12">
											</div>
										</div>

										<div class="form-group">
											<label for="photo"
												class="control-label col-md-3 col-sm-3 col-xs-2">上传照片<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6 col-xs-10 bs-glyphicons">
												<ul class="bs-glyphicons-list">
													<li style="width:150px; height:90px; margin-left:9px;"><span class="glyphicon glyphicon-open"
														aria-hidden="true"></span> <span class="glyphicon-class">请选择图片</span></li>
												</ul>
												<input type="hidden" name="photo" id="photo" value=""
													required="required" class="form-control col-md-7 col-xs-12" />
											</div>
										</div>

										<div class="ln_solid"></div>
										<div class="form-group">
											<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<button type="submit" id="btnSubmit" class="btn btn-success">保存</button>
												<button type="button" id="btnCancel" class="btn btn-default">取消</button>
											</div>
										</div>

										<input type="hidden" name="id" id="id"
											value="${model.getId()}" />

									</form>
								</div>
							</div>
						</div>


					</div>
				</div>
			</div>
			<!-- /page content -->

		</div>
	</div>

	<!-- common js -->
	<%@ include file="/WEB-INF/views/commonjs.jsp"%>
	<!-- /common js -->
	<script src="<%=request.getContextPath()%>/js/icheck/icheck.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript">
		var uploadUrl = "<%=request.getContextPath()%>/upload/index";
		$(document).ready(
				function() {
					//主动触发关闭按钮的单击事件
					$("#btnCancel").click(function() {
						parent.window.$('.layui-layer-close').trigger('click')
					});

					//保存按钮单击事件
					$("#myForm").submit(
						function(e) {
							var param = $("#myForm").serialize();

							$.ajax({
								type : "post",
								data : param,
								dataType : "text",
								success : function(data) {
									//添加成功后，刷新当前页
									layer.msg("操作成功", {
										time : 1000
									}, function() {/*window.location.reload();*/
									});
									layer.closeAll('loading');

									//更新父页面数据
									parent.window.setPage(parent.window.$(
											"#pageIndex").val());
								}
							});

							e.preventDefault();
						});
					
					//上传图片按钮单击事件
					$(".bs-glyphicons-list").click(function(){
						layer.open({
							type: 2,
							title: '上传图片',
							shadeClose: true,
							shade: 0.1,
							area: ['70%', '60%'],
							content: uploadUrl
						});
					});
		});
	</script>
</body>
</html>