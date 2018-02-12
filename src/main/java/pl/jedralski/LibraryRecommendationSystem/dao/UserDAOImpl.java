package pl.jedralski.LibraryRecommendationSystem.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import pl.jedralski.LibraryRecommendationSystem.dbconnection.DBConnector;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {


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
                userID = resultSet.getLong("id");
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT id, username, first_name, last_name, email FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String userName = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");

                user = new User(id, userName, firstName, lastName, email);
            }
            if (resultSet.next()) {
                throw new DatabaseException("Query returned more than 1 record.");
            }
            if (user == null) {
                throw new DatabaseException("No records matching username: " + username);
            }
            return user;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public boolean addUser(String username, String password, String firstName, String lastName, String email) throws InputException, DatabaseException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hash = passwordEncoder.encode(password);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnector.getConnection();
            String query = "INSERT INTO users (username, hash, first_name, last_name, email) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hash);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);
            preparedStatement.setString(5, email);
            preparedStatement.executeUpdate();
            return true;
        } catch (IllegalArgumentException e) {
            throw new InputException("Insert failed. ", e);
        } catch (SQLException e) {
            throw new DatabaseException("Update failed. ", e);
        } finally {
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public boolean updateUser(Long id, String username, String firstName, String lastName, String email) throws InputException, DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnector.getConnection();
            String query = "UPDATE users SET username = ?, first_name = ?, last_name = ?, email = ? WHERE id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, email);
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (IllegalArgumentException e) {
            throw new InputException("Insert failed. ", e);
        } catch (SQLException e) {
            throw new DatabaseException("Update failed. ", e);
        } finally {
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public boolean updateUserPassword(Long id, String password) throws InputException, DatabaseException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hash = passwordEncoder.encode(password);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnector.getConnection();
            String query = "UPDATE users SET hash = ? WHERE id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, hash);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (IllegalArgumentException e) {
            throw new InputException("Insert failed. ", e);
        } catch (SQLException e) {
            throw new DatabaseException("Update failed. ", e);
        } finally {
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public boolean findUsername(String username) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT username FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public String getHash(Long id) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT hash FROM users WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            String hash = null;
            while (resultSet.next()) {
                hash = resultSet.getString("hash");
            }
            return hash;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

}
