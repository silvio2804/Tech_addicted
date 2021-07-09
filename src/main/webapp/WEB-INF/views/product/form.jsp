<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="product" class="Model.product.Product" scope="request"/>
<c:set var="isCreate" value="${product.productId == 0}"/>

<c:if test="${not empty alert}">
    <%@include file="../partials/alert.jsp"%>
</c:if>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="param" value="Tech addicted home"/>
        <jsp:param name="styles" value="crm,crmTable"/>
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

<form method="post"action="/progetto_war_exploded/products/${isCreate ? 'create': 'update'}" enctype="multipart/form-data">
    <c:if test="${not isCreate}">
        <input type="hidden" name="productId" value="${product.productId}">
    </c:if>
    <fieldset class="grid-y cell product-form">
        <legend>${isCreate ? 'Crea' : 'Aggiorna'} Prodotto</legend>
            <label for="name" class="field cell w50">
                <input type="text" id="name" name="name" placeholder="Nome" value="${product.name}">
            </label>
            <label for="price" class="field cell w50">
                <input type="text" id="price" name="price" placeholder="Prezzo" value="${product.price}">
            </label>
            <label for="description" class="field cell w50">
                <input type="text" id="description" name="description" placeholder="Descrizione" value="${product.description}">
            </label>
            <label for="categoryId" class="field cell w50">
                    <select name="categoryId" id="categoryId">
                        <c:forEach items="${categories}" var="category">
                        <option value=${category.categoryId}>
                                ${category.categoryName}
                        </option>
                    </c:forEach>
                    </select>
            </label>
            <label for="cover" class="field cell w50">
                <input type="file" id="cover" name="cover">
            </label>
        <button type="submit" class="cell w40 btn primary">${isCreate ? 'Crea' : 'Aggiorna'}</button>
    </fieldset>
</form>
        </div>
    </section>
</main>
<%@include file="../partials/crm/footer.jsp" %>
</body>
</html>