<%--
  Created by IntelliJ IDEA.
  User: silvi
  Date: 18/06/2021
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Benvenuti in Tech Addicted"/>
        <jsp:param name="styles" value="site"/>
    </jsp:include>
</head>
<body>
<main class="app grid-y">
    <%@include file="../partials/site/header.jsp"%>
    <section class="main grid-y">
        <img src="./images/top.jps">
        <h1>Categorie</h1>
        <div class="grid-x">
            <img src="" alt="Frutta">
            <img src="" alt="Verduta">
            <img src="" alt="Sottaceti">
        </div>
    </section>
    <%@include file="../partials/site/footer.jsp"%>
</main>
</body>
</html>
