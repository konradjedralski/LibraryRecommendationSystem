package pl.jedralski.LibraryRecommendationSystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jedralski.LibraryRecommendationSystem.dao.UserDAO;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.model.User;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public Long findUserIDByUsername(String username) throws DatabaseException {
        return userDAO.findUserIDByUsername(username);
    }

    @Override
    public User findAllData(String username) throws DatabaseException {
        return userDAO.findAllData(username);
    }

    @Override
    public User addUser(String username, String password, String firstName, String lastName, String email) throws InputException, DatabaseException {
        return userDAO.addUser(username, password, firstName, lastName, email);
    }
}
