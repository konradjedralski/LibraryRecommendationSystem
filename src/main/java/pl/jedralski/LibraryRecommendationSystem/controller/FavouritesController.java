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
@RequestMapping("/favourites")
public class FavouritesController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String favourites(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        List<Book> favouritesList = new ArrayList<>();
        try {
            for (Book book : bookService.findFavourites(userService.findUserIDByUsername(authentication.getName()))){
                favouritesList.add(new Book(book.getTitle(), book.getImageL()));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        model.addAttribute("favouritesList", favouritesList);
        return "favourites";
    }
}
