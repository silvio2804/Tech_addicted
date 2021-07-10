<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>

<c:set var="context" value="${pageContext.request.contextPath}"/>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,viewport-fit=cover">
<title>${param.title}</title>
<meta name="description" content="Ecommerce High-tech">
<link rel="icon" type="image/png" href="${context}/images/logo.jpg">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-title" content="Tech Addicted">
<meta name="format-detection" content="telephone-no">
<meta name="apple-mobile-web-app-status-bar-style" content="default">
<link rel="apple-touch-startup-image" href="${context}/images/logo.jpg">
<meta name="theme-color" content="#d6faff">
<link href="${context}/css/reset.css" rel="stylesheet">
<link href="${context}/css/library.css" rel="stylesheet">
<c:if test="${not empty param.styles}">
    <c:forTokens items="${param.styles}" delims="," var="style">
    <link rel="stylesheet" href="${context}/css/${style}.css">
    </c:forTokens>
</c:if>
<c:if test="${not empty param.scripts}">
    <c:forTokens items="${param.scripts}" delims="," var="script">
    <script src="${context}/js/${script}.js" defer></script>
    </c:forTokens>
</c:if>