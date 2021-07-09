<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



        <header class="topbar grid-x align-center">
    <div class="grid-y leftside">
        <img src="../images/logo.png" width="150" height="120" alt="">
        <div class="account">
            <button class="btn justify-center">Categorie</button>
            <div class="account-content grid-x">
                <c:forEach items="${categories}" var="category">
                    <form method="get" action="${pageContext.request.contextPath}/products/searchByCat">
                        <input type="hidden" name="categoryId" id="categoryId" value="${category.categoryId}">
                        <button type="submit">${category.categoryName}</button>
                    </form>
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
        <span id="account">
            ${accountSession.firstName.concat(" ").concat(accountSession.lastName)}</span>
        </span>
        <div class="grid-y account-content">
            <a href="../accounts/profile">Profilo</a>
            <a href="../accounts/logout">Logout</a>
        </div>
        </c:when>
        <c:otherwise>
           <script src="js/header.js" type="text/javascript"></script>
            <a id="login" href="../accounts/secret">Accedi</a>
        </c:otherwise>
        </c:choose>


    </div>
    <span>
        <%@include file="../../../../icons/shopping-cart-empty-side-view.svg" %>
    </span>
</header>