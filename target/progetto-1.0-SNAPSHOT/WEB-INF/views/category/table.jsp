<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<table class="table product-table">
    <caption>Lista categorie<a href=${pageContext.request.contextPath}/categories/create class="btn primary"> Crea categoria</a></caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Nome categoria</th>

    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${categories.isEmpty()}">
            <tr>
                <td>Nessuna categoria presente!</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td data-head="Id">
                        <a href="/progetto_war_exploded/categories/show?id=${category.categoryId}">${category.categoryId}</a>
                    </td>
                    <td data-head="Nome">${category.categoryName}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
