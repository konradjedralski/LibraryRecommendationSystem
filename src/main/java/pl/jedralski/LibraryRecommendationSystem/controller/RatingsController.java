package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jedralski.LibraryRecommendationSystem.model.Book;
import pl.jedralski.LibraryRecommendationSystem.service.BookService;
import pl.jedralski.LibraryRecommendationSystem.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ratings")
public class RatingsController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String ratings(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        List<Book> ratingsList = new ArrayList<>();
        try {
            for (Book book : bookService.findRatings(userService.findUserIDByUsername(authentication.getName()))){
                ratingsList.add(new Book(book.getTitle(), book.getImageM(), book.getRating()));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        model.addAttribute("ratingsList", ratingsList);
        return "ratings";
    }

}
