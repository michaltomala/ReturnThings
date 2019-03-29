<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>collection</title>
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>

    <%@include file="../auth/fundamentalHeader.jsp"%>
    <h2>Twoje zbiórki</h2>

    <div class="form-section--column">
        <h4>Odebrane rzeczy:</h4>
        <ul>
            <c:forEach items="${receivedBounties}" var="b" varStatus="loop">
                <li>${loop.count} -->
                    <c:if test="${b.bounty.quantityOfBags == 1}">
                        1 worek:
                    </c:if>
                    <c:if test="${b.bounty.quantityOfBags <5 && b.bounty.quantityOfBags >1}">
                        ${b.bounty.quantityOfBags} worki:
                    </c:if>
                    <c:if test="${b.bounty.quantityOfBags >4}">
                        ${b.bounty.quantityOfBags} worków:
                    </c:if> dla instytucji ${b.institution.name}
                    ||| Termin odbioru: ${b.reception.date} o godzinie ${b.reception.time} ->
                    <a href="${pageContext.request.contextPath}/user/bounty/${b.id}">Szczegóły</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="form-section--column">
        <h4>Nieodebrane rzeczy:</h4>
        <ul>
            <c:forEach items="${notReceivedBounties}" var="b" varStatus="loop">
                <li>${loop.count} -->
                    <c:if test="${b.bounty.quantityOfBags == 1}">
                        1 worek:
                    </c:if>
                    <c:if test="${b.bounty.quantityOfBags <5 && b.bounty.quantityOfBags >1}">
                        ${b.bounty.quantityOfBags} worki:
                    </c:if>
                    <c:if test="${b.bounty.quantityOfBags >4}">
                        ${b.bounty.quantityOfBags} worków:
                    </c:if> dla instytucji ${b.institution.name}
                    ||| Termin odbioru: ${b.reception.date} o godzinie ${b.reception.time} ->
                    <a href="${pageContext.request.contextPath}/user/bounty/${b.id}">Szczegóły</a>
                </li>
            </c:forEach>
        </ul>
    </div>



</body>
</html>
