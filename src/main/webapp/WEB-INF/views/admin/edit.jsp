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
											<div class="col-md-6 col-sm-6 col-xs-10">
												<div id="zyupload" class="zyupload"></div>
												<input type="text" name="photo" id="photo" value=""
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
	<script src="<%=request.getContextPath()%>/js/zyupload/zyupload-1.0.0.min.js"></script>
	<script type="text/javascript">
		//不加这段代码会报错：Uncaught TypeError: Cannot read property 'msie' of undefined
		//参考地址：http://blchen.com/jquery-can-not-read-property-msie-of-the-undefined-error-solution/
		jQuery.browser={};(function(){jQuery.browser.msie=false; jQuery.browser.version=0;if(navigator.userAgent.match(/MSIE ([0-9]+)./)){ jQuery.browser.msie=true;jQuery.browser.version=RegExp.$1;}})();
		
		//上传地址
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
					
					var images = new Array();
					// 初始化插件
					$("#zyupload").zyUpload({
						width            :   "100%",                  // 宽度
						height           :   "330px",                 // 宽度
						itemWidth        :   "140px",                 // 文件项的宽度
						itemHeight       :   "115px",                 // 文件项的高度
						url              :   uploadUrl,		          // 上传文件的路径
						fileType         :   ["jpg","png","js","exe","txt"],// 上传文件的类型
						fileSize         :   51200000,                // 上传文件的大小
						multiple         :   true,                    // 是否可以多个文件上传
						dragDrop         :   true,                    // 是否可以拖动上传文件
						tailor           :   true,                    // 是否可以裁剪图片
						del              :   true,                    // 是否可以删除文件
						finishDel        :   false,  				  // 是否在上传文件完成后删除预览
						/* 外部获得的回调接口 */
						onSelect: function(selectFiles, allFiles){    // 选择文件的回调方法  selectFile:当前选中的文件  allFiles:还没上传的全部文件
							console.info("当前选择了以下文件：");
							console.info(selectFiles);
						},
						onDelete: function(file, files){              // 删除一个文件的回调方法 file:当前删除的文件  files:删除之后的文件
							console.info("当前删除了此文件：");
							console.info(file.name);
						},
					 	onSuccess: function(file, response){          // 文件上传成功的回调方法
							/* console.info("此文件上传成功：");
							console.info(file.name);
							console.info("此文件上传到服务器地址：");
							console.info(response);
							$("#uploadInf").append("<p>上传成功，文件地址是：" + response + "</p>"); */
							var json = $.parseJSON(response);
							//$("#photo").val(json.data.replace(/\\/g,"\/"));
							images.push(json.data.replace(/\\/g,"\/"));
							$("#photo").val(images.join(";"));
							//$("#uploadInf").append("<p>上传成功，文件地址是：" + json.data.replace(/\\/g,"\/") + "</p>");
						},
						onFailure: function(file, response){          // 文件上传失败的回调方法
							console.info("此文件上传失败：");
							console.info(file.name);
						},
						onComplete: function(response){           	  // 上传完成的回调方法
							console.info("文件上传完成");
							console.info(response);
						}
					});
		});
	</script>
</body>
</html>