<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Institutions</title>
    <link rel="stylesheet" href="/css/style.css" />

    <%--<link rel="shortcut icon" href="">--%>
    <link rel="shortcut icon" href="favicon.ico">
</head>
<body>

    <%@include file="adminHeader.jsp"%>

    <h2>Organizacje:</h2>

    <a href="${pageContext.request.contextPath}/admin/institutions/create">Dodaj nową organizację</a>

    <ul>
        <c:forEach items="${institutions}" var="institution" varStatus="loop">

            <li>${loop.count} ${institution.name}

                <a href="${pageContext.request.contextPath}/admin/user/edit/${u.id}">edytuj</a>
                <a href="${pageContext.request.contextPath}/admin/user/confirm/${u.id}">usuń</a>

            </li>
        </c:forEach>
    </ul>



</body>
</html>
