<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="accountShowed" class="Model.account.Account" scope="request"/>
<c:set var="isCreate" value="${accountShowed.id == 0}"/>


<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="param" value="Tech addicted home"/>
        <jsp:param name="styles" value="crm,crmTable"/>
        <jsp:param name="scripts" value="crm,"/>
    </jsp:include>
    <style>
        .acc-form > * {
            margin-bottom: 1rem;
        }

        .checkmark {
            position: relative;
            top: 0;
            left: 0;
            height: 20px;
            width: 20px;
            background-color: #eee;
            border-radius: 50%;
        }
    </style>
</head>
<body>

<c:if test="${not empty alert}">
    <%@include file="../partials/alert.jsp"%>
</c:if>

<main class="app">
    <%@include file="../partials/crm/sidebar.jsp" %>
    <section class="content grid-y">
        <%@include file="../partials/crm/header.jsp" %>
        <div class="body grid-x justify-center">

<form method="post"action="/progetto_war_exploded/accounts/${isCreate ? 'create': 'update'}">
    <c:if test="${not isCreate}">
        <input type="hidden" name="id" value="${accountShowed.id}">
    </c:if>
    <fieldset class="grid-y cell acc-form">
        <legend>${isCreate ? 'Crea' : 'Aggiorna'} Account</legend>
        <label for="name" class="field cell w 40">
            <input id="name" name="name" placeholder="Nome" type="text" value="${accountShowed.name}">
        </label>

        <label for="lastName" class="field cell w 40">
            <input id="lastName" name="lastName" placeholder="Cognome" type="text" value="${accountShowed.lastName}">
        </label>

        <label for="dataNa" class="field cell w 40" >
            <input id="dataNa" name="dataNa" placeholder="data di nascita" type="date" value="${accountShowed.password}">
        </label>

        <label for="email" class="field cell w 40">
            <input id="email" name="email" placeholder="Email" type="email" value="${accountShowed.email}">
        </label>

        <label for="street" class="field cell w 40">
            <input id="street" name="street" placeholder="Via" type="text" value="${accountShowed.street}">
        </label>

        <label for="city" class="field cell w 40">
            <input id="city" name="city" placeholder="Citta" type="text" value="${accountShowed.city}">
        </label>

        <label for="houseNumber" class="field cell w 40">
            <input id="houseNumber" name="houseNumber" placeholder="Numero civico" type="text" value="${accountShowed.city}">
        </label>

        <label for="password" class="field cell w 40" >
            <input id="password" name="password" placeholder="password" type="text" value="${accountShowed.password}">
        </label>

        <label for="maschio" class="field cell w 40">
            <h5>maschio</h5>  <input type="radio" id="maschio"  name="gender" value="m">
            <!--<h5>femmina</h5>  <input name="gender" type="radio" value="f"> -->
        </label>

        <label for="admin" class="field cell w 40">
            <h5>Admin</h5>   <input id="admin" name="admin" placeholder="Admin" type="checkbox" class="checkmark" value="true">
        </label>

        <button type="submit" class="cell w40 btn primary">${isCreate ? 'Crea' : 'Aggiorna'}</button>
    </fieldset>
</form>
        </div>
    </section>
</main>
</body>
</html>