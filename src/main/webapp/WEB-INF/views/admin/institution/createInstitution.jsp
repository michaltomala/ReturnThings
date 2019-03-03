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

    <%@include file="../adminHeader.jsp"%>

    <section class="login-page">
        <h2>Dodaj nową organizację</h2>

        <form:form method="post"
                   action="${formAction}"
                   modelAttribute="institution"
                   cssClass="form--contact">

            <form:hidden path="id" />

            <div class="form-group">
                <form:input path="name" placeholder="Nazwa Organizacji"/>
                <form:errors path="name" />
                <c:if test="${not empty nameErr}">${nameErr}</c:if>
            </div>

            <div class="form-group">
                <form:input path="goal" placeholder="Cel i misja organizacji"/>
                <form:errors path="goal" />
            </div>

            <div class="form-group">
                <form:select path="institutionLocations"  >
                        <form:option value="0" disabled="true" selected="true">Wybierz markę</form:option>
                        <form:options  items="${locations}"
                                       itemValue="id"
                                       itemLabel="location" />
                    </form:select>
                    <form:errors path="institutionLocations" />
            </div>

            <div class="form-group">
                <form:select path="whomHelp">
                    <form:option value="0" disabled="true" >Komu pomaga</form:option>
                    <form:options  items="${whomHelp}"/>
                </form:select>
                <form:errors path="whomHelp" />
            </div>

            <div class="form-group form-group--buttons">
                <a class="btn btn--without-border"
                   href="${pageContext.request.contextPath}/admin/institutions/locations">Lokalizacje</a>
                <button class="btn" type="submit">Zapisz organizację</button>
            </div>

        </form:form>
    </section>


</body>
</html>
