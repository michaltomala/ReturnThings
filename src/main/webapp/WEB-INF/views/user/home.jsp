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

    <%@include file="homeHeader.jsp"%>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/5</div>

        <form:form  action="${formAction}"
                    method="post"
                    modelAttribute="bounty">

            <div data-step=z"1" class="active">
                <h3>Zaznacz co chcesz oddać:</h3>

                <form:checkboxes delimiter="<br/>" path="bountyType" multiple="true" items="${bountyTypes}"
                                 itemLabel="type" itemValue="id" />

                <div class="form-group form-group--buttons">
                    <button type="submit" class="btn">Dalej</button>
                </div>
                <c:if test="${not empty bountyErr}" ><br/>${bountyErr}</c:if>
            </div>
        </form:form>

    </div>
</section>

    <%@include file="../fragments/landingPageFooter.jsp"%>

</body>
</html>
