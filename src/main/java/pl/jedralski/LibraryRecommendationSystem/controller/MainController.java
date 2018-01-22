package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("")
    public String main(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "main";
    }
}
