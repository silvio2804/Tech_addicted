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

    <style>

        .app {
            background: linear-gradient(var(--primary), var(--white));
        }

        .login {
            padding: 1rem;
            background-color: var(--white);
            border-radius: 10px;
        }

        .login > * {
            margin: 10px;
        }
    </style>
</head>
<body>
<form class="app grid-x justify-center align-center" action="/progetto_war_exploded/crm/dashboard" method="get">
    <fieldset class="grid-y cell w50 login">
        <h2> Login Pannello Admin</h2>
        <span>Email</span>
        <label for="email" class="field">
            <input type="email" name="email" id="email" placeholder="Email">
        </label>
        <span>Password</span>
        <label for="password" class="field">
            <input type="password" name="password" id="password" placeholder="Password">
        </label>
        <button type="submit" class="btn primary">Accedi</button>
    </fieldset>
</form>
</body>
</html>
