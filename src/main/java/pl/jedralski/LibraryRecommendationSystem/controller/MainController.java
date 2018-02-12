package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.model.Book;
import pl.jedralski.LibraryRecommendationSystem.model.Neighbour;
import pl.jedralski.LibraryRecommendationSystem.service.BookService;
import pl.jedralski.LibraryRecommendationSystem.service.RecommendationService;
import pl.jedralski.LibraryRecommendationSystem.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private RecommendationService recommendationService;

    @RequestMapping("")
    public String main(Model model, Authentication authentication) throws DatabaseException {
        String username = authentication.getName();
        Long userID = userService.findUserIDByUsername(username);
        model.addAttribute("username", username);
        model.addAttribute("checkRecommendation", 0);
        model.addAttribute("check", 0);
//        List<Book> userRatingsList = recommendationService.userRatingsList(userID);
//        List<Neighbour> getNeighbour = recommendationService.getNeighbour(userRatingsList, userID);
//        List<Neighbour> getNeighbourDistance = recommendationService.getNeighbourDistance(userRatingsList, getNeighbour);
//        HashMap<Long, Double> avgGenreRating = recommendationService.avgGenreRating(userID);
//        HashMap<Long, Double> avgAuthorRating = recommendationService.avgAuthorRating(userID);
//        List<Book> booksToRecomended = recommendationService.booksToRecomended(getNeighbourDistance, avgGenreRating, avgAuthorRating);
//        List<Book> recommendationList = new ArrayList<>();
        List<Book> lastList = new ArrayList<>();

//        for (int i = 0; i < 12; i++) {
//            recommendationList.add(new Book(booksToRecomended.get(i).getId(), booksToRecomended.get(i).getRating(), booksToRecomended.get(i).getImageL()));
//        }
//
//        if (!recommendationList.isEmpty()) {
//            model.addAttribute("checkRecommendation", 1);
//            model.addAttribute("firstRecommendation", recommendationList.get(0));
//            lastList.remove(0);
//            model.addAttribute("restRecommendation", recommendationList);
//        }

        for (Book book : bookService.findLast(userService.findUserIDByUsername(username))) {
            lastList.add(new Book(book.getId(), book.getImageL()));
        }
        if (!lastList.isEmpty()) {
            model.addAttribute("check", 1);
            model.addAttribute("firstBook", lastList.get(0));
            lastList.remove(0);
            model.addAttribute("restBooks", lastList);
        }
        return "main";
    }
}
