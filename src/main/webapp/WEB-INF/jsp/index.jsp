<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>
    <title>Две задачки</title>

    <style><%@include file="/WEB-INF/css/style.css" %></style>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script><%@include file="/WEB-INF/js/calcExpandedFormClick.js" %></script>
</head>

<body>

<h1>Расширенная форма числа</h1>

<div class="centered-div">
    <div class="list-container">
        <div class="list">
            <div class="list-item"><label for="number">Введите натуральное число:</label></div>
            <div class="list-item"><input type="text" id="number"/></div>
            <div class="list-item">
                <input type="button"
                       id="calcExpandedFormBtn"
                       value="Найти его расширенную форму"/>
            </div>
            <div class="list-item"><label id="expanded-form"></label></div>
        </div>
    </div>
</div>

</body>

</html>
