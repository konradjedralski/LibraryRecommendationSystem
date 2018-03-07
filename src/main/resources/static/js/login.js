$(function () {
    function validatePassword() {
        if (document.getElementById("register-password").value != document.getElementById("register-confirm-password").value) {
            document.getElementById("register-confirm-password").setCustomValidity("Password is not the same!");
        } else {
            document.getElementById("register-confirm-password").setCustomValidity('');
        }
    }
    document.getElementById("register-password").onchange = validatePassword;
    document.getElementById("register-confirm-password").onkeyup = validatePassword;
});