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
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
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
    public void findByTitle() throws DatabaseException {
        String title = "Going Home: Unfinished Business/ Island of Flowers/ Mind Over Matter";
        System.out.println(bookService.findByTitle(title).getId());
    }

    @Test
    public void findByTitleService() throws DatabaseException {
        String title = "Going Home: Unfinished Business/ Island of Flowers/ Mind Over Matter";
        System.out.println(bookDAO.findByTitle(title).getId());
    }

    @Test
    public void findById() throws DatabaseException {
        Long id = 5l;
        System.out.println(bookDAO.findById(id).getTitle());
    }

    @Test
    public void findByIdService() throws DatabaseException {
        Long id = 5l;
        System.out.println(bookService.findById(id).getTitle());
    }

    @Test
    public void findUserIDByUsername() throws DatabaseException {
        String username = "konrado33";
        System.out.println(userDAO.findAllData(username).getLastName());
    }

    @Test
    public void findAllData() throws DatabaseException {
        String username = "konrado33";
        System.out.println(userDAO.findUserIDByUsername(username));
    }

    @Test
    public void findRatings() throws DatabaseException {
        Long userID = 278859l;
        for (int i = 0; i < bookDAO.findRatings(userID).size(); i++) {
            System.out.println(bookDAO.findRatings(userID).get(i).getId());
        }
    }

    @Test
    public void findFavourites() throws DatabaseException {
        Long userID = 278859l;
        for (int i = 0; i < bookDAO.findFavourites(userID).size(); i++) {
            System.out.println(bookDAO.findFavourites(userID).get(i).getId());
        }
    }

    @Test
    public void findWaitings() throws DatabaseException {
        Long userID = 278859l;
        for (int i = 0; i < bookDAO.findWaitings(userID).size(); i++) {
            System.out.println(bookDAO.findWaitings(userID).get(i).getTitle());
        }
    }

    @Test
    public void findBorrowed() throws DatabaseException {
        Long userID = 278859l;
        for (int i = 0; i < bookDAO.findBorrowed(userID).size(); i++) {
            System.out.println(bookDAO.findBorrowed(userID).get(i).getId());
        }
    }

    @Test
    public void findArchive() throws DatabaseException {
        Long userID = 278859l;
        for (int i = 0; i < bookDAO.findArchive(userID).size(); i++) {
            System.out.println(bookDAO.findArchive(userID).get(i).getId());
        }
    }

    @Test
    public void findLast() throws DatabaseException {
        Long userID = 278863l;
        for (int i = 0; i < bookDAO.findLast(userID).size(); i++) {
            System.out.println(bookDAO.findLast(userID).get(i).getId());
        }
    }

    @Test
    public void checkBorrowed() throws DatabaseException {
        Long userID = 278859l;
        Long bookID = 46l;
        System.out.println(bookDAO.checkBorrowed(userID, bookID));
    }

    @Test
    public void checkBorrowedActive() throws DatabaseException {
        Long userID = 278859l;
        Long bookID = 46l;
        System.out.println(bookDAO.checkBorrowedActive(userID, bookID));
    }

    @Test
    public void checkBookAvailability() throws DatabaseException {
        Long bookID = 2l;
        System.out.println(bookDAO.checkBookAvailability(bookID));
    }

    @Test
    public void updateBookAvailability() throws InputException, DatabaseException {
        Long bookID = 1l;
        short availability = 6;
        bookDAO.updateBookAvailability(bookID, availability);
    }

    @Test
    public void addBookBorrowed() throws InputException, DatabaseException {
        Long userID = 278859l;
        Long bookID = 2l;
        bookDAO.addBookBorrowed(userID, bookID);
    }

    @Test
    public void updateBorrowedActive() throws InputException, DatabaseException {
        Long userID = 278859l;
        Long bookID = 2l;
        boolean active = false;
        bookDAO.updateBorrowedActive(userID, bookID, active);
    }

    @Test
    public void checkRating() throws DatabaseException {
        Long userID = 278859l;
        Long bookID = 21l;
        System.out.println(bookDAO.checkRating(userID, bookID));
    }

    @Test
    public void addRating() throws InputException, DatabaseException {
        Long userID = 278859l;
        Long bookID = 21l;
        short rating = 4;
        bookDAO.addRating(userID, bookID, rating);
    }

    @Test
    public void updateRating() throws InputException, DatabaseException {
        Long userID = 278859l;
        Long bookID = 2l;
        short rating = 6;
        bookDAO.updateRating(userID, bookID, rating);
    }

    @Test
    public void checkWaiting() throws DatabaseException {
        Long userID = 278859l;
        Long bookID = 31l;
        System.out.println(bookDAO.checkWaiting(userID, bookID));
    }

    @Test
    public void addWaiting() throws InputException, DatabaseException {
        Long userID = 278859l;
        Long bookID = 40l;
        bookDAO.addWaiting(userID, bookID);
    }

    @Test
    public void deleteWaiting() throws DatabaseException {
        Long userID = 278859l;
        Long bookID = 40l;
        bookDAO.deleteWaiting(userID, bookID);
    }

    @Test
    public void findUsername() throws DatabaseException {
        String username = "konrado33";
        System.out.println(userService.findUsername(username));
    }

    @Test
    public void getNeighbourDistance() throws DatabaseException {
//        List<Book> readBooks = new ArrayList<>();
//        readBooks.add(new Book(9l, (short) 10));
//        readBooks.add(new Book(16l, (short) 10));
//        readBooks.add(new Book(15l, (short) 10));
//        readBooks.add(new Book(19l, (short) 10));
//        readBooks.add(new Book(3l, (short) 10));
//        readBooks.add(new Book(41l, (short) 10));

        Long userID = 278859l;
//        List<Neighbour> neighbourList = new ArrayList<>();

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
    }

    @Test
    public void getHash() throws DatabaseException {
        Long id = 278859l;
        System.out.println(userDAO.getHash(id));
    }

    @Test
    public void avgGenreRating() throws DatabaseException {
        Long id = 278859l;
        for (int i = 0; i < recommendationDAO.avgGenreRating(id).size(); i++) {
            System.out.println(recommendationDAO.avgGenreRating(id).get(i));
        }
    }

    @Test
    public void avgAuthorRating() throws DatabaseException {
        Long id = 278859l;
        for (int i = 0; i < recommendationDAO.avgAuthorRating(id).size(); i++) {
            System.out.println(recommendationDAO.avgAuthorRating(id).get(i));
        }
    }

    @Test
    public void booksToRecomended() throws DatabaseException {
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
        for (Book book : recommendationDAO.booksToRecomended(readBooks, neighbourDistanceList, avgGenreRating, avgAuthorRating)) {
            System.out.println(book.getRating());
        }
    }

    @Test
    public void findToRecomended() throws DatabaseException {
        Long userID = 278863l;
//        List<Book> userRatingsList = recommendationService.userRatingsList(userID);
//        List<Neighbour> getNeighbour = recommendationService.getNeighbour(userRatingsList, userID);
//        List<Neighbour> getNeighbourDistance = recommendationService.getNeighbourDistance(userRatingsList, getNeighbour);
//        HashMap<Long, Double> avgGenreRating = recommendationService.avgGenreRating(userID);
//        HashMap<Long, Double> avgAuthorRating = recommendationService.avgAuthorRating(userID);
//        List<Book> booksToRecomended = recommendationService.booksToRecomended(getNeighbourDistance, avgGenreRating, avgAuthorRating);

        for (Book book : recommendationService.booksToRecomended(recommendationService.userRatingsList(userID), recommendationService.getNeighbourDistance(recommendationService.userRatingsList(userID), recommendationService.getNeighbour(recommendationService.userRatingsList(userID), userID)), recommendationService.avgGenreRating(userID), recommendationService.avgAuthorRating(userID))) {
            System.out.println(book.getId());
        }
    }

    @Test
    public void checkRecommendatedMethods() throws DatabaseException, InputException {
        Long userID = 278859l;
        List<Book> userRatingsList = recommendationService.userRatingsList(userID);
        List<Neighbour> getNeighbour = recommendationService.getNeighbour(userRatingsList, userID);
        List<Neighbour> getNeighbourDistance = recommendationService.getNeighbourDistance(userRatingsList, getNeighbour);
        HashMap<Long, Double> avgGenreRating = recommendationService.avgGenreRating(userID);
        HashMap<Long, Double> avgAuthorRating = recommendationService.avgAuthorRating(userID);
        List<Book> booksToRecomended = recommendationService.booksToRecomended(userRatingsList, getNeighbourDistance, avgGenreRating, avgAuthorRating);
        List<Book> recommendationList = new ArrayList<>();

        if (!recommendationService.userRatingsList(userID).isEmpty()) {
            int i = 0;
            for (Book recomended : booksToRecomended) {
                recommendationList.add(new Book(recomended.getId(), recomended.getRatingRecommended()));
                i++;
                if (i > 11) {
                    break;
                }
            }
            recommendationService.deleteRecommended(userID);
            recommendationService.addRecommended(userID, recommendationList);
        }
    }

    @Test
    public void usersAndRating() throws DatabaseException {
        Long bookID = 1503l;
        System.out.println(bookDAO.usersAndRating(bookID).getRatingRecommended());
    }
}