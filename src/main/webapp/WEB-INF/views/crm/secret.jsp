<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 27/05/2021
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it" dir="ltr">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Login Admin"/>
    </jsp:include>
</head>
<body>
<form action="/secret" method="post">
    <fieldset>
        <h2> Login Pannello Admin</h2>
        <span>Email</span>
        <label for="email">
            <input type="email" name="email" id="email" placeholder="Email">
        </label>
        <span>Password</span>
        <label for="password">
            <input type="password" name="password" id="password" placeholder="Password">
        </label>
        <button type="submit">Accedi</button>
    </fieldset>
</form>
</body>
</html>
