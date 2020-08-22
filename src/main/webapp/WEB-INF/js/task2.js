$(document).ready(function () {
    $("#calcExpandedFormBtn").click(function (event) {
        event.preventDefault();
        calcExpandedFormClick();
    });

    $("#saveInputBtn").click(function (event) {
        event.preventDefault();
        saveInputClick();
    });
});

function calcExpandedFormClick() {
    const number = {
        number: parseInt($("#number").val())
    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "api/get-expanded-form",
        data: JSON.stringify(number),
        dataType: 'json',
        success: function (response) {
            $('#expanded-form').text(response.answer);
        },
        error: function () {
            alert("error");
        }
    });
}

function saveInputClick() {
    const taskInput = {
        taskName: $("#taskName").text(),
        input: parseInt($("#number").val())
    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "api/save-task-input",
        data: JSON.stringify(taskInput),
        dataType: 'json',
        success: function (response) {
            window.location = 'api/download-task-input?' + response.answer
        },
        error: function () {
            alert("error");
        }
    });
}
