package pl.jedralski.LibraryRecommendationSystem.dao;

import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.model.Book;
import pl.jedralski.LibraryRecommendationSystem.model.Neighbour;

import java.util.HashMap;
import java.util.List;

public interface RecommendationDAO {

    List<Book> userRatingsList(Long userID) throws DatabaseException;

    List<Neighbour> getNeighbour(List<Book> userRatingsList, Long userID) throws DatabaseException;

    List<Neighbour> getNeighbourDistance(List<Book> userRatingsList, List<Neighbour> neighborhood) throws DatabaseException;

    HashMap<Long, Double> avgGenreRating(Long userID) throws DatabaseException;

    HashMap<Long, Double> avgAuthorRating(Long userID) throws DatabaseException;

    List<Book> booksToRecomended(List<Book> userRatingsList, List<Neighbour> neighborhood, HashMap<Long, Double> avgGenreRating, HashMap<Long, Double> avgAuthorRating) throws DatabaseException;

    List<Book> showRecommended(Long userID) throws DatabaseException;

    boolean deleteRecommended(Long userID) throws DatabaseException;

    boolean addRecommended(Long userID, List<Book> booksToRecommended) throws InputException, DatabaseException;

}
