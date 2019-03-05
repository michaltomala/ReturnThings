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

    <%@include file="../adminHeader.jsp"%>

    <h2>Lokalizacje</h2>


    <a href="/admin/institutions/addLocation">Dodaj nową lokalizację</a>
    <c:if test="${not empty addLocation}">
        <form:form method="post"
                   action="/admin/institutions/addLocation"
                   modelAttribute="addLocation"
                   cssClass="form--contact">
            <form:hidden path="id" />
            <div class="form-group">
                <form:input path="location" placeholder="Nazwa miejscowości"/>
            </div>
            <c:if test="${not empty locationErr}">
                    <div class="form-group">${locationErr}</div>
            </c:if>

            <button class="btn" type="submit">Zapisz miejscowość</button>
        </form:form>
    </c:if>


    <p>Aby edytować wciśnij nazwę lokalizacji</p>
    <p>Usunięcie jest możliwe dopiero w przypadku gdy nie ma żadnej organizacji przypisanej do danej lokalizacji!</p>
    <ul>
        <c:forEach items="${locations}" var="location" varStatus="loop">
            <li>
                <c:if test="${editLocation.id != location.id}">
                    <a href="${pageContext.request.contextPath}/admin/institutions/editLocation/${location.id}">
                        ${loop.count} ${location.location}
                    </a>

                    <c:forEach items="${locationsEnableToDelete}" var="locationEnableToDelete">
                            <c:if test="${locationEnableToDelete.id == location.id}">
                                <a href="${pageContext.request.contextPath}/admin/institutions/deleteLocation/${location.id}">
                                    Usuń
                                </a>
                            </c:if>
                    </c:forEach>
                </c:if>

                <c:if test="${editLocation.id == location.id}">
                    <form:form method="post"
                               action="/admin/institutions/editLocation/${location.id}"
                               modelAttribute="editLocation"
                               cssClass="form--contact">
                        <form:hidden path="id" />
                        <div class="form-group">
                            <form:input path="location" placeholder="${editLocation.location}"/>
                        </div>
                        <c:if test="${not empty locationErr}">
                            <div class="form-group">${locationErr}</div>
                        </c:if>

                        <button class="btn" type="submit">Zapisz zmiany</button>
                    </form:form>
                </c:if>
            </li>

        </c:forEach>
    </ul>
    <c:if test="${not empty deleteLocationErr}">
        <div class="form-group">${deleteLocationErr}</div>
    </c:if>


</body>
</html>
