<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>分配资源</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/tree/ranktree.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/tree/zTreeStyle/zTreeStyle.css" />
</head>
<body>
	<div class="input-group" style="padding:10px; border-bottom:1px solid #ccc; ">
		<button type="button" class="btn btn-info" id="btnSave">保存</button>
		<button type="button" class="btn btn-info" id="btnOpenAll">展开</button>
		<button type="button" class="btn btn-info" id="btnCloseAll" style="display:none;">收起</button>
	</div>
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<input type="hidden" name="roleId" id="roleId" value="${roleId}" />

	<!-- common js -->
	<%@ include file="/WEB-INF/views/commonjs.jsp"%>
	<!-- /common js -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tree/jquery.ztree.core.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tree/jquery.ztree.excheck.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript">
		var addUrl = "<%=request.getContextPath()%>/rolerank/set";
		$(document).ready(function(){
			//树
			var zNodes = ${strRankTrees};
			var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			
			//展开所有节点
			$("#btnOpenAll").click(function(){
				$(this).hide();
				$("#btnCloseAll").show();
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo"); 
				treeObj.expandAll(true); 
			});
			
			//收起所有节点
			$("#btnCloseAll").click(function(){
				$(this).hide();
				$("#btnOpenAll").show();
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo"); 
				//获取 zTree 的全部节点数据将节点数据转换为简单 Array 格式
		        var nodes = treeObj.transformToArray(treeObj.getNodes());
		        for(var i=0;i<nodes.length;i++){
		            /* if(nodes[i].level == 0){
		                //根节点展开
		                treeObj.expandNode(nodes[i],true,true,false)
		            }else{
		            	treeObj.expandNode(nodes[i],false,true,false)
		            } */
		            treeObj.expandNode(nodes[i],false,true,false)
		        }
			});
			
			//保存
			$("#btnSave").click(function(){
				//先获取所有选中的资源
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo"); 
	            nodes=treeObj.getCheckedNodes(true);
	            var ids = new Array();
	            $.each(nodes, function(i, o){
	            	ids.push(o.id);
	            });
	            //准备提交数据
	            var param = {
	            	roleId: $("#roleId").val(), 
	            	rankIds: ids.join(",")
	            };
	            //异步请求
	            $.ajax({
				    type:"post",
					url: addUrl,
					data : param,
					dataType : "json",
					success : function(data) {
						if(!data.success){
							layer.msg(data.message, {time: 1000});
		                    layer.closeAll('loading');
							return;
						}
						
						//添加成功后，刷新当前页
	                    layer.msg("操作成功", {time: 1000});
	                    layer.closeAll('loading');
					}
				});
			});
		});
	</script>
</body>
</html>