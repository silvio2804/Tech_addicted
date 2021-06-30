<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>

<header class="topbar grid-x align-center">
    <%@include file="../../../../icons/menu.svg" %>
    <label class="field command">
        <input type="text" placeholder="Cerca Comandi">
    </label>
    <span class="account">
        <%@include file="../../../../icons/user.svg" %>
            ${accountSession.firstName.concat(" ").concat(accountSession.lastName)}
    </span>
</header>