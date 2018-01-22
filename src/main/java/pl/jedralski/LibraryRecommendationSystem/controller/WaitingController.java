package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/waiting")
public class WaitingController {

    @RequestMapping("")
    public String waiting() {
        return "waiting";
    }
}
