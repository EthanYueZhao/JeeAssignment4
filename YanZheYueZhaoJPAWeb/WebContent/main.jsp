<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main page</title>
</head>
<body>
<h2>Main page</h2>
<%@ include file="banner.jsp" %>
<form action="${pageContext.request.contextPath}/main" method="post">
<p>To work with an existing course, enter the course code</p>
<input type="text" id="courseCode" name="courseCode">
<input type="submit" name="submit" value="Get">
<hr/>
<p>To add a new course:</p>
<input type="submit" name="submit" value="Add">  
</form>
<hr/>
</body>
</html>