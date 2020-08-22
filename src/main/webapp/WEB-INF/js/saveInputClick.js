$(document).ready(function () {
    $("#saveInputBtn").click(function (event) {
        event.preventDefault();
        calcExpandedFormClick();
    });
});

function calcExpandedFormClick() {
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
        success: function (ndata) {
            alert(ndata);
        },
        error: function () {
            alert("error");
        }
    });
}
