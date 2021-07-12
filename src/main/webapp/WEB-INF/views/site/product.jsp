<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Dettagli ${product.name}"/>
        <jsp:param name="styles" value="site"/>
        <jsp:param name="scripts" value="site,header"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/site/header.jsp" %>
<main class="app grid-y">
    <section class="grid-x align-center">
        <figure class="cell w25 product-card">
            <img src="/progetto_war_exploded/covers/${product.cover}" alt="Immagine prodotto" height="250" width="250">
            <figcaption class="product-card">
                <form method="post" action="/progetto_war_exploded/carts/add" class="grid-x">
                    <input type="hidden" name="id" value="${product.productId}">
                    <label for="quantity" class="field cell">
                        <input type="number" id="quantity" name="quantity" placeholder="Quantità" value="1">
                        <button type="submit" class="btn primary">Aggiungi al carrello</button>
                    </label>
                </form>
            </figcaption>
        </figure>
        <dl class="cell w50">
            <dt>Nome</dt>
            <dd>${product.name}</dd>
            <dt>Descrizione</dt>
            <dd>${product.description}</dd>
            <dt>Prezzo</dt>
            <dd>${product.price}</dd>
            <dt>Categoria</dt>
            <dd>${product.getCategory().getCategoryName()}</dd>
        </dl>
    </section>
</main>
<%@include file="../partials/site/footer.jsp" %>
</body>
</html>
