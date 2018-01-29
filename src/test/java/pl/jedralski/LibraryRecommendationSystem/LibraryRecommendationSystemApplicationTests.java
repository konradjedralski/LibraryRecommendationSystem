package pl.jedralski.LibraryRecommendationSystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jedralski.LibraryRecommendationSystem.dao.BookDAO;
import pl.jedralski.LibraryRecommendationSystem.dao.UserDAO;
import pl.jedralski.LibraryRecommendationSystem.model.Book;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryRecommendationSystemApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    BookDAO bookDAO;
    @Autowired
    UserDAO userDAO;

    @Test
    public void findByTitle() {
        String title = "Going Home: Unfinished Business/ Island of Flowers/ Mind Over Matter";
        try {
            System.out.println(bookDAO.findByTitle(title).getPublisher());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findUserIDByUsername() {
        String username = "konrado33";
        try {
            System.out.println(userDAO.findUserIDByUsername(username));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findRatings() {
        Long userID = 278859l;
        try {
            for (int i = 0; i < bookDAO.findRatings(userID).size(); i++) {
                System.out.println(bookDAO.findRatings(userID).get(i).getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findFavourites() {
        Long userID = 278859l;
        try {
            for (int i = 0; i < bookDAO.findFavourites(userID).size(); i++) {
                System.out.println(bookDAO.findFavourites(userID).get(i).getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
