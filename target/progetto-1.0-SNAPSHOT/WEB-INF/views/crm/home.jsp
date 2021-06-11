<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!doctype html>
<html>
<head>
<%-- head dinamico --%>
<jsp:include page="../partials/head.jsp">
    <jsp:param name="param" value="Tech addicted home"/>
    <jsp:param name="styles" value="crm, dashboard"/>
    <jsp:param name="scripts" value="crm, dashboard"/>
</jsp:include>
</head>
<body>
<main class="app">
    <aside class="sidebar">
        <nav class="menu grid-y align-center">
        <img src="../images/logo.png" width="100" height="100">
        <a href="#">Gestione Clienti</a>
        <a href="#">Gestione Prodotti</a>
        <a href="#">Gestione Ordini</a>
        <a href="#">Gestione Categorie</a>
        <a href="#">Gestione Promozioni</a>
        <a href="#">Profilo</a>
        <a href="#">Logout</a>
        </nav>
    </aside>
    <section class="content grid-y">
        <header class="topbar">
            header
        </header>
        <div class="body"></div>
        <footer class="info">
            <p>footer</p>
        </footer>
    </section>
</main>
</body>
</html>
