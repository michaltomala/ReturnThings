<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>About</title>
    <link rel="stylesheet" href="css/style.css" />
    <%--<link rel="shortcut icon" href="">--%>
    <link rel="shortcut icon" href="favicon.ico">
</head>

<%@include file="fragments/otherHeader.jsp"%>


<section class="about-us">
    <div class="about-us--text">
        <h2>Co robisz z rzeczami, których już nie potrzebujesz?</h2>
        <p class="just-justify">Celem ReturnThings jest maksymalne uproszczenie procesu przekazania rzeczy oraz
        stworzenie gotowej listy zweryfikowanych fundacji.<br/>Wierzymy, że każdy będzie miał poczucie
        zaufania do instytucji oraz zostanie zachęcony prostym mechanizmem do oddania swoich niechcianych
        rzeczy potrzebującym. Razem z współpracującymi fundacjami chcemy stworzyć przestrzeń, dzięki
        której użytkownik będzie mógł w prosty i szybki sposób oddać swoje niechciane rzeczy zaufanym
        instytucjom.
        </p>
    </div>
    <div class="about-us--image">
        <img src="/images/myImages/o-nas-image.jpg" alt="About us">
    </div>
</section>
</body>
</html>
