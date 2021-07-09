<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"  %>

<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="param" value="Gestione account"/>
        <jsp:param name="styles" value="crm,dashboard,crmTable"/>
        <jsp:param name="scripts" value="crm"/>
    </jsp:include>
</head>
<body>
<main class="app">
    <%@include file="../partials/crm/sidebar.jsp" %>
    <section class="content grid-y">
        <%@include file="../partials/crm/header.jsp" %>
        <div class="body grid-x justify-center">
            <section class="grid-y cell ">
                <%@include file="../account/table.jsp"%>
                <jsp:include page="../partials/paginator.jsp">
                    <jsp:param name="resource" value="accounts"/>
                </jsp:include>
            </section>
        </div>
    </section>
</main>
<%@include file="../partials/crm/footer.jsp" %>
</body>
</html>