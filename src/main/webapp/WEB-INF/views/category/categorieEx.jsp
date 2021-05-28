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
<head>
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
    <title>prodotti di una category</title>
</head>
<body>
<table>
    <tr>
        <th>nome</th>
        <th>descrizione</th>
        <th>prezzo</th>
    </tr>
    <c:forEach items="${prodotti}" var="product">
        <tr>
            <th>${product.nome}</th>
            <th>${product.descrizione}</th>
            <th>${product.prezzo}</th>
        </tr>
    </c:forEach>
</table>

</body>
</html>
