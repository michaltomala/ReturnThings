<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header >
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

</header>
