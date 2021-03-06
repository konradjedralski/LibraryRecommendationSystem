package pl.jedralski.LibraryRecommendationSystem.service;

import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.model.Book;

import java.util.List;

public interface BookService {

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

    short findUserBookRating(Long userID, Long bookID) throws DatabaseException;

    boolean findBook (String title) throws DatabaseException;

    int findAuthor (String author) throws DatabaseException;

    int findPublisher (String publisher) throws DatabaseException;

    short findGenre (String genre) throws DatabaseException;

    boolean addAuthor (String author) throws InputException, DatabaseException;

    boolean addPublisher(String publisher) throws InputException, DatabaseException;

    boolean addBook(String isbn, String title, short publicationYear, String imageS, String imageM, String imageL, int authorID, int publisherID, short genreID, short availability) throws InputException, DatabaseException;
}
