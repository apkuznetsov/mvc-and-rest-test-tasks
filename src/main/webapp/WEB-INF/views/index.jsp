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

<div class="canvas-like-div">
    <div class="centered-div">
        <label>
            Введите натуральное число:<br>
            <input type="text"
                   id="number"/>
        </label>

        <input type="button" id="submitButton" value="Submit"/>

        <h3 id="expanded-form"></h3>
    </div>
</div>

<script>

    $(document).ready(function () {
        $("#submitButton").click(function (event) {
            event.preventDefault();
            ajaxSubmitForm();
        });
    });

    function ajaxSubmitForm() {
        const number = $("#number").val();

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "get-expanded-form",
            data: JSON.stringify(number),
            dataType: 'json',
            success: function (data) {
                $('#expanded-form').text(data);
            },
            error: function (data) {
                alert('error');
            }
        });
    }
</script>

</body>

</html>
