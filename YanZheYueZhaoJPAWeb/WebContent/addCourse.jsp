<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a course</title>
</head>
<body>
<h2>Add a new course</h2>
<%@ include file="banner.jsp" %><br><br>
<form action="${pageContext.request.contextPath}/add" method="post">
<table>
<tr>
<td>Course code:</td>
<td><input type="text" name="courseCode" value=""></td>
</tr><tr>
<td>Course title</td>
<td><input type="text" name="courseTitle" value=""></td>
</tr><tr>
<td valign="top">Professor</td>
<td>Select "TBA" for to be assigned
<select name="profName">
<option value="TBA" selected> TBA </option>
<c:forEach items="${requestScope.professors}" var="prof">
<option value="${prof}">${prof}</option>
</c:forEach>
</select>
</td>
</tr><tr>
</table>
<input type="submit" name="add" value="Add">
<input type="submit" name="cancel" value="Cancel">
</form>
</body>
</html>