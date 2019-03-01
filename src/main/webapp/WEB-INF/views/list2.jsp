<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工列表</title>
<!--设置APP_PATH值  -->
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<link
	href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${APP_PATH}/static/jquery-3.2.1/jquery-3.2.1.min.js"></script>
</head>
<body>

	<!-- <button class="btn btn-success">button</button> -->

	<div class="container">
		<!--标题  -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!--表头 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8 ">
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!--数据  -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>#</th>
						<th>姓名</th>
						<th>性别</th>
						<th>邮箱</th>
						<th>部门</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${PageInfo.list}" var="emp">
						<tr>
							<th>${emp.empId}</th>
							<th>${emp.empName}</th>
							<th>${emp.gender=="M"?"男":"女"}</th>
							<th>${emp.email}</th>
							<th>${emp.department.deptName}</th>
							<th>
								<button class="btn btn-primary">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true">
									</span>新增
								</button>
								<button class="btn btn-danger">
									<span class="glyphicon glyphicon-remove" aria-hidden="true">
									</span>删除
								</button>
							</th>
						</tr>
					</c:forEach>





				</table>
			</div>
		</div>

		<!--PageInfo{pageNum=1, pageSize=5, size=5, startRow=1, 
		endRow=5, total=2000, pages=400, list=Page{count=true, 
		pageNum=1, pageSize=5, startRow=0, endRow=5, total=2000, 
		pages=400, reasonable=false, pageSizeZero=false}, prePage=0, 
		nextPage=2, isFirstPage=true, isLastPage=false, 
		hasPreviousPage=false, 
		hasNextPage=true, navigatePages=5, navigateFirstPage=1, 
		navigateLastPage=5, navigatepageNums=[1, 2, 3, 4, 5]}  -->
		<!--分页信息  -->
		<div class="row">
			<!-- 实时显示数据信息  -->
			<div class="col-md-6">
				当前第${PageInfo.pageNum}页,总${PageInfo.pages} 页,总${PageInfo.total}条记录。
			</div>

			<div class="col-md-6">
				<nav aria-label="Page navigation">
				<ul class="pagination">
					<li><a href="${APP_PATH}/emps?pn=1">首页</a></li>
					<c:if test="${PageInfo.hasPreviousPage }">
						<li><a href="${APP_PATH}/emps?pn=${PageInfo.pageNum-1}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>
					<!-- 连续显示的页码 -->
					<c:forEach items="${PageInfo.navigatepageNums}" var="page_num">
						<!--是当前页PageInfo.pageNum，显示激活状态  -->
						<c:if test="${page_num== PageInfo.pageNum}">
							<li class="active"><a href="#">${page_num}</a></li>
						</c:if>
						<!--不是是当前页，显示正常状态  -->
						<c:if test="${page_num != PageInfo.pageNum}">
							<li ><a href="${APP_PATH}/emps?pn=${page_num}">${page_num}</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${PageInfo.hasNextPage }">
						<li><a href="${APP_PATH}/emps?pn=${PageInfo.pageNum+1}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>

					<li><a href="${APP_PATH}/emps?pn=${PageInfo.pages}">末页</a></li>
				</ul>
				</nav>
			</div>


		</div>

	</div>

</body>
</html>