package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.model.User;
import pl.jedralski.LibraryRecommendationSystem.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";

    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, RedirectAttributes attributes) throws InputException, DatabaseException {
        if (user.getUsername().length() < 3) {
            attributes.addFlashAttribute("message", 2);
            return "redirect:/login/register";
        }
        if (user.getPassword().length() < 3) {
            attributes.addFlashAttribute("message", 3);
            return "redirect:/login/register";
        }
        if (userService.findUsername(user.getUsername()) == true) {
            attributes.addFlashAttribute("message", 1);
            return "redirect:/login/register";
        } else {

            if (bindingResult.hasErrors()) {
                return "register";
            } else {
                userService.addUser(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail());
                attributes.addFlashAttribute("message", 1);
                return "redirect:/login";
            }
        }

    }
}