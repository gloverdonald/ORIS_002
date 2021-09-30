<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 9/26/21
  Time: 5:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
</head>
<body>
<form action="/sign-in" method="post">
    <input name="username" type="text" placeholder="username"/>
    <input name="password" type="password" placeholder="password"/>
    <input type="submit">
</form>
</body>
</html>