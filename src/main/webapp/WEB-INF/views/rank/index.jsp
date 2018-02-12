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
<link href="<%=request.getContextPath()%>/css/pagination.css" rel="stylesheet" />
</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<!-- lefet menu -->
			<%@ include file="/WEB-INF/views/commonleft.jsp"%>
			<!-- /lefet menu -->

			<!-- top navigation -->
			<%@ include file="/WEB-INF/views/commonheader.jsp"%>
			<!-- /top navigation -->
			<!-- page content -->
			<div class="right_col" role="main">
				<div>
					<div class="row">



						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>
										权限管理 <small>权限列表</small>
									</h2>
									<!-- <ul class="nav navbar-right panel_toolbox">
										<li><a class="collapse-link"><i
												class="fa fa-chevron-up"></i></a></li>
										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-expanded="false"><i
												class="fa fa-wrench"></i></a>
											<ul class="dropdown-menu" role="menu">
												<li><a href="#">Settings 1</a></li>
												<li><a href="#">Settings 2</a></li>
											</ul></li>
										<li><a class="close-link"><i class="fa fa-close"></i></a>
										</li>
									</ul> -->
									<div class="clearfix"></div>
								</div>

								
								<div class="input-group" class="col-md-3" style="float:left;">
									<button type="button" class="btn btn-info" id="btnAdd">新增</button>
									<button type="button" class="btn btn-info">删除</button>
								</div>								
								
								
								<!-- 搜索框 -->
								<div class="input-group" class="col-md-3" style="width:20%; float:right;">
		                            <input type="text" name="key" id="key" class="form-control">
		                            <span class="input-group-btn">
										<button type="button" name="btnSearch" id="btnSearch" class="btn btn-primary">搜索</button>
									</span>
	                          	</div>


								<div class="x_content">
									<!-- 异步加载数据 -->
									<div class="table-responsive"></div>
									<div id="pagination"></div>
								</div>
							</div>
						</div>



					</div>
				</div>
			</div>
			<!-- /page content -->

			<!-- footer content -->
			<%@ include file="/WEB-INF/views/commonfooter.jsp"%>
			<!-- /footer content -->
		</div>
	</div>
	<!-- common js -->
	<%@ include file="/WEB-INF/views/commonjs.jsp"%>
	<!-- /common js -->
	<script src="<%=request.getContextPath()%>/js/jquery.pagination.js"></script>
	<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			setPage(1);
			
			//搜索按钮单击事件
			$("#btnSearch").click(function(){
				setPage(1);
			});
			
			//新增按钮单击事件
			$("#btnAdd").click(function(){
				layer.open({
					type: 2,
					title: '新增/修改',
					shadeClose: true,
					shade: 0.8,
					area: ['35%', '55%'],
					content: 'add/' //iframe的url
				});
			});
		});
		
		function setPage(pageIndex) {
			var param = {
				pageIndex: pageIndex,
				rankName: $("#key").val()
			};
			$.ajax({
			    type:"post",
				url: "<%=request.getContextPath()%>/rank/list",
				data : param,
				dataType : "html",
				success : function(data) {
					$(".table-responsive").html(data);
					
					//处理分页效果
					var totalRecord = $("#totalRecord").val();
					var pageSize = $("#pageSize").val();
					var pageIndex = $("#pageIndex").val();
					$("#pagination").html($.Pagination.ShowPage(totalRecord, pageSize, pageIndex, 10, true, true, true, true));
					
					//页码单击事件监听
					$("#pagination span[class='grey']").click(function () {
						var pageIndex = $(this).attr("page_index");
						setPage(pageIndex);
					});
				}
			});
		}
	</script>
</body>
</html>