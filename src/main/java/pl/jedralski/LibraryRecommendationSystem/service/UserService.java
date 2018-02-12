package pl.jedralski.LibraryRecommendationSystem.service;

import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.model.User;

public interface UserService {

    Long findUserIDByUsername(String username) throws DatabaseException;

    User findAllData(String username) throws DatabaseException;

    boolean addUser(String username, String password, String firstName, String lastName, String email) throws InputException, DatabaseException;

    boolean updateUser(Long id, String username, String firstName, String lastName, String email) throws InputException, DatabaseException;

    boolean updateUserPassword(Long id, String password) throws InputException, DatabaseException;

    boolean findUsername(String username) throws DatabaseException;

    String getHash(Long id) throws DatabaseException;
}
