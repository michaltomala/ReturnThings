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

<%@include file="../fragments/homeHeader.jsp"%>

<section class="form--steps">
 <div class="form--steps-instructions">
    <div class="form--steps-container">
        <h3>Ważne!</h3>
        <p data-step="2" class="active">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
        </p>
    </div>
</div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>2</span>/5</div>

        <form:form  action="${formAction}"
                    method="post"
                    modelAttribute="bounty">
            <div data-step="2" class="active">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">
                    <label>
                        Liczba 60l worków:
                        <form:input path="quantityOfBags" type="number" step="1" min="1" />
                        <c:if test="${not empty bountyErr}">${bountyErr}</c:if>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <a href="${pageContext.request.contextPath}/home" class="btn">Wstecz</a>
                    <button type="submit" class="btn">Dalej</button>
                </div>
            </div>
        </form:form>
    </div>
</section>

<%@include file="../fragments/landingPageFooter.jsp"%>

</body>
</html>
