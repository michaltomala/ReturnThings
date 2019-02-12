<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>

            <%--<c:if test="${empty user}">--%>
                <%--<a class="nav-link color-header" href="/login">Zaloguj się</a>--%>
                <%--<a class="nav-link color-header" href="/register">Rejestracja</a>--%>
            <%--</c:if>--%>
            <%--<c:if test="${not empty user}">--%>
                <%--Witaj--%>
                <%--<a class="nav-link color-header" href="/user/${user.id}"> ${user.login}</a>--%>
                <%--<a class="nav-link color-header" href="/settings/${user.id}">Ustawienia</a>--%>
                <%--<a class="nav-link color-header" href="/logout">Wyloguj się</a>--%>
            <%--</c:if>--%>

        </ul>

        <ul>
            <li><a href="/" class="btn btn--without-border">Start</a></li>
            <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#" class="btn btn--without-border">O nas</a></li>
            <li><a href="#" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>





















