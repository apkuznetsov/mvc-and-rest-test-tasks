$(document).ready(function () {
    $("#getSubstringsThatInStringsBtn").click(function (event) {
        event.preventDefault();
        getSubstringsThatInStringsClick();
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
