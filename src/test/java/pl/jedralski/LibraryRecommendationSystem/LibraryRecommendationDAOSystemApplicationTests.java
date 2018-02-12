package pl.jedralski.LibraryRecommendationSystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jedralski.LibraryRecommendationSystem.dao.BookDAO;
import pl.jedralski.LibraryRecommendationSystem.dao.RecommendationDAO;
import pl.jedralski.LibraryRecommendationSystem.dao.UserDAO;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.model.Book;
import pl.jedralski.LibraryRecommendationSystem.model.Neighbour;
import pl.jedralski.LibraryRecommendationSystem.service.BookService;
import pl.jedralski.LibraryRecommendationSystem.service.RecommendationService;
import pl.jedralski.LibraryRecommendationSystem.service.UserService;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryRecommendationDAOSystemApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    BookDAO bookDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    @Autowired
    RecommendationDAO recommendationDAO;
    @Autowired
    RecommendationService recommendationService;

    @Test
    public void findByTitle() {
        String title = "Going Home: Unfinished Business/ Island of Flowers/ Mind Over Matter";
        try {
            System.out.println(bookService.findByTitle(title).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByTitleService() {
        String title = "Going Home: Unfinished Business/ Island of Flowers/ Mind Over Matter";
        try {
            System.out.println(bookDAO.findByTitle(title).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        Long id = 5l;
        try {
            System.out.println(bookDAO.findById(id).getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByIdService() {
        Long id = 5l;
        try {
            System.out.println(bookService.findById(id).getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findUserIDByUsername() {
        String username = "konrado33";
        try {
            System.out.println(userDAO.findAllData(username).getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllData() {
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
                System.out.println(bookDAO.findRatings(userID).get(i).getId());
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
                System.out.println(bookDAO.findFavourites(userID).get(i).getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findWaitings() {
        Long userID = 278859l;
        try {
            for (int i = 0; i < bookDAO.findWaitings(userID).size(); i++) {
                System.out.println(bookDAO.findWaitings(userID).get(i).getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findBorrowed() {
        Long userID = 278859l;
        try {
            for (int i = 0; i < bookDAO.findBorrowed(userID).size(); i++) {
                System.out.println(bookDAO.findBorrowed(userID).get(i).getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findArchive() {
        Long userID = 278859l;
        try {
            for (int i = 0; i < bookDAO.findArchive(userID).size(); i++) {
                System.out.println(bookDAO.findArchive(userID).get(i).getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findLast() {
        Long userID = 278859l;
        try {
            for (int i = 0; i < bookDAO.findLast(userID).size(); i++) {
                System.out.println(bookDAO.findLast(userID).get(i).getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkBorrowed() {
        Long userID = 278859l;
        Long bookID = 46l;
        try {
            System.out.println(bookDAO.checkBorrowed(userID, bookID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkBorrowedActive() {
        Long userID = 278859l;
        Long bookID = 46l;
        try {
            System.out.println(bookDAO.checkBorrowedActive(userID, bookID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkBookAvailability() {
        Long bookID = 2l;
        try {
            System.out.println(bookDAO.checkBookAvailability(bookID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateBookAvailability() {
        Long bookID = 1l;
        short availability = 6;
        try {
            bookDAO.updateBookAvailability(bookID, availability);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addBookBorrowed() {
        Long userID = 278859l;
        Long bookID = 2l;
        try {
            bookDAO.addBookBorrowed(userID, bookID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateBorrowedActive() {
        Long userID = 278859l;
        Long bookID = 2l;
        boolean active = false;
        try {
            bookDAO.updateBorrowedActive(userID, bookID, active);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkRating() {
        Long userID = 278859l;
        Long bookID = 21l;
        try {
            System.out.println(bookDAO.checkRating(userID, bookID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addRating() {
        Long userID = 278859l;
        Long bookID = 21l;
        short rating = 4;
        try {
            bookDAO.addRating(userID, bookID, rating);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateRating() {
        Long userID = 278859l;
        Long bookID = 2l;
        short rating = 6;
        try {
            bookDAO.updateRating(userID, bookID, rating);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkWaiting() {
        Long userID = 278859l;
        Long bookID = 31l;
        try {
            System.out.println(bookDAO.checkWaiting(userID, bookID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addWaiting() {
        Long userID = 278859l;
        Long bookID = 40l;
        try {
            bookDAO.addWaiting(userID, bookID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteWaiting() {
        Long userID = 278859l;
        Long bookID = 40l;
        try {
            bookDAO.deleteWaiting(userID, bookID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findUsername() {
        String username = "konrado33";
        try {
            System.out.println(userService.findUsername(username));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getNeighbourDistance() {
//        List<Book> readBooks = new ArrayList<>();
//        readBooks.add(new Book(9l, (short) 10));
//        readBooks.add(new Book(16l, (short) 10));
//        readBooks.add(new Book(15l, (short) 10));
//        readBooks.add(new Book(19l, (short) 10));
//        readBooks.add(new Book(3l, (short) 10));
//        readBooks.add(new Book(41l, (short) 10));

        Long userID = 278859l;
//        List<Neighbour> neighbourList = new ArrayList<>();

        try {
//            for (Book book : recommendationDAO.userRatingsList(userID)){
//                readBooks.add(new Book(book.getId(), book))
//            }
//            for (Neighbour neighbour : recommendationDAO.getNeighbour(readBooks, userID)) {
//                neighbourList.add(neighbour);
//            }
            int i = 0;
            for (Neighbour neighbour1 : recommendationDAO.getNeighbourDistance(recommendationDAO.userRatingsList(userID), recommendationDAO.getNeighbour(recommendationDAO.userRatingsList(userID), userID))) {
                System.out.println(neighbour1.getDistance());
                i++;
            }
            System.out.println("Wynik : " + i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getHash() {
        Long id = 278859l;
        try {
            System.out.println(userDAO.getHash(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void avgGenreRating() {
        Long id = 278859l;
        try {
            for (int i = 0; i < recommendationDAO.avgGenreRating(id).size(); i++) {
                System.out.println(recommendationDAO.avgGenreRating(id).get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void avgAuthorRating() {
        Long id = 278859l;
        try {
            for (int i = 0; i < recommendationDAO.avgAuthorRating(id).size(); i++) {
                System.out.println(recommendationDAO.avgAuthorRating(id).get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void booksToRecomended() {
        List<Book> readBooks = new ArrayList<>();
        readBooks.add(new Book(9l, (short) 10));
        readBooks.add(new Book(16l, (short) 10));
        readBooks.add(new Book(15l, (short) 10));
        readBooks.add(new Book(19l, (short) 10));
        readBooks.add(new Book(3l, (short) 10));
        readBooks.add(new Book(41l, (short) 10));

        Long userID = 278859l;
        List<Neighbour> neighbourList = new ArrayList<>();
        List<Neighbour> neighbourDistanceList = new ArrayList<>();
        HashMap<Long, Double> avgGenreRating = new HashMap<>();
        HashMap<Long, Double> avgAuthorRating = new HashMap<>();

        try {
            for (Neighbour neighbour : recommendationDAO.getNeighbour(readBooks, userID)) {
                neighbourList.add(neighbour);
            }
            for (Neighbour neighbour1 : recommendationDAO.getNeighbourDistance(readBooks, neighbourList)) {
                neighbourDistanceList.add(neighbour1);
            }
            Set<Map.Entry<Long, Double>> entrySet1 = recommendationDAO.avgGenreRating(userID).entrySet();
            for (Map.Entry<Long, Double> entry : entrySet1) {
                avgGenreRating.put(entry.getKey(), entry.getValue());
            }
            Set<Map.Entry<Long, Double>> entrySet2 = recommendationDAO.avgAuthorRating(userID).entrySet();
            for (Map.Entry<Long, Double> entry : entrySet2) {
                avgAuthorRating.put(entry.getKey(), entry.getValue());
            }
            for (Book book : recommendationDAO.booksToRecomended(readBooks, neighbourDistanceList, avgGenreRating, avgAuthorRating)){
                System.out.println(book.getRating());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findToRecomended() throws DatabaseException{
        Long userID = 278859l;
//        List<Book> userRatingsList = recommendationService.userRatingsList(userID);
//        List<Neighbour> getNeighbour = recommendationService.getNeighbour(userRatingsList, userID);
//        List<Neighbour> getNeighbourDistance = recommendationService.getNeighbourDistance(userRatingsList, getNeighbour);
//        HashMap<Long, Double> avgGenreRating = recommendationService.avgGenreRating(userID);
//        HashMap<Long, Double> avgAuthorRating = recommendationService.avgAuthorRating(userID);
//        List<Book> booksToRecomended = recommendationService.booksToRecomended(getNeighbourDistance, avgGenreRating, avgAuthorRating);

        for (Book book : recommendationService.booksToRecomended(recommendationService.userRatingsList(userID), recommendationService.getNeighbourDistance(recommendationService.userRatingsList(userID), recommendationService.getNeighbour(recommendationService.userRatingsList(userID), userID)), recommendationService.avgGenreRating(userID), recommendationService.avgAuthorRating(userID))){
            System.out.println(book.getId());
        }
    }
}