<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>

    <%@include file="../fragments/authHeader.jsp"%>

    <section class="login-page">
        <h2>Zaloguj się</h2>
        <form:form method="post"
                   action="${formAction}"
                   modelAttribute="user"
                   cssClass="form--contact">
            <form:hidden path="id" />

            <c:if test="${not empty authErr}">
                <a class="btn btn--small btn--without-border reset-password">${authErr}</a><br/>
            </c:if>

            <div class="form-group">
                <c:if test="${not empty user.email}">
                    <form:input path="email" placeholder="${user.email}"/>
                </c:if>
                <c:if test="${empty user.email}">
                    <form:input path="email" placeholder="Email"/>
                </c:if>
                <form:errors path="email" cssClass="alert alert-danger" element="div"/>
            </div>

            <div class="form-group">
                <form:password path="password" placeholder="Hasło" />
                <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
                <form:errors path="password" cssClass="alert alert-danger" element="div"/>
            </div>


            <div class="form-group form-group--buttons">
                <a href="/register" class="btn btn--without-border">Załóż konto</a>
                <button class="btn" type="submit">Zaloguj się</button>
            </div>
        </form:form>

    </section>

</body>
</html>