package pl.jedralski.LibraryRecommendationSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jedralski.LibraryRecommendationSystem.dao.RecommendationDAO;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.model.Book;
import pl.jedralski.LibraryRecommendationSystem.model.Neighbour;

import java.util.HashMap;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private RecommendationDAO recommendationDAO;

    @Override
    public List<Book> userRatingsList(Long userID) throws DatabaseException {
        return recommendationDAO.userRatingsList(userID);
    }

    @Override
    public List<Neighbour> getNeighbour(List<Book> userRatingsList, Long userID) throws DatabaseException {
        return recommendationDAO.getNeighbour(userRatingsList, userID);
    }

    @Override
    public List<Neighbour> getNeighbourDistance(List<Book> userRatingsList, List<Neighbour> neighborhood) throws DatabaseException {
        return recommendationDAO.getNeighbourDistance(userRatingsList, neighborhood);
    }

    @Override
    public HashMap<Long, Double> avgGenreRating(Long userID) throws DatabaseException {
        return recommendationDAO.avgGenreRating(userID);
    }

    @Override
    public HashMap<Long, Double> avgAuthorRating(Long userID) throws DatabaseException {
        return recommendationDAO.avgAuthorRating(userID);
    }

    @Override
    public List<Book> booksToRecomended(List<Book> userRatingsList, List<Neighbour> neighborhood, HashMap<Long, Double> avgGenreRating, HashMap<Long, Double> avgAuthorRating) throws DatabaseException {
        return recommendationDAO.booksToRecomended(userRatingsList, neighborhood, avgGenreRating, avgAuthorRating);
    }
}
