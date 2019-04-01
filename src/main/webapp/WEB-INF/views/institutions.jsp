<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Institutions</title>
    <link rel="stylesheet" href="css/style.css" />
    <%--<link rel="shortcut icon" href="">--%>
    <link rel="shortcut icon" href="favicon.ico">
</head>

<%@include file="fragments/otherHeader.jsp"%>

<section class="form--steps">
    <div class="form--steps-container">
        <form>
            <div data-step="1" class="active">
            <h2>Fundacje z którymi współpracujemy</h2>
            <c:forEach items="${institutions}" var="institution">
                <div class="form-group form-group--checkbox">
                    <label>
                        <span class="description">
                                <div class="title">Fundacja ${institution.name}</div>
                                <div class="subtitle">Cel i misja: ${institution.goal}</div>
                                <div class="subtitle">Miejsce:
                                    <c:forEach items="${institution.institutionLocations}" var="location" varStatus="loop">
                                        <c:if test="${loop.count >1}">&  </c:if>${location}
                                    </c:forEach>
                                </div>

                        </span>
                    </label>
                </div>
            </c:forEach>
            </div>
        </form>
    </div>
</section>

</body>
</html>
