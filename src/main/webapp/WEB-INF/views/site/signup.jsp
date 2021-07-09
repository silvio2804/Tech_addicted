<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it" dir="ltr">

<body>
<div class="backImage"></div>

<main class="app">

<jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="Singup"/>
    <jsp:param name="styles" value="sign"/>
</jsp:include>


<form class="app grid-x justify-center align-center" action="/progetto_war_exploded/site/home" method="post"> <!-- qui chiama la servlet crm in http/crm/dashboard!-->
    <fieldset class="grid-y cell w33 login">

        <span style="text-align: center; margin: 0px"><h2 >Registrazione</h2></span>

        <label for="email" class="field cell w33">
            <input id="email" name="email" placeholder="Email" type="email">
        </label>

        <label for="emailCheck" class="field cell w 40">
            <input id="emailCheck" name="emailCheck" placeholder="Conferma email" type="email">
        </label>

        <label for="password" class="field cell w33" >
            <input id="password" name="password" placeholder="password" type="text">
        </label>

        <label for="passwordCheck" class="field cell w33" >
            <input id="passwordCheck" name="passwordCheck" placeholder="Conferma password" type="text">
        </label>

        <button type="submit" class="cell w33 btn primary">Registrati</button>

    </fieldset>
</form>
</main>
</body>
<%@include file="../partials/crm/footer.jsp" %>
</html>
