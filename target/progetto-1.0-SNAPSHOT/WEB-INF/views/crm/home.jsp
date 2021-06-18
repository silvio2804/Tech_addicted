<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!doctype html>
<html>
<head>
    <%-- head dinamico --%>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="param" value="Tech addicted home"/>
        <jsp:param name="styles" value="crm,dashboard"/>
        <jsp:param name="scripts" value="crm,dashboard"/>
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
        <header class="topbar grid-x align-center">
            <%@include file="../../../icons/menu.svg" %>
            <label class="field command">
                <input type="text" placeholder="Cerca Comandi">
            </label>
            <span class="account">
                <%@include file="../../../icons/user.svg" %>
                Benvenuto amministratore
            </span>
        </header>
        <div class="body grid-x justify-center">
            <div class="grid-y cell w40">
                <h4>Clienti Registrati</h4>
                <h2>24</h2>
            </div>
            <div class="grid-y cell w40">
                <h4>Prodotti in magazzino</h4>
                <h2>135</h2>
            </div>
            <div class="grid-y cell w40">
                <h4>Incasso mensile</h4>
                <h2>1200 euro</h2>
            </div>
            <div class="grid-y cell w40">
                <h4>Ordini mensili</h4>
                <h2>133</h2>
            </div>
        </div>
        <footer class="info">
            <p>Copyright © 2021 Tutti i diritti riservati.</p>
        </footer>
    </section>
</main>
</body>
</html>
