<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Admins</title>
    <link rel="stylesheet" href="/css/style.css" />

    <%--<link rel="shortcut icon" href="">--%>
    <link rel="shortcut icon" href="favicon.ico">
</head>
<body>

    <%@include file="adminHeader.jsp"%>

    <h2>Administratorzy:</h2>
    <ul>
        <c:forEach items="${admins}" var="admin" varStatus="loop">

            <li>${loop.count} ${admin.email}

                <a href="${pageContext.request.contextPath}/admin/edit/${admin.id}">edytuj</a>
                <a href="${pageContext.request.contextPath}/admin/confirm/${admin.id}">usuń</a>

                <c:if test="${editingAdmin.id == admin.id}">
                    <form:form method="post"
                               action="${formAction}"
                               modelAttribute="editingAdmin">
                        <form:hidden path="id" />
                        <form:hidden path="password" />

                        <form:input path="email" value="${editingAdmin.email}" />

                        <form:checkbox path="isAdmin" value="${editingAdmin.isAdmin}" />

                        <input type="submit"  value="Zapisz zmiany" class="btn btn-success">

                        <c:if test="${not empty emailErr}">
                            <div class="alert alert-danger">${emailErr}</div>
                        </c:if>

                        <div><form:errors path="email" /></div>
                    </form:form>
                </c:if>
                <c:if test="${not empty confirm and deletingUser.id == admin.id}">
                    Potwierdź usunięcie tego użytkownika!
                    <a href="${pageContext.request.contextPath}/admin/delete/${admin.id}">Tak</a>
                    <a href="${pageContext.request.contextPath}/admin/admins">Nie</a>
                </c:if>

            </li>
        </c:forEach>
    </ul>
</body>
</html>
