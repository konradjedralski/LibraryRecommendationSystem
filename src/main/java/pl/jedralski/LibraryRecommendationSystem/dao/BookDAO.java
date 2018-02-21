package pl.jedralski.LibraryRecommendationSystem.dao;

import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.model.Book;

import java.util.List;

public interface BookDAO {

    Book findByTitle(String title) throws DatabaseException;

    List<Book> findRatings(Long userID) throws DatabaseException;

    List<Book> findWaitings(Long userID) throws DatabaseException;

    List<Book> findFavourites(Long userID) throws DatabaseException;

    List<Book> findBorrowed(Long userID) throws DatabaseException;

    List<Book> findArchive(Long userID) throws DatabaseException;

    List<Book> findLast(Long userID) throws DatabaseException;

    Book findById(Long bookID) throws DatabaseException;

    boolean checkBorrowed(Long userID, Long bookID) throws DatabaseException;

    boolean checkBorrowedActive(Long userID, Long bookID) throws DatabaseException;

    short checkBookAvailability(Long bookID) throws DatabaseException;

    boolean updateBookAvailability(Long bookID, short availability) throws InputException, DatabaseException;

    boolean addBookBorrowed(Long userID, Long bookID) throws InputException, DatabaseException;

    boolean updateBorrowedActive(Long userID, Long bookID, boolean active) throws InputException, DatabaseException;

    boolean checkRating(Long userID, Long bookID) throws DatabaseException;

    boolean addRating(Long userID, Long bookID, short rating) throws InputException, DatabaseException;

    boolean updateRating(Long userID, Long bookID, short rating) throws InputException, DatabaseException;

    boolean checkWaiting(Long userID, Long bookID) throws DatabaseException;

    boolean addWaiting(Long userID, Long bookID) throws InputException, DatabaseException;

    boolean deleteWaiting(Long userID, Long bookID) throws DatabaseException;

    Book usersAndRating(Long bookID) throws DatabaseException;
}
