<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Create Grievance</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/GrievanceRequest.css">
</head>
<body>
	<div class="container">
		<h2>Submit a Grievance</h2>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<form
			action="${pageContext.request.contextPath}/GrievanceRequestServlet"
			method="post">
			<label for="subject">Subject:</label> <input type="text" id="subject"
				name="subject" required /> <label for="description">Description:</label>
			<textarea id="description" name="description" rows="5" cols="50"
				required></textarea>

			<button type="submit">Submit</button>
		</form>
	</div>
</body>
</html>
