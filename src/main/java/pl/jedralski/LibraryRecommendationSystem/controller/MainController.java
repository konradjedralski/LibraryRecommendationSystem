package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jedralski.LibraryRecommendationSystem.service.BookService;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private BookService bookService;

    @RequestMapping("")
    public String main(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "main";
    }
}
