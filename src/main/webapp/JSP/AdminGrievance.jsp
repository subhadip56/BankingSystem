<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Manage Grievances</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/GrievanceRequest.css" />
</head>
<body>
	<div class="container">
		<h2>All Customer Grievances</h2>

		<c:if test="${not empty param.message}">
			<div class="message">${param.message}</div>
		</c:if>

		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Customer</th>
					<th>Subject</th>
					<th>Description</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="g" items="${allGrievances}">
					<tr>
						<td>${g.grievanceId}</td>
						<td>${g.customerId}</td>
						<td>${g.subject}</td>
						<td>${g.description}</td>
						<!-- each row gets its own form: -->
						<td colspan="2">
							<form
								action="${pageContext.request.contextPath}/AdminGrievanceServlet"
								method="post"
								style="display: flex; gap: 0.5rem; align-items: center;">
								<input type="hidden" name="grievanceId" value="${g.grievanceId}" />
								<select name="status">
									<c:forEach var="s"
										items="${fn:split('Pending,In Progress,Resolved,Rejected',',')}">
										<option value="${s}" ${s == g.status ? 'selected' : ''}>${s}</option>
									</c:forEach>
								</select>
								<button type="submit">Update</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<br> <a class="back-link" href="${pageContext.request.contextPath}/JSP/admin_dashboard.jsp">← Back
			to Dashboard</a>
	</div>
</body>
</html>
