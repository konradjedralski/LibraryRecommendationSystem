package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MainController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String main(Model model) {
        model.addAttribute("name", "Konrad");
        return "main";
    }
}
