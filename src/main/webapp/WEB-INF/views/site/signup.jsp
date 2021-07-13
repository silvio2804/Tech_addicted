<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it" dir="ltr">

<body>
<jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="Singup"/>
    <jsp:param name="styles" value="sign"/>
    <jsp:param name="scripts" value="signform"/>
</jsp:include>

<main class="app">
<form name="registerForm" class="app grid-x justify-center align-center" method="post" onsubmit="return checkPass()">

    <fieldset class="grid-y cell w33 login">

        <span style="text-align: center; margin: 0px"><h2 >Registrazione</h2></span>

        <label for="name" class="field cell w33">
            <input id="name" name="name" placeholder="Nome" type="text" required>
        </label>

        <label for="lastName" class="field cell w33">
            <input id="lastName" name="lastName" placeholder="Cognome" type="text" required>
        </label>

        <label for="dataNa" class="field cell w33">
            <input id="dataNa" name="dataNa" placeholder="Data nascita" type="date" required>
        </label>

        <label for="email" class="field cell w33">
            <input id="email" name="email" placeholder="Email" type="email">
        </label>

        <label for="checkEmail" class="field cell w33">
            <input id="checkEmail" name="checkEmail" placeholder="Conferma email" type="email">
        </label>

        <label for="password" class="field cell w33" >
            <input id="password" name="password" type="password" placeholder="password" required>
        </label>

        <label for="checkPassword" class="field cell w33" >
            <input id="checkPassword" name="checkPassword" type="password" placeholder="Conferma password" required>
        </label>

        <button type="submit" class="cell w33 btn primary" >Registrati</button>

    </fieldset>
</form>
</main>

</body>
</html>