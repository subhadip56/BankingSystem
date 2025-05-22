<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SecureBank - Register Step 1</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/Register1.css">
</head>
<body>
	<h1>SecureBank</h1>

	<div class="register-container">
		<form class="register-form" action="<c:url value='/RegisterStep1'/>"
			method="post">
			<h2>Register - Step 1: Personal Details</h2>

			<c:if test="${not empty error}">
				<p class="error-message">${error}</p>
			</c:if>

			<div class="form-group">
				<label for="name">Name</label> <input type="text" id="name"
					name="name" required>
			</div>

			<div class="form-group">
				<label for="fatherName">Father's Name</label> <input type="text"
					id="fatherName" name="fatherName" required>
			</div>

			<div class="form-group">
				<label>Gender</label>
				<div class="radio-group">
					<label><input type="radio" name="gender" value="Male"
						required> Male</label> <label><input type="radio"
						name="gender" value="Female"> Female</label> <label><input
						type="radio" name="gender" value="Other"> Other</label>
				</div>
			</div>

			<div class="form-group">
				<label for="dob">Date of Birth</label> <input type="date" id="dob"
					name="dob" required>
			</div>

			<div class="form-group">
				<label for="email">Email Address</label> <input type="email"
					id="email" name="email" required>
			</div>

			<div class="form-group">
				<label>Marital Status</label>
				<div class="radio-group">
					<label><input type="radio" name="maritalStatus"
						value="Single" required> Single</label> <label><input
						type="radio" name="maritalStatus" value="Married"> Married</label>
				</div>
			</div>

			<div class="form-group">
				<label for="address">Address</label>
				<textarea id="address" name="address" rows="3" required></textarea>
			</div>

			<div class="form-group">
				<label for="city">City</label> <input type="text" id="city"
					name="city" required>
			</div>

			<div class="form-group">
				<label for="pinCode">Pin Code</label> <input type="text"
					id="pinCode" name="pinCode" required pattern="[0-9]{6}">
			</div>

			<div class="form-group">
				<label for="state">State</label> <input type="text" id="state"
					name="state" required>
			</div>


			<button type="submit" class="button">Next</button>
		</form>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>
