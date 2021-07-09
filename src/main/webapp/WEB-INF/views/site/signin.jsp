<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it" dir="ltr">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Login"/>
        <jsp:param name="styles" value="css,sign"/>
    </jsp:include>

</head>
<body>
<form class="app grid-x justify-center align-center" action="/progetto_war_exploded/accounts/signin" method="post"> <!-- qui chiama la servlet crm in http/crm/dashboard!-->
    <fieldset class="grid-y cell w33 login">

        <span style="text-align: center;"  >
            <img src="${pageContext.request.contextPath}/images/logo.png" width="90" height="90">
        </span>
        <span ><h2 >Login</h2></span>
        <label for="email" class="field">
            <input type="email" name="email" id="email" placeholder="Email">
        </label>
        <label for="password" class="field">
            <input type="password" name="password" id="password" placeholder="Password">
        </label>
        <button type="submit" class="btn primary">Accedi</button>
        <span style="margin: 15px">
            <a href="${pageContext.request.contextPath}/accounts/signup">Registrati qui</a>
        </span>
    </fieldset>
</form>
<%@include file="../partials/crm/footer.jsp" %>
</body>
</html>
