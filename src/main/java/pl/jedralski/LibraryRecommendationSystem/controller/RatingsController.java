package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ratings")
public class RatingsController {

    @RequestMapping("")
    public String ratings() {
        return "ratings";
    }
}
