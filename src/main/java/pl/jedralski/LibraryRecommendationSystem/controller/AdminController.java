package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.service.BookService;
import pl.jedralski.LibraryRecommendationSystem.util.UserUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @RequestMapping("")
    public String account(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        if (UserUtils.hasRoleAdmin()) {
            model.addAttribute("admin", 1);
        }
        return "admin";
    }

    @RequestMapping("/addBook")
    public String addBook(@RequestParam("title") String title, @RequestParam("author") String author, @RequestParam("publisher") String publisher, @RequestParam("year") String year, @RequestParam("genre") String genre, @RequestParam("isbn") String isbn, @RequestParam("image-s") String imageS, @RequestParam("image-m") String imageM, @RequestParam("image-l") String imageL, @RequestParam("availability") String availability, RedirectAttributes attributes) throws InputException, DatabaseException {

        int authorID = bookService.findAuthor(author);
        int publisherID = bookService.findPublisher(publisher);
        short genreID = bookService.findGenre(genre);

        if (!bookService.findBook(title)) {
            if (authorID == 0) {
                bookService.addAuthor(author);
                authorID = bookService.findAuthor(author);
            }
            if (publisherID == 0) {
                bookService.addPublisher(publisher);
                publisherID = bookService.findPublisher(publisher);
            }
            bookService.addBook(isbn, title, Short.parseShort(year), imageS, imageM, imageL, authorID, publisherID, genreID, Short.parseShort(availability));
            attributes.addFlashAttribute("info", 1);
            return "redirect:/admin";
        } else {
            attributes.addFlashAttribute("info", 2);
            return "redirect:/admin";
        }
    }
}
