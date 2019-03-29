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

    <%@include file="adminHeader.jsp"%>


    <c:if test="${empty bountyDetail}">
        <h2>Oddane Rzeczy</h2>
        <ul>
            <c:forEach items="${bounties}" var="b" varStatus="loop">

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
                    <a href="${pageContext.request.contextPath}/admin/bounties/${b.id}">Szczegóły</a>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${not empty bountyDetail}">
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
        <a href="${pageContext.request.contextPath}/admin/bounties/archive/${b.id}">Archiwizuj</a>
        </div>
    </c:if>


</body>
</html>
