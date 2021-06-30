<!DOCTYPE>

<html>

<jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="login utente"/>
</jsp:include>

<body>
<form method="post" action="/progetto_war_exploded/accounts/signin">
    <label>email:</label><br>
    <input type="email" id="email" name="email" value="email"><br>
    <label>password:</label><br>
    <input type="password" id="password" name="password" value="password"> <br>
    <input type="submit" value="accedi" name="accedi">
</form>
</body>
</html>