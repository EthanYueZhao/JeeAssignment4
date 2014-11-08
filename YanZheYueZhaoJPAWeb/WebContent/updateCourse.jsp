<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update course</title>
</head>
<body>
<body>
	<%@ include file="banner.jsp"%>
	<h2>Enter new values to update a course</h2>
	<form action="${pageContext.request.contextPath}/update" method="post">
		<table>
			<tr>
				<td>Course code</td>
				<td><b>${sessionScope.course.courseCode}</b></td>
			</tr>
			<tr>
				<td>Course title</td>
				<td><input type="text" name="courseTitle"
					value="${sessionScope.course.courseTitle}" /></td>
			</tr>
			<tr>
				<td>Current Professor</td>
				<td><b> <c:choose>
							<c:when test="${sessionScope.course.professor == null}">Not yet assigned</c:when>
							<c:otherwise>${sessionScope.course.professor.firstname} ${sessionScope.course.professor.lastname}</c:otherwise>
						</c:choose>
				</b></td>
			</tr>
			<tr>
				<td>Select new Professor</td>
				<td><select name="profName">
						<c:choose>
							<c:when test="${sessionScope.course.professor == null}">
								<option value="TBA" selected>Not assigned</option>
							</c:when>
							<c:otherwise>
								<option value="${sessionScope.course.professor.profid}" selected>${sessionScope.course.professor.firstname} ${sessionScope.course.professor.lastname}
								</option>
								<option value="TBA">Not assigned</option>
							</c:otherwise>
						</c:choose>
						<c:forEach items="${requestScope.professors}" var="prof">
							<option value="${prof.getProfid()}">${prof.getFirstname()} ${prof.getLastname()}</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<input type="submit" name="submit" value="Update" /> <input
			type="reset" name="reset" value="Reset form" /> <input type="submit"
			name="submit" value="Cancel" />
	</form>
</body>
</html>