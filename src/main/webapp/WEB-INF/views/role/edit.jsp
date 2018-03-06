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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/js/icheck/skins/minimal/grey.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/js/zyupload/skins/zyupload-1.0.0.min.css" />
	<style type="text/css">
		/*重写zyupload组件的样式*/
		#status_info{width:100%;}
		.upload_preview{width:100%; border-bottom:none;}
	</style>
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
											<label for="name"
												class="control-label col-md-3 col-sm-3 col-xs-2">角色名称<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6 col-xs-10">
												<input type="text" name="name" id="name"
													value="${model.getName()}" required="required"
													class="form-control col-md-7 col-xs-12">
											</div>
										</div>

										<div class="form-group">
											<label for="note"
												class="control-label col-md-3 col-sm-3 col-xs-2">备注<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6 col-xs-10">
												<%-- <input type="text" name="note" id="note"
													value="${model.getNote()}" required="required"
													class="form-control col-md-7 col-xs-12">
													 --%>
												<textarea class="form-control" rows="3" name="note" id="note" value="${model.getNote()}" required="required"></textarea>
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
	<script src="<%=request.getContextPath()%>/js/zyupload/zyupload-1.0.0.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//主动触发关闭按钮的单击事件
			$("#btnCancel").click(function() {
				parent.window.$('.layui-layer-close').trigger('click')
			});

			//保存按钮单击事件
			$("#myForm").submit(function(e) {
				var param = $("#myForm").serialize();

				$.ajax({
					type : "post",
					data : param,
					dataType : "json",
					success : function(data) {
						var tip = "操作成功";
						if(!data.success){
							tip = data.message;
						}
						
						//添加成功后，刷新当前页
						layer.msg(tip, {
							time : 1000
						}, function() {/*window.location.reload();*/
						});
						layer.closeAll('loading');

						//更新父页面数据
						parent.window.setPage(parent.window.$("#pageIndex").val());
					}
				});

				e.preventDefault();
			});
		});
	</script>
</body>
</html>