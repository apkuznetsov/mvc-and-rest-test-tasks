$(document).ready(function () {
    $("#calcExpandedFormBtn").click(function (event) {
        event.preventDefault();
        calcExpandedFormBtnClick();
    });
});

function calcExpandedFormBtnClick() {
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
