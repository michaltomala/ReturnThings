<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Meaning</title>
    <link rel="stylesheet" href="css/style.css" />
    <%--<link rel="shortcut icon" href="">--%>
    <link rel="shortcut icon" href="favicon.ico">
</head>

<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
        <c:if test="${empty user}">
            <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </c:if>
        <c:if test="${not empty user}">
            <li class="logged-user">
                <c:if test="${empty user.name }">Witaj ${user.email}</c:if>
                <c:if test="${not empty user.name}">Witaj ${user.name}</c:if>
                <ul class="dropdown">
                    <c:if test="${user.isAdmin == true}">
                        <li>
                            <a href="/admin/dashboard">Panel Administracyjny</a>
                        </li>
                    </c:if>
                    <li><a href="/user/profile">Profil</a></li>
                    <li><a href="/user/settings">Ustawienia</a></li>
                    <li><a href="/user/collection">Moje zbiórki</a></li>
                    <li><a href="/logout">Wyloguj</a></li>
                </ul>
            </li>
        </c:if>
        </ul>

        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="/meaning" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="/about" class="btn btn--without-border">O nas</a></li>
            <li><a href="/institutions" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="/contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>O co chodzi ?<br/></h1><h1>
            <section class="form--steps">
                    <form>
                        <div data-step="1" class="active">
                            <div class="form-group form-group--checkbox">
                                <label>
                                <span class="description">
                                <div class="title">Dużo osób ma w domu rzeczy, których nie używa, ale są
                                    one w dobrym stanie i chce przekazaćje osobom,
                                    którym się mogą przydać - nie wie jednak jak w prosty sposób to
                                    zrobić.</div>
                                <div class="subtitle">Jest wiele dostępnych rozwiązań, ale wiele z nich
                                    wymaga dodatkowego wysiłku lub nie budząone zaufania.W zweryfikowane
                                    miejsca trzeba pojechać, a nie ma na to czasu lub nie ma jak tam
                                    pojechać. Natomiast kontenery pod domem lub lokalne zbiórki są
                                    niezweryfikowane i nie wiadomo czy te rzeczy faktycznie trafią
                                    do potrzebujących.</div>
                                </span>
                                </label>
                            </div>
                        </div>
                    </form>
            </section>
            </h1>
        </div>
    </div>
</header>



</body>
</html>
