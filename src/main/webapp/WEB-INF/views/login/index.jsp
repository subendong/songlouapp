<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>登录-管理后台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- common css -->
	<%@ include file="/WEB-INF/views/commoncss.jsp"%>
	<!-- /common css -->
</head>
<body class="login">
	<div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form name="myForm" id="myForm">
              <h1>登录</h1>
              <div>
                <input type="text" name="username" id="username" class="form-control" placeholder="Username" />
              </div>
              <div>
                <input type="password" name="password" id="password" class="form-control" placeholder="Password" />
              </div>
              <div>
                <a class="btn btn-default submit" href="javascript:void(0);">登录</a>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>

	<!-- common js -->
	<%@ include file="/WEB-INF/views/commonjs.jsp"%>
	<!-- /common js -->
	<script type="text/javascript">
		$(document).ready(function(){
			$(".submit").click(function(){
				var param = $("form").serialize();
				$.ajax({
					type : "post",
					data : param,
					dataType : "json",
					success : function(data) {
						if(data.success){
							window.location.href = "<%=request.getContextPath()%>/admin/index";
						}else{
							alert(data.message);
						}
					}
				});
			});
		});
	</script>
</body>
</html>