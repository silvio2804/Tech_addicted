<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<table class="table product-table">
    <caption><a href="/accounts/create"> Lista Account</a></caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Email</th>
        <th>Data nascita</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${accounts.isEmpty()}">
            <tr>
                <td>Nessun account presente!</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${accounts}" var="account">
                <tr>
                    <td data-head="Id">
                        <a href="/progetto_war_exploded/accounts/show?id=${account.id}">${account.id}</a>
                    </td>
                    <td data-head="Nome">${account.name}</td>
                    <td data-head="Cognome">${account.lastName}</td>
                    <td data-head="email">${account.name}</td>
                    <td data-head="Data nascita">${account.date}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
