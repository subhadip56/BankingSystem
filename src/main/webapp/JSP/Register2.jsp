<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SecureBank - Additional Details</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/Register2.css">
</head>
<body>

	<h1>SecureBank</h1>

	<div class="register-container">
		<form action="<c:url value='RegisterStep2'/>" method="post"
			class="register2-form">
			<h2>Page 2: Additional Details</h2>

			<c:if test="${not empty error}">
				<p class="error-message">${error}</p>
			</c:if>

			<div class="form-group">
				<label for="religion">Religion :</label> <select name="religion"
					id="religion" required>
					<option value="">--Select--</option>
					<option value="Hindu">Hindu</option>
					<option value="Muslim">Muslim</option>
					<option value="Christian">Christian</option>
					<option value="Other">Other</option>
				</select>
			</div>

			<div class="form-group">
				<label for="category">Category :</label> <select name="category"
					id="category" required>
					<option value="">--Select--</option>
					<option value="General">General</option>
					<option value="OBC">OBC</option>
					<option value="SC">SC</option>
					<option value="ST">ST</option>
				</select>
			</div>

			<div class="form-group">
				<label for="income">Income :</label> <select name="income"
					id="income" required>
					<option value="">--Select--</option>
					<option value="Null">Null</option>
					<option value="Below 150000">Below 1,50,000</option>
					<option value="1,50,000-2,50,000">1,50,000 - 2,50,000</option>
					<option value="Above 250000">Above 2,50,000</option>
				</select>
			</div>

			<div class="form-group">
				<label for="education">Educational :</label> <select
					name="education" id="education" required>
					<option value="">--Select--</option>
					<option value="Non-Graduate">Non-Graduate</option>
					<option value="Graduate">Graduate</option>
					<option value="Post-Graduate">Post-Graduate</option>
					<option value="Doctorate">Doctorate</option>
					<option value="Others">Others</option>
				</select>
			</div>

			<div class="form-group">
				<label for="occupation">Occupation :</label> <select
					name="occupation" id="occupation" required>
					<option value="">--Select--</option>
					<option value="Salaried">Salaried</option>
					<option value="Self-Employed">Self-Employed</option>
					<option value="Business">Business</option>
					<option value="Student">Student</option>
					<option value="Retired">Retired</option>
					<option value="Others">Others</option>
				</select>
			</div>

			<div class="form-group">
				<label for="pan">PAN Number :</label> <input type="text" id="pan"
					name="pan" placeholder="Enter PAN Number" required>
			</div>

			<div class="form-group">
				<label for="aadhar">Aadhar Number :</label> <input type="text"
					id="aadhar" name="aadhar" placeholder="Enter Aadhar Number"
					required>
			</div>

			<div class="form-group">
				<label>Senior Citizen :</label>
				<div class="radio-group">
					<label><input type="radio" name="seniorCitizen" value="Yes"
						required> Yes</label> <label><input type="radio"
						name="seniorCitizen" value="No"> No</label>
				</div>
			</div>

			<div class="form-group">
				<label>Existing Account :</label>
				<div class="radio-group">
					<label><input type="radio" name="existingAccount"
						value="Yes" required> Yes</label> <label><input
						type="radio" name="existingAccount" value="No"> No</label>
				</div>
			</div>

			<button type="submit" class="form-button">Next</button>

		</form>
	</div>


	<jsp:include page="footer.jsp" />
</body>
</html>
