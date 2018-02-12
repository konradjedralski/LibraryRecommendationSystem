package pl.jedralski.LibraryRecommendationSystem.service;

import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.model.Book;
import pl.jedralski.LibraryRecommendationSystem.model.Neighbour;

import java.util.HashMap;
import java.util.List;

public interface RecommendationService {

    List<Book> userRatingsList(Long userID) throws DatabaseException;

    List<Neighbour> getNeighbour(List<Book> userRatingsList, Long userID) throws DatabaseException;

    List<Neighbour> getNeighbourDistance(List<Book> userRatingsList, List<Neighbour> neighborhood) throws DatabaseException;

    HashMap<Long, Double> avgGenreRating(Long userID) throws DatabaseException;

    HashMap<Long, Double> avgAuthorRating(Long userID) throws DatabaseException;

    List<Book> booksToRecomended (List<Book> userRatingsList, List<Neighbour> neighborhood, HashMap<Long, Double> avgGenreRating, HashMap<Long, Double> avgAuthorRating) throws DatabaseException;
}
