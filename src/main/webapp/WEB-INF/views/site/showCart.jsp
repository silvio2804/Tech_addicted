<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="it" dir="ltr">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Carrello"/>
        <jsp:param name="styles" value="site"/>
    </jsp:include>
</head>
<body>
    <main class="app grid-y">
        <%@include file="../partials/site/header.jsp"%>
        <section class="main grid-x justify-center">
            <c:choose>
                <c:when test="${empty sessionCart or sessionCart.items.isEmpty()}">
                    <h2 class="cell">Carrello vuoto</h2>
                </c:when>
                <c:otherwise>
                    <h1 class="cell">Totale carrello : ${sessionCart.total()}</h1>
                    <table class="table cell"></table>
                    <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Prezzo</th>
                        <th>Quantità</th>
                        <th>Azioni</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sessionCart.items}" var="cartEntry">
                        <tr>
                            <td data-head="Nome">${cartEntry.product.name}</td>
                            <td data-head="Prezzo">${cartEntry.product.price}</td>
                            <td data-head="Quantità">${cartEntry.product.quantity}</td>
                            <td data-head="Azioni">
                            <form method="post" action="progetto_war_exploded/carts/remove"class="cart-form">
                                <input type="hidden" name="id" value="${cartEntry.product.id}">
                                <button class="btn primary" type="submit">Rimuovi</button>
                            </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <form method="post" action="progetto_war_exploded/orders/create" class="cell">
                        <button type="submit" class="btn primary">Completa ordine</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </section>
    </main>
    <%@include file="../partials/site/footer.jsp"%>
</body>
</html>