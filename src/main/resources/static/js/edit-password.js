$(function () {
    function validatePassword() {
        if (document.getElementById("new-password").value != document.getElementById("confirm-password").value) {
            document.getElementById("confirm-password").setCustomValidity("Password is not the same!");
        } else {
            document.getElementById("confirm-password").setCustomValidity('');
        }
    }

    document.getElementById("new-password").onchange = validatePassword;
    document.getElementById("confirm-password").onkeyup = validatePassword;

    $('#submit-password').click(function () {
        if (document.getElementById("current-password").value == "") {
            document.getElementById("current-password").setCustomValidity("Current password can not be empty!");
        } else {
            document.getElementById("current-password").setCustomValidity('');
        }
        if (!(/[a-zA-Z0-9]{3,}/.test(document.getElementById("new-password").value))) {
            document.getElementById("new-password").setCustomValidity("New password must have minimum 3 characters (only letters and numbers)!");
        } else {
            document.getElementById("new-password").setCustomValidity('');
        }
    });
});

function cleanCurrentPassword(e) {
    if (window.event) {
        document.getElementById("current-password").setCustomValidity('');
    }
}

function cleanNewPassword(e) {
    if (window.event) {
        document.getElementById("new-password").setCustomValidity('');
    }
}