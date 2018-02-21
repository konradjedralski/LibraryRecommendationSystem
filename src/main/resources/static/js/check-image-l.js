$(window).on("load", function() {
    $('.img').each(
        function(){
                var height = $(this).height();
                if (height < 10)
                    $(this).attr("src", "/img/not-found-l.jpg");
        });
});