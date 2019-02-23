<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Users</title>
    <link rel="stylesheet" href="/css/style.css" />

    <%--<link rel="shortcut icon" href="">--%>
    <link rel="shortcut icon" href="favicon.ico">
</head>
<body>

    <%@include file="adminHeader.jsp"%>

    <h2>Użytkownicy:</h2>

    <%--${loop.count}` it will give 1 starting base index.--%>
    <%--varStatus="loop"--%>
    <ul>
        <c:forEach items="${users}" var="u" varStatus="loop">

            <c:if test="${u.isAdmin == false}">
                <li>${loop.count} ${u.email}

                <a href="${pageContext.request.contextPath}/admin/user/edit/${u.id}">edytuj</a>
                <a href="${pageContext.request.contextPath}/admin/user/confirm/${u.id}">usuń</a>

                <c:if test="${editingUser.id == u.id}">
                    <form:form method="post"
                               action="${formAction}"
                               modelAttribute="editingUser">
                        <form:hidden path="id" />
                        <form:hidden path="password" />

                        <form:input path="email" value="${editingUser.email}" />

                        <form:checkbox path="isAdmin" value="${editingUser.isAdmin}" />

                        <input type="submit"  value="Zapisz zmiany" class="btn btn-success">

                        <c:if test="${not empty emailErr}">
                            <div class="alert alert-danger">${emailErr}</div>
                        </c:if>

                        <div><form:errors path="email" /></div>
                    </form:form>
                </c:if>
                <c:if test="${not empty confirm and deletingUser.id == u.id}">
                    Potwierdź usunięcie tego użytkownika!
                    <a href="${pageContext.request.contextPath}/admin/user/delete/${u.id}">Tak</a>
                    <a href="${pageContext.request.contextPath}/admin/user/users">Nie</a>
                </c:if>

                </li>
            </c:if>
        </c:forEach>
    </ul>
</body>
</html>
