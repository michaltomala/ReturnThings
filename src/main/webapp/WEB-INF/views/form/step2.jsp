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
        <p data-step="2" class="active">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
        </p>
    </div>
</div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>2</span>/5</div>

        <form:form  action="${formAction}"
                    method="post"
                    modelAttribute="bounty"
        >
            <div data-step="2" class="active">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">
                    <label>
                        Liczba 60l worków:
                        <form:input path="quantityOfBags" type="number" step="1" min="1" />
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <a href="${pageContext.request.contextPath}/home" class="btn">Wstecz</a>
                    <button type="submit" class="btn">Dalej</button>
                </div>
            </div>
        </form:form>
    </div>
</section>

    <footer>
        <div class="contact">
            <h2>Skontaktuj się z nami</h2>
            <h3>Formularz kontaktowy</h3>
            <form class="form--contact">
                <div class="form-group form-group--50">
                    <input type="text" name="name" placeholder="Imię" />
                </div>
                <div class="form-group form-group--50">
                    <input type="text" name="surname" placeholder="Nazwisko" />
                </div>

                <div class="form-group">
            <textarea
                    name="message"
                    placeholder="Wiadomość"
                    rows="1"
            ></textarea>
                </div>

                <button class="btn" type="submit">Wyślij</button>
            </form>
        </div>
        <div class="bottom-line">
            <span class="bottom-line--copy">Copyright &copy; 2018</span>
            <div class="bottom-line--icons">
                <a href="#" class="btn btn--small"
                ><img src="../images/icon-facebook.svg"
                /></a>
                <a href="#" class="btn btn--small"
                ><img src="../images/icon-instagram.svg"
                /></a>
            </div>
        </div>
    </footer>

</body>
</html>