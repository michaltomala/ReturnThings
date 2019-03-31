<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="../css/style.css" />
</head>
<body>

<%@include file="../user/homeHeader.jsp"%>


<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="4" class="active">
                Na podstawie Twoich kryteriów oraz rzeczy, które masz do oddania
                wybraliśmy organizacje, którym możesz pomóc. Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>4</span>/5</div>

        <form>
            <div data-step="4" class="active">
                <h3>Wybierz organizacje, której chcesz pomóc klikając w nią:</h3>

                <c:forEach items="${institutions}" var="institution">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <span class="description">
                                <a href="${pageContext.request.contextPath}/form/step4/${institution.name}">
                                    <div class="title">Fundacja ${institution.name}</div>
                                </a>
                                    <div class="subtitle">Cel i misja: ${institution.goal}</div>
                            </span>
                        </label>
                    </div>
                </c:forEach>

                <div class="form-group form-group--buttons">
                    <a href="${pageContext.request.contextPath}/form/step3" class="btn">Wstecz</a>
                </div>
            </div>


        </form>
    </div>
</section>

<%@include file="../fragments/landingPageFooter.jsp"%>

</body>
</html>
