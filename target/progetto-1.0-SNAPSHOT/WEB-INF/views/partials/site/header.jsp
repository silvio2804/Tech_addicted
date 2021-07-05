<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="topbar grid-x align-center">
    <div class="grid-y">
        <img src="../images/logo.png" width="150" height="120">
        <button class="btn">Categorie</button>
    </div>
    <label class="field command">
        <input type="text" placeholder="Cerca..." class="search">
    </label>
    <span class="account">
        <%@include file="../../../../icons/user.svg" %>
            ${accountSession.firstName.concat(" ").concat(accountSession.lastName)}
        <%@include file="../../../../icons/shopping-cart-empty-side-view.svg"%>
    </span>
</header>