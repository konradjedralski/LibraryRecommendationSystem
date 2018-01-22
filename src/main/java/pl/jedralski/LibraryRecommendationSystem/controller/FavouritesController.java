package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/favourites")
public class FavouritesController {

    @RequestMapping("")
    public String favourites() {
        return "favourites";
    }
}
