<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="static demo.app.WebApp.TASK_1_NAME" %>

<html>

<head>
    <title>Две задачки</title>

    <link rel="stylesheet" href="css/style.css">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="js/task1.js" type="text/javascript"></script>
</head>

<body>

<h1 id="taskName"><%= TASK_1_NAME %></h1>

<div class="centered-div">
    <div class="list-container">
        <div class="list">
            <div class="list-item"><label for="substrings">Введите массив подстрок (в формате JSON):</label></div>
            <div class="list-item"><label for="substrings">Например, ["arp", "live", "strong"]</label></div>

            <div class="list-item">
                <input type="file" id="file" name="file"/>
                <input type="button"
                       id="uploadInputBtn"
                       value="Выгрузить ввод. данные из файла"/>
            </div>

            <div class="list-item"><input type="text" id="substrings"/></div>

            <div class="list-item"><label for="strings">Введите массив строк (в формате JSON):</label></div>
            <div class="list-item"><input type="text" id="strings"/></div>

            <div class="list-item">
                <input type="button"
                       id="getSubstringsThatInStringsBtn"
                       value="Найти подстроки"/>
            </div>
            <div class="list-item"><label id="resultSubstrings"></label></div>

            <div class="list-item">
                <input type="button"
                       id="saveInputBtn"
                       value="Сохранить ввод"/>
            </div>
        </div>
    </div>
</div>

</body>

</html>
