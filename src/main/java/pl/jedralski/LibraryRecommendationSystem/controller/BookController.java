package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jedralski.LibraryRecommendationSystem.model.Book;
import pl.jedralski.LibraryRecommendationSystem.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("")
    public String waiting(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "book";
    }

    @RequestMapping(value = "/titleSearch", method = RequestMethod.GET)
    public String search(@RequestParam(value = "search", required = false) String title, Model model, Authentication authentication) {
        Book searchResult = null;

        try {
            searchResult = bookService.findByTitle(title);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        model.addAttribute("username", authentication.getName());
        model.addAttribute("search", searchResult);
        return "book";
    }


}
