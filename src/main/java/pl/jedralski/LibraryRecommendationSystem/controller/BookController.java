package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

    @RequestMapping("")
    public String waiting() {
        return "book";
    }
}
