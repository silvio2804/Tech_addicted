<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Ricerca Prodotto"/>
        <jsp:param name="styles" value="site"/>
        <jsp:param name="scripts" value="site"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/site/header.jsp" %>
<main class=" grid-x">
    <aside>
        <form method="get" action="/products/search">
            <fieldset>
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
                <button type="submit">Cerca</button>
            </fieldset>
        </form>
    </aside>

    <section>
        <div class="grid-y">
            <c:forEach items="${products}" var="product">
                <%@ include file="../partials/site/card.jsp" %>
            </c:forEach>
        </div>
    </section>
</main>
<%@include file="../partials/site/footer.jsp" %>

</body>
</html>
