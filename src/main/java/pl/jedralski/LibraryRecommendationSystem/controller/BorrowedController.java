package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/borrowed")
public class BorrowedController {

    @RequestMapping("")
    public String borrowed() {
        return "borrowed";
    }
}
