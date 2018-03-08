<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>资源管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- common css -->
	<%@ include file="/WEB-INF/views/commoncss.jsp"%>
	<!-- /common css -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pagination.css" />
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
										资源管理 <small>资源列表</small>
									</h2>
									<div class="clearfix"></div>
								</div>

								
								<div class="input-group" class="col-md-3" style="float:left;">
									<button type="button" class="btn btn-info" id="btnAdd">新增</button>
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
		//操作按钮相关链接
		var addUrl = "<%=request.getContextPath()%>/rank/add";
		var editUrl = "<%=request.getContextPath()%>/rank/edit";
		var listUrl = "<%=request.getContextPath()%>/rank/list";
		var deleteUrl = "<%=request.getContextPath() %>/rank/delete";
		
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
					shade: 0.5,
					area: ['35%', '55%'],
					content: addUrl //iframe的url
				});
			});
			
			//修改链接单击事件
			$(".table-responsive").on("click", "table tr td .aEdit", function(){
				layer.open({
					type: 2,
					title: '新增/修改',
					shadeClose: true,
					shade: 0.5,
					area: ['35%', '55%'],
					content: editUrl + "?id=" + $(this).attr("rankId") //iframe的url
				});
			});
			
			//删除事件
			$(".table-responsive").on("click", "table tr td .aDelete", function(){
				var id = $(this).attr("rankId");
	 		 	layer.confirm("确定删除？", {btn: ["确定","取消"]}, function(){
	 		 		//确定删除，需要处理的业务逻辑
 					ajaxDelete(id);
				},
				function(){
					//取消删除，do nothing。这个function可以不写的，但是如果想在取消后做点其它事情，就有用了
				});
				//下面这个效果也不错，别删啊，可以试试
				/*layer.msg("确定删除？", {
					time: 0, //不自动关闭
					btn: ["确定","取消"],
					yes: function(index){
						alert("yes");
					}
				});*/
			});
		});
		
		//异步删除
		function ajaxDelete(id){
			var param = {
				id : id	
			};
			$.ajax({
			    type:"post",
				url: deleteUrl,
				data : param,
				dataType : "json",
				success : function(data) {
					//如果删除失败，则弹出提示信息
					if(!data.success){
						layer.msg(data.message, {time: 1000});
	                    layer.closeAll('loading');
						return;
					}
					
					//删除成功之后，重新加载当前页数据
					var pageIndex = $("#pageIndex").val();
					setPage(pageIndex);
					
					//弹出成功提示
					layer.msg("删除成功", {time: 1000},function(){
					});
                    layer.closeAll('loading');
				}
			});
		}
		
		//分页处理
		function setPage(pageIndex) {
			var param = {
				pageIndex: pageIndex,
				rankName: $("#key").val()
			};
			$.ajax({
			    type:"post",
				url: listUrl,
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