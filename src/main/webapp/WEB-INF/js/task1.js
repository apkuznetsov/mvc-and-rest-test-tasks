$(document).ready(function () {
    $("#getSubstringsThatInStringsBtn").click(function (event) {
        event.preventDefault();
        getSubstringsThatInStringsClick();
    });

    $("#saveInputBtn").click(function (event) {
        event.preventDefault();
        saveInputClick();
    });

});

function getSubstringsThatInStringsClick() {
    const input = {
        substrings: JSON.parse($("#substrings").val()),
        strings: JSON.parse($("#strings").val())
    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "api/get-substrings-that-in-strings",
        data: JSON.stringify(input),
        dataType: 'json',
        success: function (response) {
            $('#resultSubstrings').text(JSON.stringify(response));
        },
        error: function () {
            alert("error");
        }
    });
}

function saveInputClick() {
    const input = {
        taskName: $("#taskName").text(),
        substrings: JSON.parse($("#substrings").val()),
        strings: JSON.parse($("#strings").val())
    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "api/jquery-save-substrs-and-strs-input",
        data: JSON.stringify(input),
        dataType: 'json',
        success: function (response) {
            window.location = 'api/jquery-download-substrs-and-strs-input?' + response.answer
        },
        error: function () {
            alert("error");
        }
    });
}
