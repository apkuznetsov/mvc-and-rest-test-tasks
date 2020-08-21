<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>
    <title>Две задачки</title>

    <style type="">
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
                       id="submitButton"
                       value="Найти его расширенную форму"/>
            </div>
            <div class="list-item"><label id="expanded-form"></label></div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $("#submitButton").click(function (event) {
            event.preventDefault();
            ajaxSubmitButtonClick();
        });
    });

    function ajaxSubmitButtonClick() {
        const number = {
            number: parseInt($("#number").val())
        }

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "api/get-expanded-form",
            data: JSON.stringify(number),
            dataType: 'json',
            success: function (expandedFormNumber) {
                $('#expanded-form').text(expandedFormNumber.answer);
            },
            error: function () {
                alert("error");
            }
        });
    }
</script>

</body>

</html>
