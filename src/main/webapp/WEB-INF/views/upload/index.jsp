<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>上传文件</title>
	<!-- common css -->
	<%@ include file="/WEB-INF/views/commoncss.jsp"%>
	<!-- /common css -->
	<link rel="stylesheet" href="<%=pathCss%>/js/dropzone/dropzone.min.css" />
</head>
<body style="background-color:#fff;">
	<form class="dropzone" name="myForm" id="my-awesome-dropzone">
	</form>

	<!-- common js -->
	<%@ include file="/WEB-INF/views/commonjs.jsp"%>
	<!-- /common js -->
	
	<script src="<%=request.getContextPath()%>/js/dropzone/dropzone.min.js"></script>
	<script type="text/javascript">
		var url = "<%=request.getContextPath() %>/upload/upload";
	    Dropzone.options.myAwesomeDropzone = {
    		paramName: "file", //相当于<input>元素的name属性，默认为file。
    		url: url,
	        maxFiles: 1,
	        maxFilesize: 1,//最大文件大小，单位是 MB
	        acceptedFiles: ".jpg,.png,.gif",
	        addRemoveLinks: true,
	        init: function() {
	            this.on("success", function(file) {
	                console.log("File " + file.name + "uploaded");
	            });
	            this.on("removedfile", function(file) {
	                console.log("File " + file.name + "removed");
	            });
	            this.on("error", function(data) {
	            	debugger;
	                alert(data);
	            });
	        },
	        success: function(data){
	        	alert(data);
	        }
   		};
	</script>
</body>
</html>
<!-- 
dropzone参考地址：
DropzoneJS 使用指南（文件拖拽上传）https://segmentfault.com/a/1190000004045240
Dropzone的使用方法http://blog.csdn.net/shao508/article/details/47038919
JavaScript 文件拖拽上传插件 dropzone.js 介绍http://www.renfei.org/blog/dropzone-js-introduction.html
http://wxb.github.io/dropzonejs.com.zh-CN/dropzonezh-CN/#configuration
-->