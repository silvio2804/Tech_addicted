<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        div #container{
            position:absolute;
            top:50%;
            left:50%;
            width:550px;
            height:400px;
            margin-left:-275px;
            margin-top: -200px;
            color:#000;
            padding:10px;

        }
        p{
            font-family: "Roboto Light";
            font-weight:bold;
            text-align: center;
        }


        body {
            background-image: url("/progetto_war_exploded/images/error404.jpg");
            background-position: center;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>


<div id="container">
    <p>Pagina non trovata, assicurati di aver digitato l'indirizzo correttamente</p>
    <p><a href="${pageContext.request.contextPath}/pages/">Torna alla home page</a></p>
</div>
</body>
</html>
