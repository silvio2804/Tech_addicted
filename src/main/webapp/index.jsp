<%--
  Created by IntelliJ IDEA.
  User: silvi
  Date: 18/04/2021
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--<img src="logo" alt="" --%>

<input type="text" size="50" name="ricerca" id="ricerca" value="Cerca prodotti">
<form>
    <input type="submit" name="chi siamo" id="chi siamo" value="chi siamo" formaction="chiSiamo.html" formmethod="post">
    <input type="submit" name="carrello" id="carrello" value="carrello" formaction="/carrelloServlet" formmethod="post">
    <input type="submit" name="accesso" id="accesso"  value="accesso o registrati" formaction="/loginServlet" formmethod="post">
</form>

<%--<img src="immagine centrale"> --%>

<%-- bestSeller --%>

</body>
</html>
