<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="static demo.app.WebApp.TASK_1_NAME" %>
<%@ page import="static demo.app.WebApp.TASK_2_NAME" %>

<html>

<head>
    <title>Две задачки</title>

    <link rel="stylesheet" href="css/style.css">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="js/index.js" type="text/javascript"></script>
</head>

<body>

<h1 id="taskName">Две задачки</h1>

<div class="centered-div">
    <p>
        <select size="2" multiple name="options[]">
            <option disabled>Выберите задачку</option>

            <option selected value="1">
                <%= TASK_1_NAME %>
            </option>
            <option value="2">
                <%= TASK_2_NAME %>
            </option>
        </select>
    </p>
    <p><input type="submit" value="Выбрать"></p>
</div>

</body>

</html>
