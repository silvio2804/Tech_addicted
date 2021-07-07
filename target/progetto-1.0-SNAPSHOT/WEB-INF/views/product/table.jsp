<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<table class="table product-table">
    <caption>Lista prodotti<a href=${pageContext.request.contextPath}/products/create class="btn primary"> Crea prodotto</a></caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Url</th>
        <th>Nome</th>
        <th>Prezzo</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${products.isEmpty()}">
        <tr>
            <td>Nessun prodotto presente!</td>
        </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td data-head="Id">
                            <a href="/progetto_war_exploded/products/show?id=${product.productId}">${product.productId}</a>
                    </td>
                    <td data-head="Url">
                            <a href="/progetto_war_exploded/covers/${product.cover}">Immagine</a>
                    </td>
                    <td data-head="Nome">${product.name}</td>
                    <td data-head="Prezzo">${product.price}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>