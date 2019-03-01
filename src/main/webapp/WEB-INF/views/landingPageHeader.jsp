<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="header--main-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <c:if test="${empty user}">
                <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
                <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
            </c:if>
            <c:if test="${not empty user}">
                <li class="logged-user">
                    <c:if test="${empty user.userDetails.name }">Witaj ${user.email}</c:if>
                    <c:if test="${not empty user.userDetails.name}">Witaj ${user.userDetails.name}</c:if>
                    <ul class="dropdown">
                        <c:if test="${user.isAdmin == true}">
                            <li>
                                <a href="/admin/dashboard">Panel Administracyjny</a>
                            </li>
                        </c:if>
                        <li><a href="/user/profile">Profil</a></li>
                        <li><a href="/user/settings">Ustawienia</a></li>
                        <li><a href="#">Moje zbiórki</a></li>
                        <li><a href="/logout">Wyloguj</a></li>
                    </ul>
                </li>
            </c:if>
        </ul>

        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#" class="btn btn--without-border">O nas</a></li>
            <li><a href="#" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Zacznij pomagać!<br />
                Oddaj niechciane rzeczy w zaufane ręce
            </h1>

            <ul class="slogan--buttons">
                <li><a href="#" class="btn btn--large">Oddaj rzeczy</a></li>
                <li><a href="#" class="btn btn--large">Zorganizuj zbiórkę</a></li>
            </ul>
        </div>
    </div>
</header>
