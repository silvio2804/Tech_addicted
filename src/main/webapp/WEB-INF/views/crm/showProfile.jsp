<%@ page import="Model.account.Account" %>
<%@ page import="java.util.Optional" %>
<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="param" value="Show administrator profile"/>
        <jsp:param name="styles" value="crm,dashboard"/>
        <jsp:param name="scripts" value="crm,dashboard"/>
    </jsp:include>
</head>
<body>
<% Optional<Account> profileAccount = (Optional<Account>) request.getAttribute("profileAccount");%>
<main class="app">
    <%@include file="../partials/crm/sidebar.jsp" %>
    <section class="content grid-y">
        <%@include file="../partials/crm/header.jsp" %>


        <div class="body grid-x align-center">
            <div class="proimg">
                <div style="align-items: center"> <img src="../images/profile.png" width="250" height="270"></div></div>
            <div class="grid-y justify-center profile" id="profileform">
                <label for="id utente">id utente: </label> <br>
                <input type="text" readonly id="id utente" value="<%=profileAccount.get().getId()%>"> <br>

                <label for="nome">nome:</label> <br>
                <input type="text" readonly id="nome" value="<%=profileAccount.get().getName()%>"> <br>

                <label for="cognome">cognome: </label> <br>
                <input type="text" readonly id="cognome" value="<%=profileAccount.get().getLastName()%>"> <br>

                <label for="email">email: </label> <br>
                <input type="text" readonly id="email" value="<%=profileAccount.get().getEmail()%>"> <br>

                <label for="sesso">sesso: </label> <br>
                <input type="text" readonly id="sesso" value="<%=profileAccount.get().getGender()%>"> <br>
            </div>
        </div>

    </section>
</main>
<%@include file="../partials/crm/footer.jsp" %>
</body>
</html>

