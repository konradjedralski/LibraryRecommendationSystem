$(document).ready(function () {

    var options = {

        url: "/json/books.json",

        getValue: "name",

        list: {
            match: {
                enabled: true
            },
            onLoadEvent: function() {
                $(".eac-icon").each(function () {
                    $(this).attr("src", $(this).attr("src")+"?_="+(new Date().getTime()));
                    $(this).on('load', function() {
                        if ($(this)[0].height < 10) {
                            $(this).attr("src", "/img/not-found-m.jpg");
                        }
                    });
                });
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