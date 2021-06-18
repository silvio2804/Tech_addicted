<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="param" value="Tech addicted home"/>
        <jsp:param name="styles" value="crm,dashboard"/>
        <jsp:param name="scripts" value="crm,dashboard"/>
    </jsp:include>
</head>
<body>
<main class="app">
    <%@include file="../partials/crm/sidebar.jsp" %>
    <section class="content grid-y">
        <%@include file="../partials/crm/header.jsp" %>
        <div class="body grid-x justify-center">
            <jsp:include page="../partials/crm/statscard.jsp">
                <jsp:param name="title" value="Clienti registrati"/>
                <jsp:param name="stat" value="24"/>
            </jsp:include>

            <jsp:include page="../partials/crm/statscard.jsp">
                <jsp:param name="title" value="Prodotti in magazzino"/>
                <jsp:param name="stat" value="135"/>
            </jsp:include>

            <jsp:include page="../partials/crm/statscard.jsp">
                <jsp:param name="title" value="Incasso mensile"/>
                <jsp:param name="stat" value="1200 euro"/>
            </jsp:include>

            <jsp:include page="../partials/crm/statscard.jsp">
                <jsp:param name="title" value="Ordini mensili"/>
                <jsp:param name="stat" value="133"/>
            </jsp:include>
        </div>
        <%@include file="../partials/crm/footer.jsp" %>
    </section>
</main>
</body>
</html>
