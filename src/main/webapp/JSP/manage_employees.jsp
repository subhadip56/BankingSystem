<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Employees – SecureBank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/manage_employees.css">
</head>
<body>

	<div class="header">
		<h1>SecureBank</h1>
		<div class="nav-links">
			<a href="${pageContext.request.contextPath}/JSP/admin_dashboard.jsp">Dashboard</a>
			<a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
		</div>
	</div>

	<div class="page-title">
		<h2>Manage Employees</h2>
	</div>

	<div class="table-container">
		<table class="emp-table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Full Name</th>
					<th>Email</th>
					<th>Username</th>
					<th>Role</th>
					<th>Hired On</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody id="employeeTableBody">
				<!-- Populated in JS below -->
			</tbody>
		</table>

		<div id="noEmployeesMessage" style="display: none;">
			<p class="no-data">No employees found.</p>
		</div>
	</div>

	<!-- Pass employees data to JavaScript -->
	<script type="text/javascript">
        var employees = [
            <c:forEach var="e" items="${employees}" varStatus="status">
                {
                    id:        "${e.id}",
                    fullName:  "${e.fullName}",
                    email:     "${e.email}",
                    username:  "${e.username}",
                    role:      "${e.role}",
                    createdAt: "<fmt:formatDate value='${e.createdAt}' pattern='yyyy-MM-dd'/>"
                }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];
    </script>

	<script
		src="${pageContext.request.contextPath}/JAVA_SCRIPT/manage_employees.js"></script>
</body>
</html>
