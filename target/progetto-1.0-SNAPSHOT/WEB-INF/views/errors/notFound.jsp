<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        div*{
            background-image: url("../../../images/error404.jpg");
        }
    </style>
</head>
<body>
<div>
    <p>Pagina non trovata, assicurati di aver digitato l'indirizzo correttamente</p>
    <a href="${pageContext.request.contextPath}/pages/">Torna alla home page</a>
</div>
</body>
</html>
