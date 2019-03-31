<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Bounties</title>
    <link rel="stylesheet" href="/css/style.css" />

    <%--<link rel="shortcut icon" href="">--%>
    <link rel="shortcut icon" href="favicon.ico">
</head>
<body>

<%@include file="../../fragments/adminHeader.jsp"%>

    <h2>Szczegóły</h2>
    <div class="form-section--column">
        <li>
            <c:if test="${bountyDetail.bounty.quantityOfBags == 1}">
                1 worek
            </c:if>
            <c:if test="${bountyDetail.bounty.quantityOfBags <5 && bountyDetail.bounty.quantityOfBags >1}">
                ${bountyDetail.bounty.quantityOfBags} worki
            </c:if>
            <c:if test="${bountyDetail.bounty.quantityOfBags >4}">
                ${bountyDetail.bounty.quantityOfBags} worków
            </c:if> dla instytucji ${bountyDetail.institution.name}:
            <c:forEach var="gift" items="${bountyDetail.bounty.bountyType}">
                <br/><span>-${gift.type} </span>
            </c:forEach>
        </li>
    </div>

<c:if test="${bountyDetail.archived == false}">
    <div class="form-section--column">
        <h4>Status:
            <c:if test="${bountyDetail.received == false}">
                nieodebrane -><a href="${pageContext.request.contextPath}/admin/bounties/receive/${bountyDetail.id}">zmień na odebrane</a>
            </c:if>
            <c:if test="${bountyDetail.received == true }">
                odebrane -><a href="${pageContext.request.contextPath}/admin/bounties/receive/${bountyDetail.id}">zmień na nieodebrane</a>
            </c:if>
        </h4>
    </div>
</c:if>

    <div class="form-section--column">
        <h4>Adres odbioru:</h4>
        <ul>
            <li>Ulica: ${bountyDetail.reception.street}</li>
            <li>Miasto: ${bountyDetail.reception.city}</li>
            <li>Kod Pocztowy: ${bountyDetail.reception.postCode}</li>
            <li>Numer Telefonu: ${bountyDetail.reception.phone}</li>
        </ul>
    </div>

    <div class="form-section--column">
        <h4>Termin odbioru:</h4>
        <ul>
            <li>Data odbioru: ${bountyDetail.reception.date}</li>
            <li>Godzina: ${bountyDetail.reception.time}</li>
            <li>Dodatkowy komentarz dla kuriera: ${bountyDetail.reception.comments}</li>
        </ul>
    </div>
    <div class="form-section--column">
        <h4>Użytkownik wypełniający:</h4>
        <ul>
            <li>Imię: ${bountyDetail.user.name}</li>
            <li>Nazwisko: ${bountyDetail.user.surname}</li>
            <li>email: ${bountyDetail.user.email}</li>
        </ul>
    </div>
    <br/><div class="form-section--column">
    <c:if test="${bountyDetail.received == true and bountyDetail.archived == false}">
        <a href="${pageContext.request.contextPath}/admin/bounties/archive/${bountyDetail.id}">Archiwizuj</a>
    </c:if>
    <br/><a href="${pageContext.request.contextPath}/admin/bounties/">Wróć do listy</a>
    </div>

</body>
</html>
