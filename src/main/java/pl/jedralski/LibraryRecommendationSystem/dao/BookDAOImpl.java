package pl.jedralski.LibraryRecommendationSystem.dao;

import org.springframework.stereotype.Repository;
import pl.jedralski.LibraryRecommendationSystem.dbconnection.DBConnector;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    @Override
    public Book findByTitle(String title) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT author, publisher, genre, isbn, title, year_of_publication, image_url_l FROM books JOIN book_author ON books.author_id=book_author.id JOIN book_genre ON books.genre_id=book_genre.id JOIN book_publisher ON books.publisher_id=book_publisher.id WHERE title = ? LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            resultSet = preparedStatement.executeQuery();
            Book book = null;
            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            if (resultSet.next()) {
                String author = resultSet.getString("author");
                String publisher = resultSet.getString("publisher");
                String genre = resultSet.getString("genre");
                String isbn = resultSet.getString("isbn");
                String bookTitle = resultSet.getString("title");
                short year = resultSet.getShort("year_of_publication");
                String imageL = resultSet.getString("image_url_l");

                book = new Book(author, publisher, genre, isbn, bookTitle, year, imageL);
            }
            if (resultSet.next()) {
                throw new DatabaseException("Query returned more than 1 record.");
            }
            if (book == null) {
                throw new DatabaseException("No records matching ID: " + title);
            }
            return book;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public List<Book> findRatings(Long userID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT rating, title, image_url_m FROM books JOIN book_rating ON books.id=book_rating.book_id WHERE book_rating.user_id = ? ORDER BY book_id ASC";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();

            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            while (resultSet.next()) {
                short rating = resultSet.getShort("rating");
                String title = resultSet.getString("title");
                String imageM = resultSet.getString("image_url_m");

                books.add(new Book(title, imageM, rating));
            }
            return books;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public List<Book> findWaitings(Long userID) throws DatabaseException {
        return null;
    }

    @Override
    public List<Book> findFavourites(Long userID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT title, image_url_l FROM books JOIN book_rating ON books.id=book_rating.book_id WHERE book_rating.user_id = ? AND rating = 10 ORDER BY book_id ASC";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();

            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String imageM = resultSet.getString("image_url_l");

                books.add(new Book(title, imageM));
            }
            return books;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public List<Book> findBorrowed(Long userID) throws DatabaseException {
        return null;
    }

    @Override
    public List<Book> findArchive(Long userID) throws DatabaseException {
        return null;
    }

    @Override
    public List<Book> findLast(Long userID) throws DatabaseException {
        return null;
    }

    @Override
    public Book findById(Long bookID) throws DatabaseException {
        return null;
    }
}
