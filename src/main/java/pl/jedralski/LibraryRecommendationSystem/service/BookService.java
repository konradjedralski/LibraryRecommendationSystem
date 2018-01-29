package pl.jedralski.LibraryRecommendationSystem.service;

import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
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
}
