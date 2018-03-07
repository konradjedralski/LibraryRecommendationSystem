$(function () {
    $('#submit').click(function () {
        if (document.getElementById("title").value == "") {
            document.getElementById("title").setCustomValidity("Title can not be empty!");
        } else {
            document.getElementById("title").setCustomValidity('');
        }
        if (document.getElementById("author").value == "") {
            document.getElementById("author").setCustomValidity("Author can not be empty!");
        } else {
            document.getElementById("author").setCustomValidity('');
        }
        if (document.getElementById("publisher").value == "") {
            document.getElementById("publisher").setCustomValidity("Publisher can not be empty!");
        } else {
            document.getElementById("publisher").setCustomValidity('');
        }
        if (!(/^[12][0-9]{3}$/.test(document.getElementById("year").value))) {
            document.getElementById("year").setCustomValidity("Wrong publication year!");
        } else {
            document.getElementById("year").setCustomValidity('');
        }
        if (!(/^(97(8|9))?\d{9}(\d|X)$/.test(document.getElementById("isbn").value))) {
            document.getElementById("isbn").setCustomValidity("Wrong ISBN number!");
        } else {
            document.getElementById("isbn").setCustomValidity('');
        }
        if (document.getElementById("image-s").value == "") {
            document.getElementById("image-s").setCustomValidity("URL Image S can not be empty!");
        } else {
            document.getElementById("image-s").setCustomValidity('');
        }
        if (document.getElementById("image-m").value == "") {
            document.getElementById("image-m").setCustomValidity("URL Image M can not be empty!");
        } else {
            document.getElementById("image-m").setCustomValidity('');
        }
        if (document.getElementById("image-l").value == "") {
            document.getElementById("image-l").setCustomValidity("URL Image L can not be empty!");
        } else {
            document.getElementById("image-l").setCustomValidity('');
        }
        if (!(/^[1-9][0-9]*$/.test(document.getElementById("availability").value))) {
            document.getElementById("availability").setCustomValidity("Wrong availability number!");
        } else {
            document.getElementById("availability").setCustomValidity('');
        }
    });
});

function cleanTitle(e) {
    if (window.event) {
        document.getElementById("title").setCustomValidity('');
    }
}

function cleanAuthor(e) {
    if (window.event) {
        document.getElementById("author").setCustomValidity('');
    }
}

function cleanPublisher(e) {
    if (window.event) {
        document.getElementById("publisher").setCustomValidity('');
    }
}

function cleanYear(e) {
    if (window.event) {
        document.getElementById("year").setCustomValidity('');
    }
}

function cleanIsbn(e) {
    if (window.event) {
        document.getElementById("isbn").setCustomValidity('');
    }
}

function cleanImageS(e) {
    if (window.event) {
        document.getElementById("image-s").setCustomValidity('');
    }
}

function cleanImageM(e) {
    if (window.event) {
        document.getElementById("image-m").setCustomValidity('');
    }
}

function cleanImageL(e) {
    if (window.event) {
        document.getElementById("image-l").setCustomValidity('');
    }
}

function cleanAvailability(e) {
    if (window.event) {
        document.getElementById("availability").setCustomValidity('');
    }
}