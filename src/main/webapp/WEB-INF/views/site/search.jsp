<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Ricerca Prodotto"/>
        <jsp:param name="styles" value="site"/>
        <jsp:param name="scripts" value="site,header"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/site/header.jsp" %>
<main class="app grid-y">
    <section class="body grid-x">
        <div class="grid-y">
            <form method="get" action="/products/search">
                <fieldset class="grid-y search-form">
                    <legend style="background-color: var(--primary); color:white; border: solid white; border-radius: 20px">Ricerca avanzata</legend>
                    <label for="name">
                        <input type="text" name="name" id="name" placeholder="Nome">
                    </label>
                    <label for="category">
                        <select id="category">
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.categoryId}">${category.categoryName}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <label for="minprice">
                        <input type="number" id="minprice" name="minprice" placeholder="Prezzo Min.">
                    </label>
                    <label for="maxprice">
                        <input type="number" id="maxprice" name="maxprice" placeholder="Prezzo Max.">
                    </label>
                    <button type="submit" class="btn">Cerca</button>
                </fieldset>
            </form>
        </div>

        <div class="grid-y result">
            <c:forEach items="${products}" var="product">
                <%@ include file="../partials/site/card.jsp" %>
            </c:forEach>
        </div>
    </section>
</main>
<%@include file="../partials/site/footer.jsp" %>

</body>
</html>
