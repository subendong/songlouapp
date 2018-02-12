<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>权限管理</title>
<!-- common css -->
<%@ include file="/WEB-INF/views/commoncss.jsp"%>
<!-- /common css -->
<link href="<%=pathCss%>/js/icheck/skins/minimal/grey.css" rel="stylesheet" />
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
									<form name="myForm" id="myForm" method="post" data-parsley-validate class="form-horizontal form-label-left">

										<div class="form-group">
											<label for="parentId"
												class="control-label col-md-3 col-sm-3 col-xs-2">上级权限<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6 col-xs-10">
												<select class="form-control" name="parentId" id="parentId" required="required">
													<option value="">请选择</option>
													<c:forEach var="rank" items="${ranks}">
														<option value="${rank.getId()}">
														<c:forEach var="i" begin="1" end="${rank.getDepth() - 1}">   
															<c:out value="|--" />
														</c:forEach>
														${rank.getRankName() }
														</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label for="rankName"
												class="control-label col-md-3 col-sm-3 col-xs-2">权限名称<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6 col-xs-10">
												<input type="text" name="rankName" id="rankName"
													required="required" class="form-control col-md-7 col-xs-12">
											</div>
										</div>
										<div class="form-group">
											<label for="controller"
												class="control-label col-md-3 col-sm-3 col-xs-2">控制器<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6 col-xs-10">
												<input type="text" name="controller" id="controller"
													required="required" class="form-control col-md-7 col-xs-12">
											</div>
										</div>
										<div class="form-group">
											<label for="action"
												class="control-label col-md-3 col-sm-3 col-xs-2">方法<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6 col-xs-10">
												<input type="text" name="action" id="action"
													required="required" class="form-control col-md-7 col-xs-12">
											</div>
										</div>

										<div class="form-group">
											<label for="showOrder"
												class="control-label col-md-3 col-sm-3 col-xs-2">排序<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6 col-xs-10">
												<input type="text" name="showOrder" id="showOrder"
													required="required" class="form-control col-md-7 col-xs-12"
													value="0">
											</div>
										</div>

										<div class="form-group">
											<label for="leftShow"
												class="control-label col-md-3 col-sm-3 col-xs-2">是否左边显示</label>
											<div class="col-md-6 col-sm-6 col-xs-10">
												<input type="checkbox" name="leftShow" id="leftShow"
													value="1" />
											</div>
										</div>
										<div class="ln_solid"></div>
										<div class="form-group">
											<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<button type="submit" id="btnSubmit" class="btn btn-success">确认添加</button>
												<button type="button" id="btnTest" class="btn btn-default">取消</button>
											</div>
										</div>

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
		$(document).ready(function() {
			//测试主动触发关闭按钮的单击事件
			$("#btnTest").click(function() {
				//$(".layui-layer-close", parent.document).trigger("click");
				parent.window.$('.layui-layer-close').trigger('click')
			});
			
			//用icheck美化checkbox
			//关于icheck的使用，百度网盘已保存，具体文件为：icheck-1.x/demo/index.html，里面有说明
			//参考文档：http://blog.csdn.net/liyixia/article/details/54582096
			$('input').iCheck({
			    checkboxClass: 'icheckbox_minimal-grey',
			    radioClass: 'iradio_minimal-grey',
			    increaseArea: '20%'
			});
			
			//保存按钮单击事件
			$("#myForm").submit(function(e){
				var param = {
					parentId: $("#parentId").val(),
					rankName: $("#rankName").val(),
					controller: $("#controller").val(),
					action: $("#action").val(),
					showOrder: $("#showOrder").val(),
					leftShow: $("#leftShow").is(":checked") ? 1 : 0,
				};

				$.ajax({
				    type:"post",
					url: "<%=request.getContextPath()%>/rank/add",
					data : param,
					dataType : "text",
					success : function(data) {
	                    layer.msg("添加成功", {time: 1000},function(){window.location.reload();});
	                    layer.closeAll('loading');
					}
				});
				
				//这里return false和e.preventDefault()都是可以的，只不过使用e.preventDefault()还得给回调方法加个evcent参数
				//用e.preventDefault()可以装逼，显得高大上
				//return false;
				e.preventDefault();
			});
		});
	</script>
</body>
</html>