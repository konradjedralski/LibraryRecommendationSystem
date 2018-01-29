package pl.jedralski.LibraryRecommendationSystem.dao;

import org.springframework.stereotype.Repository;
import pl.jedralski.LibraryRecommendationSystem.dbconnection.DBConnector;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.model.Book;
import pl.jedralski.LibraryRecommendationSystem.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAOImpl implements UserDAO{


    @Override
    public Long findUserIDByUsername(String username) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT id FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            Long userID = null;
            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            if (resultSet.next()) {
                int id = resultSet.getInt("id");

                userID = (long)id;
            }
            if (resultSet.next()) {
                throw new DatabaseException("Query returned more than 1 record.");
            }
            if (userID == null) {
                throw new DatabaseException("No records matching username: " + username);
            }
            return userID;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public User findAllData(String username) throws DatabaseException {
        return null;
    }

    @Override
    public User addUser(String username, String password, String firstName, String lastName, String email) throws InputException, DatabaseException {
        return null;
    }
}
