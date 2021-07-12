<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<table class="table product-table">
    <caption>Lista ordini<a href=${pageContext.request.contextPath}/orders/create class="btn primary"> Crea ordine</a></caption>

    <thead>
    <tr>
        <th>Id</th>
        <th>Id Account</th>
        <th>Data ordine</th>
        <th>Pagamento</th>
        <th>Totale</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${orders.isEmpty()}">
            <tr>
                <td>Nessun ordine presente!</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td data-head="Id">
                        <a href="/progetto_war_exploded/orders/show?id=${order.orderId}">${order.orderId}</a>
                    </td>
                    <td data-head="Id Account">
                        <a href="/progetto_war_exploded/account/show?id=${account.accountId}">${account.accountId}</a>
                    </td>
                    <td data-head="Data ordine">${order.orderDate}</td>
                    <td data-head="Pagamento">${order.payment}</td>
                    <td data-head="Totale">${order.total}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
