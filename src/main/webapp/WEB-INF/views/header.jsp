<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <nav class="container container--70">
        <ul class="nav--actions">
            <c:if test="${empty user}">
                <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
                <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
            </c:if>

            <c:if test="${not empty user}">
                <li class="btn btn--small btn--without-border" >Witaj ${user.email}</li>
                <c:if test="${user.isAdmin == true}">
                    <li>
                        <a href="/admin/dashboard" class="btn btn--small btn--without-border">Panel Administracyjny</a>
                    </li>
                </c:if>
                <li><a href="/login" class="btn btn--small btn--without-border">Profil użytkownika</a></li>
                <li><a href="/logout" class="btn btn--small btn--highlighted">Wyloguj się</a></li>
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






















