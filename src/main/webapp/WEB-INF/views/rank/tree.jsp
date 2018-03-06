<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>分配权限</title>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/tree/ranktree.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/tree/zTreeStyle/zTreeStyle.css" />
</head>
<body>
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>

	<!-- common js -->
	<%@ include file="/WEB-INF/views/commonjs.jsp"%>
	<!-- /common js -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tree/jquery.ztree.core.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tree/jquery.ztree.excheck.min.js"></script>
	<script type="text/javascript">
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

		var zNodes =[
			{ id:1, pId:0, name:"随意勾选 1", open:true},
			{ id:11, pId:1, name:"随意勾选 1-1", open:true},
			{ id:111, pId:11, name:"随意勾选 1-1-1"},
			{ id:112, pId:11, name:"随意勾选 1-1-2"},
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
			{ id:22, pId:2, name:"随意勾选 2-2", open:true},
			{ id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
			{ id:222, pId:22, name:"随意勾选 2-2-2"},
			{ id:23, pId:2, name:"随意勾选 2-3"}
		];
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	</script>
</body>
</html>