<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="category" class="Model.category.Category" scope="request"/>
<c:set var="isCreate" value="${category.categoryId == 0}"/>

<c:if test="${not empty alert}">
    <%@include file="../partials/alert.jsp"%>
</c:if>

<form method="post"action="/TechAddicted/categories/${isCreate ? 'create': 'update'}">
    <c:if test="${not isCreate}">
        <input type="hidden" name="id" value="${category.categoryId}">
    </c:if>
    <fieldset class="grid-x cell category-form">
        <legend>${isCreate ? 'Crea' : 'Aggiorna'} Categoria</legend>
        <label for="label" class="field cell w 40">
            <input id="label" name="label" placeholder="Nome" type="text" value="${category.categoryName}">
        </label>
        <button type="submit" class="cell w40 btn primary">${isCreate ? 'Crea' : 'Aggiorna'}</button>
    </fieldset>
</form>
