package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.model.User;
import pl.jedralski.LibraryRecommendationSystem.service.UserService;
import pl.jedralski.LibraryRecommendationSystem.util.UserUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Resource(name = "authenticationManager")
    private AuthenticationManager authenticationManager;

    @RequestMapping("")
    public String account(Model model, Authentication authentication) throws DatabaseException {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("userData", userService.findAllData(authentication.getName()));

        if (UserUtils.hasRoleAdmin()) {
            model.addAttribute("admin", 1);
        }

        return "account";
    }

    @RequestMapping("/edit/profile")
    public String editProfile(Model model, Authentication authentication) throws DatabaseException {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("userData", userService.findAllData(authentication.getName()));
        if (UserUtils.hasRoleAdmin()) {
            model.addAttribute("admin", 1);
        }
        return "edit-profile";
    }

    @PostMapping("/edit/profile")
    public String editProfilePost(@RequestParam("password") String password, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, RedirectAttributes attributes, Model model, Authentication authentication, HttpServletRequest request) throws InputException, DatabaseException {
        User userData = userService.findAllData(authentication.getName());
        model.addAttribute("username", authentication.getName());
        model.addAttribute("userData", userData);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (username.equals("")) {
            username = userData.getUsername();
        }
        if (email.equals("")) {
            email = userData.getEmail();
        }
        if (firstName.equals("")) {
            firstName = userData.getFirstName();
        }
        if (lastName.equals("")) {
            lastName = userData.getLastName();
        }
        if (!(passwordEncoder.matches(password, userService.getHash(userData.getId())))) {
            attributes.addFlashAttribute("message", 3);
            return "redirect:/account/edit/profile";
        }
        if (username.equals(userData.getUsername())) {
            userService.updateUser(userData.getId(), username, firstName, lastName, email);
            attributes.addFlashAttribute("message", 2);
            return "redirect:/account";
        } else {
            if (userService.findUsername(username)) {
                attributes.addFlashAttribute("message", 2);
                return "redirect:/account/edit/profile";
            } else {
                userService.updateUser(userData.getId(), username, firstName, lastName, email);
                login(request, username, password);
                attributes.addFlashAttribute("message", 2);
                return "redirect:/account";
            }
        }
    }

    //Authentication new username
    private void login(HttpServletRequest request, String userName, String password) {
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, password);

        //Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        //Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }

    @RequestMapping("/edit/password")
    public String editPassword(Model model, Authentication authentication) throws DatabaseException {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("userData", userService.findAllData(authentication.getName()));
        if (UserUtils.hasRoleAdmin()) {
            model.addAttribute("admin", 1);
        }
        return "edit-password";
    }

    @PostMapping("/edit/password")
    public String editPasswordPost(@RequestParam("current-password") String currentPassword, @RequestParam("new-password") String password, Model model, Authentication authentication, RedirectAttributes attributes) throws InputException, DatabaseException {
        User userData = userService.findAllData(authentication.getName());
        model.addAttribute("username", authentication.getName());
        model.addAttribute("userData", userData);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(currentPassword, userService.getHash(userData.getId()))) {
            if (currentPassword.equals(password)) {
                attributes.addFlashAttribute("message", 3);
                return "redirect:/account/edit/password";
            } else {
                userService.updateUserPassword(userData.getId(), password);
                attributes.addFlashAttribute("message", 3);
                return "redirect:/account";
            }
        } else {
            attributes.addFlashAttribute("message", 2);
            return "redirect:/account/edit/password";
        }
    }
}
