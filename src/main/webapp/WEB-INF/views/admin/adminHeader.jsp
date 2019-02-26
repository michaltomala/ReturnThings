<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>
    <nav class="nav--dashboard">
        <ul class="nav--actions">
            <li><a href="/" class="btn btn--small btn--without-border">Strona Główna</a></li>
                <li class="btn btn--small btn--without-border" >Witaj ${user.email}</li>
                <li>
                    <a href="/admin/dashboard" class="btn btn--small btn--without-border">Panel Administracyjny</a>
                </li>
                <li><a href="/login" class="btn btn--small btn--without-border">Profil użytkownika</a></li>
                <li><a href="/logout" class="btn btn--small btn--highlighted">Wyloguj się</a></li>
        </ul>
    </nav>
</header>