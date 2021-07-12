<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="param" value="Tech addicted home"/>
        <jsp:param name="styles" value="crm,category"/>
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
            <form method="post" action="/progetto_war_exploded/categories/create">
                <fieldset class="grid-y cell product-form">
                    <legend>Crea Categoria</legend>
                    <label for="nameCategory" class="field cell w50"> </label>
                        <input type="text" id="nameCategory" name="nameCategory" placeholder="Nome">
                    <button type="submit" class="cell w50 btn primary">Crea</button>
                </fieldset>
            </form>
        </div>
    </section>
</main>
<%@include file="../partials/crm/footer.jsp" %>
</body>
</html>
