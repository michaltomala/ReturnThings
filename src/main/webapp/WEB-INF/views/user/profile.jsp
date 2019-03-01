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

    <%@include file="../auth/fundamentalHeader.jsp"%>

    <section class="login-page">
        <h2>Twoje dane</h2>

        <form:form method="post"
                   action="${formAction}"
                   modelAttribute="userDetails"
                   cssClass="form--contact">
            <form:hidden path="id" />

            <div class="form-group">
                <form:input path="name" placeholder="${userDetails.name}"/>
            </div>

            <div class="form-group">
                <form:input path="surname" placeholder="${userDetails.surname}" />
            </div>

            <div class="form-group form-group--buttons">
                <a class="btn btn--without-border"></a>
                <button class="btn" type="submit">Zapisz zmiany</button>
            </div>
        </form:form>

    </section>







</body>
</html>
