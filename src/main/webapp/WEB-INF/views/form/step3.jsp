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
        <div class="form--steps-counter">Krok <span>1</span>/5</div>
        <form action="${formAction}">

            <div data-step="3" class="active">
                <h3>Lokalizacja:</h3>

                <div class="form-group form-group--dropdown">
                    <select name="localization">
                        <option value="0">- wybierz -</option>
                        <option value="warsaw">Warszawa</option>
                        <option value="wroclaw">Wrocław</option>
                        <option value="poznan">Poznań</option>
                        <option value="gdansk">Gdańsk</option>
                    </select>
                </div>

                <div class="form-section">
                    <h4>Komu chcesz pomóc?</h4>
                    <div class="form-section--checkboxes">
                        <div class="form-group form-group--checkbox">
                            <label>
                                <input type="checkbox" name="help[]" value="children" />
                                <span class="checkbox">dzieciom</span>
                            </label>
                        </div>

                        <div class="form-group form-group--checkbox">
                            <label>
                                <input type="checkbox" name="help[]" value="mothers" />
                                <span class="checkbox">samotnym matkom</span>
                            </label>
                        </div>

                        <div class="form-group form-group--checkbox">
                            <label>
                                <input type="checkbox" name="help[]" value="homeless" />
                                <span class="checkbox">bezdomnym</span>
                            </label>
                        </div>

                        <div class="form-group form-group--checkbox">
                            <label>
                                <input type="checkbox" name="help[]" value="disabled" />
                                <span class="checkbox">niepełnosprawnym</span>
                            </label>
                        </div>

                        <div class="form-group form-group--checkbox">
                            <label>
                                <input type="checkbox" name="help[]" value="old" />
                                <span class="checkbox">osobom starszym</span>
                            </label>
                        </div>
                    </div>
                </div>

                <div class="form-section">
                    <h4>Wpisz nazwę konkretnej organizacji (opcjonalnie)</h4>
                    <div class="form-group">
                        <textarea rows="4" name="organization_search"></textarea>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="submit" class="btn">Szukaj</button>
                </div>
            </div>

        </form>
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
