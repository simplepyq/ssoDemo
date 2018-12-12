<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/7 0007
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>$Title$</title>
	</head>
	<body>
		<h1>This is Login Page!</h1>
		<form action="/user/login" method="post">
			<input type="hidden" name="redirectUrl" value="${sessionScope.redirectUrl}">
			<input type="text" name="username">
			<input type="password" name="password">
			<input type="submit" value="submit">
		</form>
		<h1>${sessionScope.errorMsg}</h1>
	</body>
</html>
