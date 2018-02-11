<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-striped jambo_table bulk_action">
	<thead>
		<tr class="headings">
			<th class="column-title" >
				<input type="checkbox" id="check-all" class="flat" />
			</th>
			<th class="column-title" style="display: table-cell;">ID
			</th>
			<th class="column-title" style="display: table-cell;">权限名称</th>
			<th class="column-title" style="display: table-cell;">模块
			</th>
			<th class="column-title" style="display: table-cell;">操作</th>
			<th class="column-title" style="display: table-cell;">菜单栏显示</th>
			<th class="column-title" style="display: table-cell;">排序
			</th>
			<th class="column-title no-link last"
				style="display: table-cell;"><span class="nobr">操作</span>
			</th>
			<th class="bulk-actions" colspan="7" style="display: none;">
				<a class="antoo" style="color: #fff; font-weight: 500;">Bulk
					Actions ( <span class="action-cnt">1 Records
						Selected</span> ) <i class="fa fa-chevron-down"></i>
			</a>
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
				<td class="a-center ">
					<input type="checkbox" class="flat" name="table_records" />
				</td>
				<td class=" ">${rank.getId()}</td>
				<td class=" ">${rank.getRankName()}</td>
				<td class=" ">${rank.getControl()}</td>
				<td class=" ">${rank.getAction()}</td>
				<td class=" ">${rank.getLeftShow()}</td>
				<td class="a-right a-right ">${rank.getShowOrder()}</td>
				<td class=" last"><a href="#">删除</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<input type="hidden" name="totalRecord" id="totalRecord" value="${pagingModel.getTotalRecord()}" />
<input type="hidden" name="totalPage" id="totalPage" value="${pagingModel.getTotalPage()}" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${pagingModel.getPageIndex()}" />
<input type="hidden" name="pageSize" id="pageSize" value="${pagingModel.getPageSize()}" />