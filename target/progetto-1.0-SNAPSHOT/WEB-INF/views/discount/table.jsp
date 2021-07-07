<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<table class="table product-table">
    <caption>Lista sconti<a href=${pageContext.request.contextPath}/discounts/create class="btn primary"> Crea sconto</a></caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Nome sconto</th>
        <th>Percentuale</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${discounts.isEmpty()}">
            <tr>
                <td>Nessuno sconto presente!</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${discounts}" var="discount">
                <tr>
                    <td data-head="Id">
                        <a href="/progetto_war_exploded/discounts/show?id=${discount.discountId}">${discount.discountId}</a>
                    </td>
                    <td data-head="Nome sconto">${discount.discountName}</td>
                    <th data-head="Percentuale">${discount.percentage}</th>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
