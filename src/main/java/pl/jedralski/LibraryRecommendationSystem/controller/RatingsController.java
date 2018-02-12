package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
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
    public String ratings(Model model, Authentication authentication) throws DatabaseException {

        List<Book> ratingsList = new ArrayList<>();

        for (Book book : bookService.findRatings(userService.findUserIDByUsername(authentication.getName()))) {
            ratingsList.add(new Book(book.getId(), book.getTitle(), book.getImageM(), book.getRating()));
        }
        model.addAttribute("username", authentication.getName());
        model.addAttribute("ratingsList", ratingsList);
        return "ratings";
    }

}
