<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="it" dir="ltr">
<head>
    <jsp:include page="WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="homePage"/>
    </jsp:include>
    <style>
        html,body{
            font-family: "Quattrocento";
            font-weight: normal;
            font-style: normal;
        }
    </style>
</head>
<body>
<% response.sendRedirect("./accounts/secret"); %>
</body>
</html>
