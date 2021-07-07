<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="topbar grid-x align-center">
    <div class="grid-y leftside">
        <img src="../images/logo.png" width="150" height="120" alt="">
        <div class="account">
            <button class="btn justify-center">Categorie</button>
            <div class="account-content grid-x">
                <c:forEach items="${categories}" var="category">
                    ${category.name}
                </c:forEach>
            </div>
        </div>
    </div>
    <label class="field command">
        <input type="text" placeholder="Cerca..." class="search">
    </label>
    <div class="account">
    <span>
        <%@include file="../../../../icons/user.svg" %>
        <c:choose>
            <c:when test="${not empty accountSession}">
                ${accountSession.firstName.concat(" ").concat(accountSession.lastName)}
                </span>
        <div class="grid-y account-content">
            <a href="../accounts/profile">Profilo</a>
            <a href="../accounts/logout">Logout</a>
        </div>
        </c:when>
        <c:otherwise>
<a href="../accounts/secret">Accedi</a>
        </c:otherwise>
        </c:choose>


    </div>
    <span>
        <%@include file="../../../../icons/shopping-cart-empty-side-view.svg" %>
    </span>
</header>