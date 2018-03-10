var everyImages = 0;
var addedImages = 0;

$(document).ready(function(){
    everyImages = $(".img-carousel").length;
    $(".img-carousel").each(function(){
        $(this).attr("src", $(this).attr("src")+"?_="+(new Date().getTime()));
        $(this).on('load', function() {
            if ($(this)[0].height < 10)
            {
                $(this).attr("src", "/img/not-found-l.jpg");
                addedImages--;
            }
            $(this).width('100%');
            $(this).height('100%');
            addedImages++;
            if (addedImages >= everyImages) {
                $('#myCarousel1').carousel({
                    interval: 6000
                });

                $('#myCarousel2').carousel({
                    interval: 6000
                });

                $('.carousel .item').each(function () {
                    var next = $(this).next();
                    if (!next.length) {
                        next = $(this).siblings(':first');
                    }
                    next.children(':first-child').clone().appendTo($(this));

                    for (var i = 0; i < 4; i++) {
                        next = next.next();
                        if (!next.length) {
                            next = $(this).siblings(':first');
                        }

                        next.children(':first-child').clone().appendTo($(this));
                    }
                });
            }
        });
    });
});