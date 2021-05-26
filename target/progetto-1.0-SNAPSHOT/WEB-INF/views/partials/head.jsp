<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial scale=1,viewport-fit=cover">
<title>${param.title}</title>
<meta name="description" content="Ecommerce High-tech">
<link rel="icon" type="image/png" href="image/logo.png">
<meta name="apple-mobile-web-app-title" content="Tech Addicted">
<meta name="format-detection" content="telephone-no">
<meta name="apple-mobile-wweb-app-status-bar-style" content="default">
<link rel="apple-touch-icon" href="image/logo.png">
<meta name="theme-color" content="#000000">
<link href="css/reset.css" rel="stylesheet">
<link href="css/library.css" rel="stylesheet">
<c:if test="${not empty param.style}">
    <link rel="stylesheet" href="css/${param.style}">
</c:if>
<script src="js/library.js" defer></script>
<c:if test="${not empty param.script}">
    <script src="js/${param.script}" defer></script>
</c:if>