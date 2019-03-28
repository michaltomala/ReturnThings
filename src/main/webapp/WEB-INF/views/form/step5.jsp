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
            <p data-step="5" class="active">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>


<div class="form--steps-container">
    <div class="form--steps-counter">Krok <span>5</span>/5</div>
        <form:form action="${formAction}"
                   method="post"
                   modelAttribute="reception">

            <div data-step="5" class="active">
                    <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru</h4>

                            <div class="form-group form-group--inline">
                                <label>
                                    Ulica <form:input type="text" name="address" path="street"/>
                                    <form:errors path="street"/>
                                </label>
                            </div>

                            <div class="form-group form-group--inline">
                                <label>
                                    Miasto <form:input type="text" name="city" path="city"/>
                                    <form:errors path="city"/>
                                </label>
                            </div>

                            <div class="form-group form-group--inline">
                                <label>
                                    Kod pocztowy <form:input type="text" name="postcode" path="postCode"/>
                                    <form:errors path="postCode"/>
                                </label>
                            </div>

                            <div class="form-group form-group--inline">
                                <label>
                                    Numer telefonu <form:input type="phone" name="phone" path="phone"/>
                                    <form:errors path="phone"/>
                                </label>
                            </div>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru</h4>

                            <div class="form-group form-group--inline">
                                <label>
                                    Data <form:input type="date" name="data" path="date"/>
                                    <form:errors path="date"/>
                                </label>
                            </div>

                            <div class="form-group form-group--inline">
                                <label>
                                    Godzina <form:input type="time" name="time" path="time"/>
                                    <form:errors path="time"/>
                                </label>
                            </div>

                            <div class="form-group form-group--inline">
                                <label>
                                    Uwagi dla kuriera <form:textarea alt="Brak uwag" value="Brak uwag"
                                    placeholder="Brak uwag"  name="more_info" rows="5" path="comments"/>
                                </label>
                            </div>
                        </div>

                    </div>

                    <div class="form-group form-group--buttons">
                        <a href="${pageContext.request.contextPath}/form/step4"
                           class="btn prev-step">Wstecz</a>
                        <button type="submit" class="btn next-step">Dalej</button>
                    </div>
                </div>
        </form:form>
    </div>
</section>

<%@include file="../landingPageFooter.jsp"%>


</body>
</html>
