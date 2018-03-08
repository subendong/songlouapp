<%@page import="com.songlou.service.LeftMenuService"%>
<%@page import="com.songlou.service.SessionUtil"%>
<%@page import="com.songlou.pojo.Admin"%>
<%@page import="com.songlou.pojo.Rank"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String pathLeft = request.getContextPath();
	Admin admin = SessionUtil.getCurrentAdmin(request);
	List<Rank> ranks = SessionUtil.getRankListFromSession(request, admin);
%>
<div class="col-md-3 left_col">
	<div class="left_col scroll-view">
		<div class="navbar nav_title" style="border: 0px; height:auto; min-height:auto;">
		</div>

		<div class="clearfix"></div>

		<!-- menu profile quick info -->
		<div class="profile clearfix">
			<div class="profile_pic">
				<img src="<%=pathLeft + admin.getPhoto()%>" class="img-circle profile_img" />
			</div>
			<div class="profile_info">
				<span>Welcome,</span>
				<h2><%=admin.getUsername() %></h2>
			</div>
		</div>
		<!-- /menu profile quick info -->

		<br />

		<!-- sidebar menu -->
		<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
			<div class="menu_section">
				<h3>General</h3>
				<%=LeftMenuService.getMenuHtml(ranks, request) %>
			</div>
		</div>
		<!-- /sidebar menu -->

		<!-- /menu footer buttons -->
		<div class="sidebar-footer hidden-small">
			<a data-toggle="tooltip" data-placement="top" title="Settings"> <span
				class="glyphicon glyphicon-cog" aria-hidden="true"></span>
			</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
				<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
			</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
				class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
			</a> <a data-toggle="tooltip" data-placement="top" title="Logout"
				href="<%=pathLeft + "/login/logout"%>"> <span class="glyphicon glyphicon-off"
				aria-hidden="true"></span>
			</a>
		</div>
		<!-- /menu footer buttons -->
	</div>
</div>