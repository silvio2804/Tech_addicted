<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="tag" class="Model.tag.Tag" scope="request"/>
<c:set var="isCreate" value="${tag.tagId == 0}"/>



<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="param" value="Tech addicted home"/>
        <jsp:param name="styles" value="crm,crmTable"/>
        <jsp:param name="scripts" value="crm,"/>
    </jsp:include>
    <style>
        .product-form > * {
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
<main class="app">
    <%@include file="../partials/crm/sidebar.jsp" %>
    <section class="content grid-y">
        <%@include file="../partials/crm/header.jsp" %>
        <div class="body grid-y justify-center">
            <c:if test="${not empty alert}">
                <%@include file="../partials/alert.jsp"%>
            </c:if>

<form method="post"action="/progetto_war_exploded/tags/${isCreate ? 'create': 'update'}">
    <c:if test="${not isCreate}">
        <input type="hidden" name="tagId" value="${tag.tagId}">
    </c:if>
    <fieldset class="grid-x cell category-form">
        <legend>${isCreate ? 'Crea' : 'Aggiorna'} Tag</legend>
        <label for="word" class="field cell w 40">
            <input id="word" name="word" placeholder="Parola" type="text" value="${tag.word}">
        </label>
        <button type="submit" class="cell w40 btn primary">${isCreate ? 'Crea' : 'Aggiorna'}</button>
    </fieldset>
</form>
            <c:if test="${not isCreate}">
                <form method="post" action="progetto_war_exploded/tags/delete">
                    <input type="hidden" name="id" value="${tag.tagId}">
                    <button type="submit" class="cell w40 btn">Elimina</button>
                </form>
            </c:if>
        </div>
    </section>
</main>
<%@include file="../partials/crm/footer.jsp" %>
</body>
</html>