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


    <div class="form--steps-container">
        <form>

            <!-- STEP 6 -->
            <div data-step="6" class="active">
                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Oddajesz:</h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span class="summary--text">
                                    <c:if test="${sessionScope.bounty.quantityOfBags == 1}">
                                        1 worek:
                                    </c:if>
                                    <c:if test="${sessionScope.bounty.quantityOfBags <5 && sessionScope.bounty.quantityOfBags >1}">
                                        ${sessionScope.bounty.quantityOfBags} worki:
                                    </c:if>
                                    <c:if test="${sessionScope.bounty.quantityOfBags >4}">
                                        ${sessionScope.bounty.quantityOfBags} worków:
                                    </c:if>

                                    <c:forEach var="gift" items="${sessionScope.bounty.bountyType}">
                                        <br/><span>-${gift.type} </span>
                                    </c:forEach>
                                </span>
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text"
                                >Dla fundacji ${sessionScope.chosenInstitution.name}</span
                                >
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru:</h4>
                            <ul>
                                <li>${sessionScope.reception.street}</li>
                                <li>${sessionScope.reception.city}</li>
                                <li>${sessionScope.reception.postCode}</li>
                                <li>${sessionScope.reception.phone}</li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru:</h4>
                            <ul>
                                <li>${sessionScope.reception.date}</li>
                                <li>${sessionScope.reception.time}</li>
                                <li>${sessionScope.reception.comments}</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <a href="${pageContext.request.contextPath}/form/step5" class="btn">Wstecz</a>
                    <a href="${pageContext.request.contextPath}/form/saveForm" class="btn">Dalej</a>
                </div>
            </div>
        </form>
    </div>
</section>

<%@include file="../landingPageFooter.jsp"%>

</body>
</html>

