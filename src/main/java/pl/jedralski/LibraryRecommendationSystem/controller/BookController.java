package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.service.BookService;
import pl.jedralski.LibraryRecommendationSystem.service.UserService;
import pl.jedralski.LibraryRecommendationSystem.util.UserUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/titleSearch", method = RequestMethod.GET)
    public String searchByTitle(@RequestParam(value = "search", required = false) String title, Model model, Authentication authentication, HttpServletRequest request, RedirectAttributes attributes) throws DatabaseException {
        if (bookService.findByTitle(title) != null) {
            model.addAttribute("username", authentication.getName());
            model.addAttribute("search", bookService.findByTitle(title));
            model.addAttribute("rate", bookService.usersAndRating(bookService.findByTitle(title).getId()));
            model.addAttribute("userRating", bookService.findUserBookRating(userService.findUserIDByUsername(authentication.getName()), bookService.findByTitle(title).getId()));

            if (UserUtils.hasRoleAdmin()) {
                model.addAttribute("admin", 1);
            }
            return "book";
        } else {
            attributes.addFlashAttribute("message", 1);
            return "redirect:" + request.getHeader("Referer");
        }
    }

    @RequestMapping("/search/{info}/{id}")
    public String searchById(@PathVariable Long id, @PathVariable short info, Model model, Authentication authentication) throws DatabaseException {

        model.addAttribute("username", authentication.getName());
        model.addAttribute("search", bookService.findById(id));
        model.addAttribute("rate", bookService.usersAndRating(id));
        model.addAttribute("userRating", bookService.findUserBookRating(userService.findUserIDByUsername(authentication.getName()), id));
        model.addAttribute("info", info);

        if (UserUtils.hasRoleAdmin()) {
            model.addAttribute("admin", 1);
        }
        return "book";
    }

    @RequestMapping("/borrow/{bookID}")
    public String borrowBook(@PathVariable Long bookID, Authentication authentication) throws InputException, DatabaseException {
        Long userID = userService.findUserIDByUsername(authentication.getName());
        short availability = bookService.checkBookAvailability(bookID);
        if (availability > 0) {
            if (!(bookService.checkBorrowed(userID, bookID))) {
                bookService.addBookBorrowed(userID, bookID);
                bookService.updateBookAvailability(bookID, --availability);
                return "redirect:/book/search/1/{bookID}";
            } else {
                if (!(bookService.checkBorrowedActive(userID, bookID))) {
                    bookService.updateBorrowedActive(userID, bookID, true);
                    bookService.updateBookAvailability(bookID, --availability);
                    return "redirect:/book/search/2/{bookID}";
                } else {
                    return "redirect:/book/search/3/{bookID}";
                }
            }
        } else {
            return "redirect:/book/search/4/{bookID}";
        }
    }

    @RequestMapping("/return/{bookID}")
    public String returnBook(@PathVariable Long bookID, Authentication authentication) throws InputException, DatabaseException {
        Long userID = userService.findUserIDByUsername(authentication.getName());
        short availability = bookService.checkBookAvailability(bookID);
        if (bookService.checkBorrowed(userID, bookID)) {
            if (bookService.checkBorrowedActive(userID, bookID)) {
                bookService.updateBorrowedActive(userID, bookID, false);
                bookService.updateBookAvailability(bookID, ++availability);
                return "redirect:/book/search/5/{bookID}";
            } else {
                return "redirect:/book/search/6/{bookID}";
            }
        } else {
            return "redirect:/book/search/7/{bookID}";
        }
    }

    @RequestMapping("/rating/{rating}/{bookID}")
    public String addRating(@PathVariable short rating, @PathVariable Long bookID, Authentication authentication) throws InputException, DatabaseException {
        Long userID = userService.findUserIDByUsername(authentication.getName());
        if (!(bookService.checkRating(userID, bookID))) {
            bookService.addRating(userID, bookID, rating);
            bookService.deleteWaiting(userID, bookID);
            return "redirect:/book/search/8/{bookID}";
        } else {
            bookService.updateRating(userID, bookID, rating);
            bookService.deleteWaiting(userID, bookID);
            return "redirect:/book/search/9/{bookID}";
        }
    }

    @RequestMapping("/waiting/{bookID}")
    public String addWaiting(@PathVariable Long bookID, Authentication authentication) throws InputException, DatabaseException {
        Long userID = userService.findUserIDByUsername(authentication.getName());
        if (!(bookService.checkWaiting(userID, bookID))) {
            bookService.addWaiting(userID, bookID);
            return "redirect:/book/search/10/{bookID}";
        } else {
            return "redirect:/book/search/11/{bookID}";
        }
    }
}
