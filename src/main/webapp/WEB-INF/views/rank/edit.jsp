<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>新增/修改资源</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- common css -->
	<%@ include file="/WEB-INF/views/commoncss.jsp"%>
	<!-- /common css -->
	<!-- 复选框美化插件 -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/js/icheck/skins/minimal/grey.css" />
	<!-- 树形菜单插件 -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/js/tree/ranktree.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/js/tree/zTreeStyle/zTreeStyle.css" />
	<style type="text/css">
		/*重新定义样式，不然树形会有问题*/
		#treeDemo .button{box-shadow:none;}
	</style>
</head>
<body class="nav-md">
	<div id="menuContent" class="menuContent" style="position: absolute; display:none;">				
		<ul id="treeDemo" class="ztree" style="margin-top:0px; z-index:100000; position:absolute; 
		border: 1px solid #ccc; background-color: #fff; width:100%; height:100%; overflow-y:auto;"></ul>
	</div>
								
								
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
											<label for="parentId"
												class="control-label col-md-3 col-sm-3 col-xs-2">上级资源</label>
											<div class="col-md-6 col-sm-6 col-xs-10">
												<input type="text" name="parentName" id="parentName" value="${parentRankName}"
												  ${model.getId() > 0 ? "readonly=\"readonly\"" : ""} class="form-control col-md-7 col-xs-12" />
												<input type="hidden" name="parentId" id="parentId" 
												value="${model.getParentId()}" required="required" class="form-control col-md-7 col-xs-12" />
													
													
												
												<%-- <select class="form-control" name="parentId" id="parentId" ${model.getId() > 0 ? "" : "readonly=\"readonly\""}>
													<option value="0">请选择</option>
													<c:forEach var="rank" items="${ranks}">
														<option value="${rank.getId()}">
															<c:forEach var="i" begin="1" end="${rank.getDepth() - 1}">
																<c:out value="|----" />
															</c:forEach> ${rank.getRankName() }
														</option>
													</c:forEach>
												</select> --%>
											</div>
										</div>
										<div class="form-group">
											<label for="rankName"
												class="control-label col-md-3 col-sm-3 col-xs-2">资源名称<span
												class="required">*</span></label>
											<div class="col-md-6 col-sm-6 col-xs-10">
												<input type="text" name="rankName" id="rankName" value="${model.getRankName()}"
													required="required" class="form-control col-md-7 col-xs-12">
											</div>
										</div>
										<div class="form-group">
											<label for="controller"
												class="control-label col-md-3 col-sm-3 col-xs-2">控制器</label>
											<div class="col-md-6 col-sm-6 col-xs-10">
												<input type="text" name="controller" id="controller" value="${model.getController()}" class="form-control col-md-7 col-xs-12">
											</div>
										</div>
										<div class="form-group">
											<label for="action"
												class="control-label col-md-3 col-sm-3 col-xs-2">方法</label>
											<div class="col-md-6 col-sm-6 col-xs-10">
												<input type="text" name="action" id="action" value="${model.getAction()}" class="form-control col-md-7 col-xs-12">
											</div>
										</div>

										<div class="form-group">
											<label for="leftShow"
												class="control-label col-md-3 col-sm-3 col-xs-2">是否左边显示</label>
											<div class="col-md-6 col-sm-6 col-xs-10" style="margin-top:6px;">
												<input type="checkbox" name="leftShow" id="leftShow" value="1" />
											</div>
										</div>
										<div class="ln_solid"></div>
										<div class="form-group">
											<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<button type="submit" id="btnSubmit" class="btn btn-success">保存</button>
												<button type="button" id="btnCancel" class="btn btn-default">取消</button>
											</div>
										</div>

										<input type="hidden" name="id" id="id" value="${model.getId()}" />
										<input type="hidden" name="hiddenParentId" id="hiddenParentId" value="${model.getParentId()}" />
										<input type="hidden" name="hiddenLeftShow" id="hiddenLeftShow" value="${model.getLeftShow()}" />

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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/icheck/icheck.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tree/jquery.ztree.core.min.js"></script>
	<script type="text/javascript">
		var setting = {
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				//在这里可以添加多个事件
				onClick: onClick
			}
		};
	
		var zNodes =${strRankTrees};
		
		function onClick(e, treeId, treeNode) {
			$("#parentId").val(treeNode.id);
			$("#parentName").val(treeNode.name);
			hideMenu();
		}

		function showMenu() {
			if($("#parentName").attr("readonly") == "readonly"){
				return;
			}
			
			var obj = $("#parentName");
			var offset = $("#parentName").offset();
			var width = (obj.width() + 22) + "px";
			var height = ($(document.body).height() / 1.5) + "px";
			$("#menuContent").css({left: offset.left + "px", top: offset.top + obj.outerHeight() + "px", width: width, height: height}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		
		
		
		
		
		$(document).ready(function() {
			//树插件
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$("#parentName").click(function(){
				showMenu();
			});
			
			
			//页面加载完后，给权限下拉框赋默认值
			$("#parentId").val($("#hiddenParentId").val());
			$("#leftShow").iCheck($("#hiddenLeftShow").val() == 1 ? "check" : "uncheck");
			
			//测试主动触发关闭按钮的单击事件
			$("#btnCancel").click(function() {
				//$(".layui-layer-close", parent.document).trigger("click");
				parent.window.$('.layui-layer-close').trigger('click')
			});
			
			//用icheck美化checkbox
			//关于icheck的使用，百度网盘已保存，具体文件为：icheck-1.x/demo/index.html，里面有说明
			//参考文档：http://blog.csdn.net/liyixia/article/details/54582096
			$("#leftShow").iCheck({
			    checkboxClass: 'icheckbox_minimal-grey',
			    radioClass: 'iradio_minimal-grey',
			    increaseArea: '20%'
			});
			
			//保存按钮单击事件
			$("#myForm").submit(function(e){
				var param = {
					id: $("#id").val(),
					parentId: $("#parentId").val(),
					rankName: $("#rankName").val(),
					controller: $("#controller").val(),
					action: $("#action").val(),
					leftShow: $("#leftShow").is(":checked") ? 1 : 0
				};

				$.ajax({
				    type:"post",
					data : param,
					dataType : "json",
					success : function(data) {
						if(!data.success){
							layer.msg(data.message, {time: 2000});
		                    layer.closeAll('loading');
							return;
						}
						
						//添加成功后，刷新当前页
	                    layer.msg("操作成功", {time: 1000},function(){/*window.location.reload();*/});
	                    layer.closeAll('loading');
	                    
	                    //更新父页面数据
	                    parent.window.setPage(parent.window.$("#pageIndex").val());
					}
				});
				
				//这里return false和e.preventDefault()都是可以的，只不过使用e.preventDefault()还得给回调方法加个event参数
				//用e.preventDefault()可以装逼，显得高大上
				//return false;
				e.preventDefault();
			});
		});
	</script>
</body>
</html>