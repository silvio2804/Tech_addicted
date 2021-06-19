<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="param" value="Tech addicted home"/>
        <jsp:param name="styles" value="crm,products"/>
        <jsp:param name="scripts" value="crm,"/>
    </jsp:include>
    <style>
        .product-form > * {
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
<main class="app">
    <%@include file="../partials/crm/sidebar.jsp" %>
    <section class="content grid-y">
        <%@include file="../partials/crm/header.jsp" %>
        <div class="body grid-x justify-center">
            <form method="post" action="/progetto_war_exploded/products/create" enctype="multipart/form-data">
                <fieldset class="grid-y cell product-form">
                    <legend>Crea Prodotto</legend>
                    <label for="name" class="field cell w50">
                        <input type="text" id="name" name="name" placeholder="Nome">
                    </label>
                    <label for="price" class="field cell w50">
                        <input type="text" id="price" name="price" placeholder="Prezzo">
                    </label>
                    <label for="description" class="field cell w50">
                        <input type="text" id="description" name="description" placeholder="Descrizione">
                    </label>
                    <label for="categoryId" class="field cell w50">
                        <select name="categoryId" id="categoryId">
                            <option value="1">notebook</option>
                            // da completare con le categorie prese automaticamente dal DB
                        </select>
                    </label>
                    <label for="cover" class="field cell w50">
                        <input type="file" id="cover" name="cover">
                    </label>
                    <button type="submit" class="cell w50 btn primary">Crea</button>
                </fieldset>
            </form>
        </div>
        <%@include file="../partials/crm/footer.jsp" %>
    </section>
</main>
</body>
</html>