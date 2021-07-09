<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="order" class="Model.order.Order" scope="request"/>
<c:set var="isCreate" value="${order.orderId == 0}"/>

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
        <div class="body grid-x justify-center">


<form method="post"action="/progetto_war_exploded/orders/${isCreate ? 'create': 'update'}">
    <c:if test="${not isCreate}">
        <input type="hidden" name="orderId" value="${order.orderId}">
    </c:if>
    <fieldset class="grid-x cell product-form">
        <legend>${isCreate ? 'Crea' : 'Aggiorna'} Ordine </legend>

        <label for="orderDate" class="field cell w 40">
            <input id="orderDate" name="orderDate" placeholder="Data ordine" type="text" value="${order.orderDate}">
        </label>

        <label for="payment" class="field cell w 40">
            <input id="payment" name="payment" placeholder="Pagamento" type="text" value="${order.payment}">
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