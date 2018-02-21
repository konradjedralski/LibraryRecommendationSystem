package pl.jedralski.LibraryRecommendationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.model.Book;
import pl.jedralski.LibraryRecommendationSystem.service.RecommendationService;
import pl.jedralski.LibraryRecommendationSystem.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recommendation")
public class RecommendationController {

    @Autowired
    private UserService userService;
    @Autowired
    private RecommendationService recommendationService;

    @RequestMapping("")
    public String main(Authentication authentication) throws InputException, DatabaseException {
        Long userID = userService.findUserIDByUsername(authentication.getName());
        List<Book> recommendationList = new ArrayList<>();

        if (!recommendationService.userRatingsList(userID).isEmpty()) {
            int i = 0;
            for (Book recomended : recommendationService.booksToRecomended(recommendationService.userRatingsList(userID), recommendationService.getNeighbourDistance(recommendationService.userRatingsList(userID), recommendationService.getNeighbour(recommendationService.userRatingsList(userID), userID)), recommendationService.avgGenreRating(userID), recommendationService.avgAuthorRating(userID))) {
                recommendationList.add(new Book(recomended.getId(), recomended.getRatingRecommended()));
                i++;
                if (i > 11) {
                    break;
                }
            }
            recommendationService.deleteRecommended(userID);
            recommendationService.addRecommended(userID, recommendationList);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
