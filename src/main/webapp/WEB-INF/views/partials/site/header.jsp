<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<header class="topbar grid-x align-center">
    <div class="grid-y leftside">
        <img src="../images/logo.png" width="150" height="120" alt="">
        <div class="account">
            <button class="btn justify-center">Categorie</button>
            <div class="account-content">
                <c:forEach items="${categories}" var="category">
                    <form method="get" action="${pageContext.request.contextPath}/products/searchByCat">
                        <input type="hidden" name="categoryId" id="categoryId" value="${category.categoryId}">
                        <button type="submit">${category.categoryName}</button>
                    </form>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="search-container">
        <form method="post" action="/products/search" class="searchbar-form">
            <input type="search" name="name" placeholder="Cerca..." class="search-field">
            <button type="submit" class="search-button">
                <img src="../images/search.png" height="30" width="30">
            </button>
        </form>
    </div>
    <div class="account">
    <span>
        <%@include file="../../../../icons/user.svg" %>
        <c:choose>
            <c:when test="${not empty accountSession}">
        <span id="account">
                ${accountSession.firstName}</span>
        </span>
        <div class="grid-y account-content">
            <a href="../accounts/profile">Profilo</a>
            <a href="../accounts/logout">Logout</a>
        </div>
        </c:when>
        <c:otherwise>
            <a id="login" href="../accounts/signin">Accedi</a>
            </span>
        </c:otherwise>
        </c:choose>
    </div>
    <c:if test="${not empty accountSession}">
        <span><button class="btn">
    <a href="/progetto_war_exploded/site/wish">WishList</a>
</button> </span>
    </c:if>
    <span>
        <%@include file="../../../../icons/shopping-cart-empty-side-view.svg" %>
    </span>
    <div>
        <span>
            <button class="btn" id="sc"> Ricerca guidata </button>
        </span>

        <div class="formtag" id="myForm">
            <form action="/progetto_war_exploded/product/search" method="post" class="form-container">
                <h3>Cosa ti interessa?</h3>
                <label for="tag">
                    <select name="tag" id="tag">
                        <c:forEach items="${tags}" var="tag">
                            <option value="${tag.tagId}">${tag.word}</option>
                        </c:forEach>
                    </select>
                </label>
                <button type="submit" class="btn">Cerca</button>
            </form>
        </div>
    </div>
</header>