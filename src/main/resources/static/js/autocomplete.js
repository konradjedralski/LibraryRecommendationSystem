$(document).ready(function () {

    var options = {

        url: "/json/books.json",

        getValue: "name",

        list: {
            match: {
                enabled: true
            }
        },

        template: {
            type: "iconLeft",
            fields: {
                iconSrc: "icon"
            }
        },

        theme: "bootstrap"
    };

    $("#search").easyAutocomplete(options);

    $(".eac-bootstrap").each(function () {
        $(this).attr("style", "width:100%");
    });

});