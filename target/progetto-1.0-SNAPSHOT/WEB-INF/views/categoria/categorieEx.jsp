<%--
  Created by IntelliJ IDEA.
  User: silvi
  Date: 25/05/2021
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../partials/head.jsp"%>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
    </style>
    <title>prodotti di una categoria</title>

<body>
<table>
    <tr>
        <th>nome</th>
        <th>descrizione</th>
        <th>prezzo</th>
    </tr>
    <c:forEach items="${prodotti}" var="prodotto">
        <tr>
            <th>${prodotto.nome}</th>
            <th>${prodotto.descrizione}</th>
            <th>${prodotto.prezzo}</th>
        </tr>
    </c:forEach>
</table>

</body>
</html>
