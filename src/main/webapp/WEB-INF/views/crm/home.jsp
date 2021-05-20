<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<head>hello world</head>
<html>
<%-- head dinamico --%>
<jsp:include page="../partials/head.jsp">
    <jsp:param name="param" value="Tech addicted home"/>
    <jsp:param name="style" value="crhome.css"/>
    <jsp:param name="script" value="crhome.js"/>
</jsp:include>
<body>
</body>
</html>
