<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Dashboard</title>
    <link rel="stylesheet" href="/css/style.css" />

    <%--<link rel="shortcut icon" href="">--%>
    <link rel="shortcut icon" href="favicon.ico">
</head>
<body>

<header>
    <%@include file="../header.jsp"%>
</header>

<section class="section--dashboard">
    <h2>Panel Administracyjny</h2>
    <a href="/użytkownicy" class="a--dashboard">
        <img  class="img--dashboard--user" src="/images/myImages/user.jpeg" title="Użytkownicy" >Użytkownicy
    </a>

    <a href="/administratorzy" class="a--dashboard">
        <img  src="/images/myImages/admin.jpeg" title="Administratorzy">Administratorzy
    </a>

    <a href="/instytucje" class="a--dashboard">
        <img class="img--dashboard" src="/images/myImages/Instytucje.jpg" title="Instytucje" >Instytucje
    </a>

    <a href="/dary" class="a--dashboard">
        <img class="img--dashboard" src="/images/myImages/bounty.jpeg" title="Dary" alt="">Dary
    </a>

</section>


</body>
</html>
