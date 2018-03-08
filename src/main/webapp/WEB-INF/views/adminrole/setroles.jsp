<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>分配角色</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- common css -->
	<%@ include file="/WEB-INF/views/commoncss.jsp"%>
	<!-- /common css -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/js/icheck/skins/minimal/grey.css" />
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
							<div class="x_panel" style="padding:0px;">
								<div class="x_content">
									<form name="myForm" id="myForm" method="post"
										data-parsley-validate class="form-horizontal form-label-left">
										<div class="form-group" style="height:300px; overflow-y:auto; overflow-x:hidden; margin:10px 10px 0px 10px;">
											<div class="row">
												<ul class="list-unstyled">
													<c:forEach var="role" items="${roleSets}">
														<li class="col-xs-4">
															<input type="checkbox" name="id" id="id_${role.getId()}" value="${role.getId()}" ${role.isChecked()?"checked='checked'":"" } />
															&nbsp;<label for="id_${role.getId()}">${role.getName()}</label>
														</li>
													</c:forEach>
												</ul>
											</div>
										</div>

										<div class="ln_solid"></div>
										<div class="form-group">
											<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<button type="submit" id="btnSubmit" class="btn btn-success">保存</button>
												<button type="button" id="btnCancel" class="btn btn-default">取消</button>
											</div>
										</div>

										<input type="hidden" name="adminId" id="adminId" value="${adminId}" />

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
			//用icheck美化checkbox
			$("input[type='checkbox']").iCheck({
			    checkboxClass: 'icheckbox_minimal-grey',
			    radioClass: 'iradio_minimal-grey',
			    increaseArea: '20%'
			});
			
			//主动触发关闭按钮的单击事件
			$("#btnCancel").click(function() {
				parent.window.$('.layui-layer-close').trigger('click')
			});

			//保存按钮单击事件
			$("#myForm").submit(function(e) {
				var param = {
					admin: $("#adminId").val(),
					roleIds: getIds()
				};

				$.ajax({
					type : "post",
					data : param,
					dataType : "json",
					success : function(data) {
						var tip = "操作成功";
						if(!data.success){tip = data.message;}
						
						//添加成功后，刷新当前页
						layer.msg(tip, {time : 1000}, function() {/*window.location.reload();*/});
						layer.closeAll('loading');
					}
				});

				e.preventDefault();
			});
		});
		
		//获取选中复选框的值
        function getIds() {
            var ids = "";
            $("input[name='id']").each(function() {
                var checked = $(this).prop("checked");
                if (checked) {
                    ids += $(this).val() + ",";
                }
            });

            if (ids.length > 0) {
                ids = ids.substr(0, ids.length - 1);
            }

            return ids;
        }
	</script>
</body>
</html>