<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>
    <nav class="nav--dashboard">
        <ul class="nav--actions">
            <li><a href="/" class="btn btn--small btn--without-border">Strona Główna</a></li>
            <c:if test="${empty user.name }">
                <li class="btn btn--small btn--without-border" >Witaj ${user.email}</li>
            </c:if>
            <c:if test="${not empty user.name}">
                <li class="btn btn--small btn--without-border" >Witaj ${user.name}</li>
            </c:if>
            <li>
                <a href="/admin/dashboard" class="btn btn--small btn--without-border">Panel Administracyjny</a>
            </li>
            <li><a href="/user/profile" class="btn btn--small btn--without-border">Profil użytkownika</a></li>
            <li><a href="/user/settings" class="btn btn--small btn--without-border">Ustawienia</a></li>

            <li><a href="/logout" class="btn btn--small btn--highlighted">Wyloguj się</a></li>
        </ul>
    </nav>
</header>