$(function () {

    var password = document.getElementById("register-password");
    var confirm_password = document.getElementById("register-confirm-password");

    function validatePassword() {
        if (password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Password is not the same!");
        } else {
            confirm_password.setCustomValidity('');
        }
    }
    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
});