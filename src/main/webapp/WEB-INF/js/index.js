$(document).ready(function () {
    $("#chooseBtn").click(function (event) {
        event.preventDefault();
        chooseClick();
    });
});

function chooseClick() {
    const select = document.getElementById("chooseOptions");
    const option = select.options[select.selectedIndex].value;
    window.location.href = "task" + option;
}
