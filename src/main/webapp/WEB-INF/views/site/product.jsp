<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Dettagli ${product.fullName}"/>
        <jsp:param name="styles" value="site"/>
        <jsp:param name="scripts" value="site"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/site/header.jsp" %>
<main class="app grid-y">
    <figure class="cell w25 product-card">
        <img src="${product.image}" alt="Immagine prodotto" class="fluid-img">
        <figcaption>
            <form method="post" action="/progetto_war_exploded/carts/add" class="grid-y">
                <input type="hidden" name="id" value="${product.id}">
                <label for="quantity" class="field cell">
                    <input type="number" id="quantity" name="quantity" placeholder="Quantità" value="1">
                    <button type="submit" class="btn primary">Aggiungi al carrello</button>
                </label>
            </form>
        </figcaption>
    </figure>
    <dl class="cell w50">
        <dt>Nome</dt>
        <dd>${product.fullName}</dd>
        <dt>Descrizione</dt>
        <dd>${product.resume}</dd>
        <dt>Prezzo</dt>
        <dd>${product.price}</dd>
        <dt>Categoria</dt>
        <dd>${product.category.label}</dd>
    </dl>
</main>
<%@include file="../partials/site/footer.jsp" %>
</body>
</html>
