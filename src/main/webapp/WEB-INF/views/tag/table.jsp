<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<table class="table product-table">
    <caption><a href="../tags/create"> Lista Tag</a></caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Parola</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${tags.isEmpty()}">
            <tr>
                <td>Nessun tag presente!</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${tags}" var="tag">
                <tr>
                    <td data-head="Id">
                        <a href="/progetto_war_exploded/tags/show?id=${tag.tagId}">${tag.tagId}</a>
                    </td>
                    <td data-head="Parola">${tag.word}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
