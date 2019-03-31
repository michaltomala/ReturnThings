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
            <p data-step="3" class="active">
                Jeśli wiesz komu chcesz pomóc, możesz wpisać nazwę tej organizacji w
                wyszukiwarce. Możesz też filtrować organizacje po ich lokalizacji
                bądź celu ich pomocy.
            </p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>3</span>/5</div>

        <form:form action="${formAction}"
                   method="post"
                   modelAttribute="institution">

            <div data-step="3" class="active">
                <h3>Lokalizacja:</h3>

                <div class="form-group form-group--dropdown">
                        <form:select path="institutionLocations" name="localization">

                        <c:if test="${not empty sessionScope.institution.institutionLocations}">
                            <c:forEach items="${sessionScope.institution.institutionLocations}"
                                       var="institutionLocations">
                                <option value="${institutionLocations.id}" >
                                        ${institutionLocations.location}
                                </option>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty sessionScope.institution.institutionLocations}">
                            <option value="0">- wybierz -</option>
                        </c:if>
                        <c:forEach items="${locations}" var="location">
                            <option value="${location.id}" >${location.location}</option>
                        </c:forEach>
                        </form:select>
                </div>

                <div class="form-section">
                    <h4>Komu chcesz pomóc?</h4>
                    <form:checkboxes delimiter="<br/>" path="whomHelp" items="${whomHelp}" multiple="true"
                                     itemValue="id" itemLabel="whomHelp" />
                </div>

                <div class="form-section">
                    <h4>Wpisz nazwę konkretnej organizacji (opcjonalnie)</h4>
                    <div class="form-group">
                        <form:textarea rows="4" path="name"/>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <a href="${pageContext.request.contextPath}/form/step2" class="btn">Wstecz</a>
                    <button type="submit" class="btn">Dalej</button>
                </div>

                <c:if test="${not empty institutionErr}"><h4><br/>${institutionErr}</h4></c:if>
            </div>

        </form:form>
    </div>
</section>


<%@include file="../fragments/landingPageFooter.jsp"%>


<script src="/js/app.js" ></script>
</body>
</html>
