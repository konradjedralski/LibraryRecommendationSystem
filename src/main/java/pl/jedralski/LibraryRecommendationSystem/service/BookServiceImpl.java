package pl.jedralski.LibraryRecommendationSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jedralski.LibraryRecommendationSystem.dao.BookDAO;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.model.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Override
    public Book findByTitle(String title) throws DatabaseException {
        return bookDAO.findByTitle(title);
    }

    @Override
    public List<Book> findRatings(Long userID) throws DatabaseException {
        return bookDAO.findRatings(userID);
    }

    @Override
    public List<Book> findWaitings(Long userID) throws DatabaseException {
        return bookDAO.findWaitings(userID);
    }

    @Override
    public List<Book> findFavourites(Long userID) throws DatabaseException {
        return bookDAO.findFavourites(userID);
    }

    @Override
    public List<Book> findBorrowed(Long userID) throws DatabaseException {
        return bookDAO.findBorrowed(userID);
    }

    @Override
    public List<Book> findArchive(Long userID) throws DatabaseException {
        return bookDAO.findArchive(userID);
    }

    @Override
    public List<Book> findLast(Long userID) throws DatabaseException {
        return bookDAO.findLast(userID);
    }

    @Override
    public Book findById(Long bookID) throws DatabaseException {
        return bookDAO.findById(bookID);
    }

    @Override
    public boolean checkBorrowed(Long userID, Long bookID) throws DatabaseException {
        return bookDAO.checkBorrowed(userID, bookID);
    }

    @Override
    public boolean checkBorrowedActive(Long userID, Long bookID) throws DatabaseException {
        return bookDAO.checkBorrowedActive(userID, bookID);
    }

    @Override
    public short checkBookAvailability(Long bookID) throws DatabaseException {
        return bookDAO.checkBookAvailability(bookID);
    }

    @Override
    public boolean updateBookAvailability(Long bookID, short availability) throws InputException, DatabaseException {
        return bookDAO.updateBookAvailability(bookID, availability);
    }

    @Override
    public boolean addBookBorrowed(Long userID, Long bookID) throws InputException, DatabaseException {
        return bookDAO.addBookBorrowed(userID, bookID);
    }

    @Override
    public boolean updateBorrowedActive(Long userID, Long bookID, boolean active) throws InputException, DatabaseException {
        return bookDAO.updateBorrowedActive(userID, bookID, active);
    }

    @Override
    public boolean checkRating(Long userID, Long bookID) throws DatabaseException {
        return bookDAO.checkRating(userID, bookID);
    }

    @Override
    public boolean addRating(Long userID, Long bookID, short rating) throws InputException, DatabaseException {
        return bookDAO.addRating(userID, bookID, rating);
    }

    @Override
    public boolean updateRating(Long userID, Long bookID, short rating) throws InputException, DatabaseException {
        return bookDAO.updateRating(userID, bookID, rating);
    }

    @Override
    public boolean checkWaiting(Long userID, Long bookID) throws DatabaseException {
        return bookDAO.checkWaiting(userID, bookID);
    }

    @Override
    public boolean addWaiting(Long userID, Long bookID) throws InputException, DatabaseException {
        return bookDAO.addWaiting(userID, bookID);
    }

    @Override
    public boolean deleteWaiting(Long userID, Long bookID) throws DatabaseException {
        return bookDAO.deleteWaiting(userID, bookID);
    }
}
