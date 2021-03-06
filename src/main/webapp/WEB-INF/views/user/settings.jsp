<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Settings</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>

    <%@include file="../fragments/fundamentalHeader.jsp"%>

    <section class="login-page">
        <h2>Ustawienia konta</h2>
        <form:form method="post"
                   action="${formAction}"
                   modelAttribute="user"
                   cssClass="form--contact">
            <form:hidden path="id" />

            <div class="form-group">
                <form:input path="email" placeholder="${user.email}"/>
                <c:if test="${not empty emailErr}">
                    <div class="alert alert-danger" role="alert">${emailErr}</div>
                </c:if>
            </div>

            <div class="form-group">
                <form:password path="password" placeholder="nowe Hasło" />
                <c:if test="${empty emailErr}">
                    <form:errors path="password" cssClass="alert alert-danger" element="div"/>
                </c:if>
            </div>

            <div class="form-group">
                <form:password path="repeatedPassword" placeholder="Powtórz nowe Hasło " />
                <c:if test="${not empty pwdErr}">
                    <div class="alert alert-danger" role="alert">${pwdErr}</div>
                </c:if>
            </div>

            <div class="form-group form-group--buttons">
                <a class="btn btn--without-border"></a>
                <button class="btn" type="submit">Zapisz zmiany</button>
            </div>
        </form:form>

    </section>
</body>
</html>


