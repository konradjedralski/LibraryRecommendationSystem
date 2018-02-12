$(window).on("load", function() {
    $('img').each(
        function(){
                var height = $(this).height();
                if (height < 30)
                    $(this).attr("src", "https://juststickers.in/wp-content/uploads/2016/12/404-error-not-found.png");
        });
});