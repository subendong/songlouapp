<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-striped jambo_table bulk_action">
	<thead>
		<tr class="headings">
			<th class="column-title col-md-1" style="display: table-cell;">ID
			</th>
			<th class="column-title col-md-4" style="display: table-cell;">资源名称</th>
			<th class="column-title col-md-2" style="display: table-cell;">模块
			</th>
			<th class="column-title col-md-2" style="display: table-cell;">操作</th>
			<th class="column-title col-md-1" style="display: table-cell;">菜单栏显示</th>
			<th class="column-title col-md-1" style="display: table-cell;">排序
			</th>
			<th class="column-title no-link last col-md-1"
				style="display: table-cell;"><span class="nobr">操作</span>
			</th>
		</tr>
	</thead>

	<tbody>
		<%
			int i = 0;
			String trClass = "";
		%>
		<c:forEach var="rank" items="${pagingModel.getDatas()}">
			<%
				i++;
				trClass = i % 2 == 1 ? "even" : "odd";
			%>
			<tr class="<%=trClass%> pointer">
				<td class=" ">${rank.getId()}</td>
				<td class=" ">
					<c:forEach var="i" begin="1" end="${rank.getDepth() - 1}" step="1">   
					<c:out value="|----------" />
					</c:forEach>  
					${rank.getRankName()}
				</td>
				<td class=" ">${rank.getController()}</td>
				<td class=" ">${rank.getAction()}</td>
				<td class=" ">${rank.getLeftShow() == 1 ? "√" : "×"}</td>
				<td class=" ">${rank.getInnerOrder()}</td>
				<td class=" last">
					<a href="#" class="aEdit" rankId="${rank.getId()}">修改</a>
					<a href="#" class="aDelete" rankId="${rank.getId()}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<input type="hidden" name="totalRecord" id="totalRecord" value="${pagingModel.getTotalRecord()}" />
<input type="hidden" name="totalPage" id="totalPage" value="${pagingModel.getTotalPage()}" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${pagingModel.getPageIndex()}" />
<input type="hidden" name="pageSize" id="pageSize" value="${pagingModel.getPageSize()}" />
<input type="hidden" name="deleteUrl" id="deleteUrl" value="<%=request.getContextPath() %>/rank/delete" />