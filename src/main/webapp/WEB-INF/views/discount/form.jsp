<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="discount" class="Model.discount.Discount" scope="request"/>
<c:set var="isCreate" value="${discount.discountId == 0}"/>

<c:if test="${not empty alert}">
    <%@include file="../partials/alert.jsp"%>
</c:if>

<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="param" value="Tech addicted home"/>
        <jsp:param name="styles" value="crm,crmTable"/>
        <jsp:param name="scripts" value="crm,"/>
    </jsp:include>
    <style>
        .discount-form > * {
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
<main class="app">
    <%@include file="../partials/crm/sidebar.jsp" %>
    <section class="content grid-y">
        <%@include file="../partials/crm/header.jsp" %>
        <div class="body grid-x justify-center">


<form method="post"action="/progetto_war_exploded/discounts/${isCreate ? 'create': 'update'}">
    <c:if test="${not isCreate}">
        <input type="hidden" name="discountId" value="${discount.discountId}">
    </c:if>
    <fieldset class="grid-x cell discount-form">
        <legend >${isCreate ? 'Crea' : 'Aggiorna'} Sconto</legend>
        <label for="discountName" class="field cell w 40">
            <input id="discountName" name="discountName" placeholder="Nome sconto" type="text" value="${discount.discountName}">
        </label>

        <label for="percentage" class="field cell w 40">
            <input id="percentage" name="percentage" placeholder="Percentuale sconto" type="text" value="${discount.percentage}">
        </label>

        <button type="submit" class="cell w40 btn primary">${isCreate ? 'Crea' : 'Aggiorna'}</button>
    </fieldset>
</form>
        </div>
    </section>
</main>
<%@include file="../partials/crm/footer.jsp" %>
</body>
</html>