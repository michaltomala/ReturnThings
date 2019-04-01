<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Profile</title>
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>

    <%@include file="../fragments/fundamentalHeader.jsp"%>

    <section class="login-page">
        <h2>Twoje dane</h2>

        <form:form method="post"
                   action="${formAction}"
                   modelAttribute="user"
                   cssClass="form--contact">
            <form:hidden path="id" />

            <div class="form-group">
                <c:if test="${empty user.name}">
                    <form:input path="name" placeholder="Imię" />
                </c:if>
                <c:if test="${not empty user.name }">
                    <form:input path="name" placeholder="${user.name}" />
                </c:if>
            </div>

            <div class="form-group">
                <c:if test="${empty user.surname }">
                    <form:input path="surname" placeholder="Nazwisko" />
                </c:if>
                <c:if test="${not empty user.surname }">
                    <form:input path="surname" placeholder="${user.surname}" />
                </c:if>
            </div>

            <div class="form-group">
                <c:if test="${empty user.city }">
                    <form:input path="city" placeholder="Miasto" />
                </c:if>
                <c:if test="${not empty user.city }">
                    <form:input path="city" placeholder="${user.city}" />
                </c:if>
            </div>

            <div class="form-group">
                <c:if test="${empty user.street}">
                    <form:input path="street" placeholder="Ulica" />
                </c:if>
                <c:if test="${not empty user.street}">
                    <form:input path="street" placeholder="${user.street}" />
                </c:if>
            </div>

            <div class="form-group">
                <c:if test="${empty user.postCode}">
                    <form:input path="postCode" placeholder="Adres Pocztowy" />
                </c:if>
                <c:if test="${not empty user.postCode}">
                    <form:input path="postCode" placeholder="${user.postCode}" />
                </c:if>
                <form:errors path="postCode" />
            </div>

            <div class="form-group">
                <c:if test="${empty user.phone}">
                    <form:input path="phone" placeholder="Telefon" />
                </c:if>
                <c:if test="${not empty user.phone}">
                    <form:input path="phone" placeholder="${user.phone}" />
                </c:if>
                <form:errors path="phone" />
            </div>

            <div class="form-group form-group--buttons">
                <a class="btn btn--without-border"></a>
                <button class="btn" type="submit">Zapisz zmiany</button>
            </div>
        </form:form>

    </section>







</body>
</html>
