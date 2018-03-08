<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-striped jambo_table bulk_action">
	<thead>
		<tr class="headings">
			<th class="column-title col-md-1" >
				<input type="checkbox" id="check-all" class="flat" />
			</th>
			<th class="column-title col-md-1" style="display: table-cell;">ID</th>
			<th class="column-title col-md-9" style="display: table-cell;">角色</th>
			<th class="column-title col-md-1 no-link last" style="display: table-cell;"><span class="nobr">操作</span>
			</th>
		</tr>
	</thead>

	<tbody>
		<%
			int i = 0;
			String trClass = "";
		%>
		<c:forEach var="role" items="${pagingModel.getDatas()}">
			<%
				i++;
				trClass = i % 2 == 1 ? "even" : "odd";
			%>
			<tr class="<%=trClass%> pointer">
				<td class="a-center ">
					<input type="checkbox" class="flat" name="id" value="${role.getId()}" />
				</td>
				<td class=" ">${role.getId()}</td>
				<td class=" ">${role.getName()}</td>
				<td class=" last">
					<a href="#" class="aEdit" roleId="${role.getId()}">修改</a>
					<a href="#" class="aSetRank" roleId="${role.getId()}">分配资源</a>
					<a href="#" class="aDelete" roleId="${role.getId()}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<input type="hidden" name="totalRecord" id="totalRecord" value="${pagingModel.getTotalRecord()}" />
<input type="hidden" name="totalPage" id="totalPage" value="${pagingModel.getTotalPage()}" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${pagingModel.getPageIndex()}" />
<input type="hidden" name="pageSize" id="pageSize" value="${pagingModel.getPageSize()}" />