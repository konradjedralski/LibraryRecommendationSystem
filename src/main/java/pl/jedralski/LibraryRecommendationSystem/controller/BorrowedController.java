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
import pl.jedralski.LibraryRecommendationSystem.util.UserUtils;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/borrowed")
public class BorrowedController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String borrowed(Model model, Authentication authentication) throws DatabaseException{
        if (UserUtils.hasRoleAdmin()){
            model.addAttribute("admin", 1);
        }

        List<Book> borrowedList = new ArrayList<>();
        for (Book book : bookService.findBorrowed(userService.findUserIDByUsername(authentication.getName()))) {
            borrowedList.add(new Book(book.getId(), book.getImageL()));
        }
        if (borrowedList.isEmpty()){
            model.addAttribute("checkBorrowed", 1);
        }
        model.addAttribute("username", authentication.getName());
        model.addAttribute("borrowedList", borrowedList);
        return "borrowed";
    }

    @RequestMapping("/archive")
    public String archive(Model model, Authentication authentication) throws DatabaseException{
        if (UserUtils.hasRoleAdmin()){
            model.addAttribute("admin", 1);
        }

        List<Book> archiveList = new ArrayList<>();
        for (Book book : bookService.findArchive(userService.findUserIDByUsername(authentication.getName()))) {
            archiveList.add(new Book(book.getId(), book.getImageL()));
        }
        if (archiveList.isEmpty()){
            model.addAttribute("checkArchive", 1);
        }
        model.addAttribute("username", authentication.getName());
        model.addAttribute("archiveList", archiveList);
        return "archive";
    }
}
