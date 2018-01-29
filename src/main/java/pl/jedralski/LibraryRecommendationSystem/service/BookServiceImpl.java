package pl.jedralski.LibraryRecommendationSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jedralski.LibraryRecommendationSystem.dao.BookDAO;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
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
        return findById(bookID);
    }
}
