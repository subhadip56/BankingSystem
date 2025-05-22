<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="en_US"/>
<fmt:setTimeZone value="Asia/Kolkata"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>My Grievances</title>
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/CSS/GrievanceRequest.css">
</head>
<body>
  <div class="container">
    <h2>My Grievances</h2>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Subject</th>
          <th>Description</th>
          <th>Status</th>
          <th>Created</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="g" items="${grievances}">
          <tr>
            <td>${g.grievanceId}</td>
            <td>${g.subject}</td>
            <td>${g.description}</td>
            <td>${g.status}</td>
            <td>
              <fmt:formatDate
                value="${g.createdAt}"
                pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <br/><br/>
    <a class="back-link"
       href="${pageContext.request.contextPath}/JSP/CustomerDashboard.jsp">
      ← Back to Dashboard
    </a>
  </div>
</body>
</html>
