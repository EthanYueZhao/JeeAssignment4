<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Banner</title>
</head>
<body>
<h3>Welcome ${sessionScope.userName}</h3>
<p>There are <b>${applicationScope.courseCount}</b> courses in our system.</p>
<p>The last update time:<b> ${applicationScope.lastUpdated}</b></p> 
<form action="${pageContext.request.contextPath}/login" method="get">
<input type="submit" value="log out"/> 
</form>
<hr/>
</body>
</html>