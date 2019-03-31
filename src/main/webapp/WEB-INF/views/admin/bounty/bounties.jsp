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


    <c:if test="${not empty bounties}">
        <h2>Oddane Rzeczy</h2>
        <li><a href="${pageContext.request.contextPath}/admin/bounties/archived/">->Zarchiwizowane</a></li>
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

    <c:if test="${empty bounties}">
        <h2>Aktualnie nie ma żadnej historii</h2>
        <li><a href="${pageContext.request.contextPath}/admin/bounties/archived/">->Zarchiwizowane</a></li>
    </c:if>


</body>
</html>
