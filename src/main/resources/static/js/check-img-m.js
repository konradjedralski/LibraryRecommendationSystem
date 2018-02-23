$(window).on("load", function() {
    $('.img').each(
        function(){
            var $this = $(this);
            if ($this.height() < 10) {
                $this.attr("src", "/img/not-found-m.jpg");
            } else {
                $this.css("width", "100%");
                $this.css("height", "100%");
            }
        });
});