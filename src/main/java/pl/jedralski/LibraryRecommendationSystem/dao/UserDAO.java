package pl.jedralski.LibraryRecommendationSystem.dao;

import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.model.User;

public interface UserDAO {

    Long findUserIDByUsername(String username) throws DatabaseException;
    User findAllData (String username) throws DatabaseException;
    User addUser (String username, String password, String firstName, String lastName, String email) throws InputException, DatabaseException;
}
