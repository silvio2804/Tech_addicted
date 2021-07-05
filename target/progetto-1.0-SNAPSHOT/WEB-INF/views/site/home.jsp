<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Benvenuti in Tech Addicted"/>
        <jsp:param name="styles" value="site"/>
        <jsp:param name="scripts" value="site, slide"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/site/header.jsp" %>
<main class=" grid-y">
    <section class="grid-y">

        <div class="slideshow-container align-center">

            <div class="mySlides fade align-center">
                <img class="bsi" src="../images/promo1.jpg">
            </div>

            <div class="mySlides fade align-center">
                <img class="bsi" src="../images/promo2.jpg">
            </div>

            <div class="mySlides fade align-center">
                <img class="bsi" src="../images/promo3.jpg">
            </div>

        </div>
        <br>

        <!-- The dots/circles -->
        <div style="text-align:center;">
            <span class="dot" onclick="currentSlide(1)"></span>
            <span class="dot" onclick="currentSlide(2)"></span>
            <span class="dot" onclick="currentSlide(3)"></span>
        </div>
    </section>

    <section id="best">
        <h1 class="bs">IN EVIDENZA</h1>
        <div class="grid-x">
            <div class="card">
                <img src="?.jpg" alt="?" style="width:100%">
                <h1>?</h1>
                <p class="price">€?</p>
                <p>
                    <button>Visualizza prodotto</button>
                </p>
            </div>

            <div class="card">
                <img src="?.jpg" alt="?" style="width:100%">
                <h1>?</h1>
                <p class="price">€?</p>
                <p>
                    <button>Visualizza prodotto</button>
                </p>
            </div>
        </div>
    </section>
</main>
<%@include file="../partials/site/footer.jsp" %>

</body>
</html>
