<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<table class="table product-table">
    <caption><a href="../products/create"> Lista Prodotti</a></caption>
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
        <c:when test="${product.isEmpty()}">
        <tr>
            <td>Nessun prodotto presente!</td>
        </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="resource" var="product">
                <tr>
                    <td data-head="Id">
                            <a href="/Techaddicted/products/show?id=${product.id}">${product.id}</a>
                    </td>
                    <td data-head="Url">
                            <a href="/Techaddicted/covers/${product.cover}">Immagine</a>
                    </td>
                    <td data-head="Nome">${product.nome}</td>
                    <td data-head="Prezzo">${product.prezzo}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>