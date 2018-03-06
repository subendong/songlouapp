<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>分配权限</title>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/tree/ranktree.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/tree/zTreeStyle/zTreeStyle.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/js/icheck/skins/minimal/grey.css" />
</head>
<body>
	<div class="input-group" style="padding:10px; border-bottom:1px solid #ccc; ">
		<button type="button" class="btn btn-info" id="btnSave">保存</button>
		<button type="button" class="btn btn-info" id="btnOpenAll">展开</button>
		<button type="button" class="btn btn-info" id="btnCloseAll">收起</button>
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
		
		/*var zNodes =[
			{ id:1, pId:0, name:"随意勾选 1", open:true},
			{ id:11, pId:1, name:"随意勾选 1-1", open:true},
			{ id:111, pId:11, name:"随意勾选 1-1-1", open:true},
			{ id:112, pId:11, name:"随意勾选 1-1-2", open:true},
			{ id:113, pId:11, name:"随意勾选 1-1-3"},
			{ id:114, pId:11, name:"随意勾选 1-1-4"},
			{ id:115, pId:11, name:"随意勾选 1-1-5"},
			{ id:116, pId:11, name:"随意勾选 1-1-6"},
			{ id:117, pId:11, name:"随意勾选 1-1-7"},
			{ id:118, pId:11, name:"随意勾选 1-1-8"},
			{ id:119, pId:11, name:"随意勾选 1-1-9"},
			{ id:1110, pId:11, name:"随意勾选 1-1-10"},
			{ id:1111, pId:11, name:"随意勾选 1-1-11"},
			{ id:1112, pId:11, name:"随意勾选 1-1-12"},
			{ id:1113, pId:11, name:"随意勾选 1-1-13"},
			{ id:1114, pId:11, name:"随意勾选 1-1-14"},
			{ id:1115, pId:11, name:"随意勾选 1-1-15"},
			{ id:1116, pId:11, name:"随意勾选 1-1-16"},
			{ id:1117, pId:11, name:"随意勾选 1-1-17"},
			{ id:1118, pId:11, name:"随意勾选 1-1-18"},
			{ id:1119, pId:11, name:"随意勾选 1-1-19"},
			{ id:11110, pId:11, name:"随意勾选 1-1-20"},
			{ id:11111, pId:11, name:"随意勾选 1-1-12"},
			{ id:11112, pId:11, name:"随意勾选 1-1-22"},
			{ id:12, pId:1, name:"随意勾选 1-2", open:true},
			{ id:121, pId:12, name:"随意勾选 1-2-1"},
			{ id:122, pId:12, name:"随意勾选 1-2-2"},
			{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
			{ id:21, pId:2, name:"随意勾选 2-1"},
			{ id:22, pId:2, name:"随意勾选 2-2"},
			{ id:221, pId:22, name:"随意勾选 2-2-1"},
			{ id:222, pId:22, name:"随意勾选 2-2-2"},
			{ id:23, pId:2, name:"随意勾选 2-3"}
		]; */
		
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
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo"); 
				treeObj.expandAll(true); 
			});
			
			//收起所有节点
			$("#btnCloseAll").click(function(){
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
				//先获取所有选中的权限
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