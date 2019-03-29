<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Single Bounty</title>
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>


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
        </c:if> dla instytucji ${bountyDetail.institution.name} , która pomaga

        <c:forEach var="gift" items="${bountyDetail.institution.whomHelp}" varStatus="loop">
            <span>-${gift.type} </span><c:if test="${loop.count>1}"> &&</c:if>
        </c:forEach>
        <br/><h4>Oddałeś:</h4>
        <c:forEach var="gift" items="${bountyDetail.bounty.bountyType}">
            <br/><span>-${gift.type} </span>
        </c:forEach>
    </li>
</div>

<c:if test="${bountyDetail.archived == false}">
    <div class="form-section--column">
        <h4>Status:
            <c:if test="${bountyDetail.received == false}">
                nieodebrane -><a href="${pageContext.request.contextPath}/user/bounty/receive/${bountyDetail.id}">zmień na odebrane</a>
            </c:if>
            <c:if test="${bountyDetail.received == true }">
                odebrane -><a href="${pageContext.request.contextPath}/user/bounty/receive/${bountyDetail.id}">zmień na nieodebrane</a>
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

<br/><div class="form-section--column">
    <c:if test="${bountyDetail.received == true and bountyDetail.archived == false}">
        <a href="${pageContext.request.contextPath}/user/bounty/archive/${bountyDetail.id}">Archiwizuj</a>
    </c:if>
    <br/><a href="${pageContext.request.contextPath}/user/collection">Wróć do listy</a>
</div>

</body>
</html>
