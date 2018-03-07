$(function () {
    $('#submit-profile').click(function () {
        if (!(/^$|[a-zA-Z0-9]{3,}/.test(document.getElementById("username").value))) {
            document.getElementById("username").setCustomValidity("New username must have minimum 3 characters (only letters and numbers)!");
        } else {
            document.getElementById("username").setCustomValidity('');
        }
        if (!(/^$|^[a-z\d]+[\w\d.-]*@(?:[a-z\d]+[a-z\d-]+\.){1,5}[a-z]{2,6}$/.test(document.getElementById("email").value))) {
            document.getElementById("email").setCustomValidity("Invalid email address!");
        } else {
            document.getElementById("email").setCustomValidity('');
        }
        if (document.getElementById("password").value == "") {
            document.getElementById("password").setCustomValidity("Password can not be empty!");
        } else {
            document.getElementById("password").setCustomValidity('');
        }
    });
});

function cleanUsername(e) {
    if (window.event) {
        document.getElementById("username").setCustomValidity('');
    }
}

function cleanEmail(e) {
    if (window.event) {
        document.getElementById("email").setCustomValidity('');
    }
}

function cleanPassword(e) {
    if (window.event) {
        document.getElementById("password").setCustomValidity('');
    }
}
