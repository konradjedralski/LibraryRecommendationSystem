package pl.jedralski.LibraryRecommendationSystem.dao;

import org.springframework.stereotype.Repository;
import pl.jedralski.LibraryRecommendationSystem.dbconnection.DBConnector;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
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
            String query = "SELECT books.id, author, publisher, genre, isbn, title, year_of_publication, image_url_l FROM books JOIN book_author ON books.author_id=book_author.id JOIN book_genre ON books.genre_id=book_genre.id JOIN book_publisher ON books.publisher_id=book_publisher.id WHERE LOWER(title) = LOWER(?) LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            resultSet = preparedStatement.executeQuery();
            Book book = null;
            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String author = resultSet.getString("author");
                String publisher = resultSet.getString("publisher");
                String genre = resultSet.getString("genre");
                String isbn = resultSet.getString("isbn");
                String bookTitle = resultSet.getString("title");
                short year = resultSet.getShort("year_of_publication");
                String imageL = resultSet.getString("image_url_l");

                book = new Book(id, author, publisher, genre, isbn, bookTitle, year, imageL);
            }
            if (resultSet.next()) {
                throw new DatabaseException("Query returned more than 1 record.");
            }
            if (book == null) {
                //throw new DatabaseException("No records matching title: " + title);
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
            String query = "SELECT books.id, rating, title, image_url_m FROM books JOIN book_rating ON books.id=book_rating.book_id WHERE book_rating.user_id = ? ORDER BY rating DESC";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();

            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                short rating = resultSet.getShort("rating");
                String title = resultSet.getString("title");
                String imageM = resultSet.getString("image_url_m");

                books.add(new Book(id, title, imageM, rating));
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT books.id, title, author, image_url_m FROM books JOIN book_waiting ON books.id=book_waiting.book_id JOIN book_author ON books.author_id=book_author.id WHERE book_waiting.user_id = ? ORDER BY books.id ASC";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();

            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String imageM = resultSet.getString("image_url_m");

                books.add(new Book(id, title, author, imageM));
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
    public List<Book> findFavourites(Long userID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT books.id, title, image_url_l FROM books JOIN book_rating ON books.id=book_rating.book_id WHERE book_rating.user_id = ? AND rating = 10 ORDER BY book_id ASC";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();

            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String imageL = resultSet.getString("image_url_l");

                books.add(new Book(id, title, imageL));
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT books.id, image_url_l FROM books JOIN book_borrowed ON books.id=book_borrowed.book_id WHERE book_borrowed.user_id = ? AND active = true ORDER BY books.id ASC";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();

            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String imageL = resultSet.getString("image_url_l");

                books.add(new Book(id, imageL));
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
    public List<Book> findArchive(Long userID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT books.id, image_url_l FROM books JOIN book_borrowed ON books.id=book_borrowed.book_id WHERE book_borrowed.user_id = ? AND active = false ORDER BY books.id ASC";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();

            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String imageL = resultSet.getString("image_url_l");

                books.add(new Book(id, imageL));
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
    public List<Book> findLast(Long userID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT book_id, image_url_l FROM books JOIN book_rating ON books.id=book_rating.book_id WHERE user_id = ? ORDER BY book_rating.id DESC LIMIT 12";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();

            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            while (resultSet.next()) {
                Long id = resultSet.getLong("book_id");
                String imageL = resultSet.getString("image_url_l");

                books.add(new Book(id, imageL));
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
    public Book findById(Long bookID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT books.id, author, publisher, genre, isbn, title, year_of_publication, image_url_l FROM books JOIN book_author ON books.author_id=book_author.id JOIN book_genre ON books.genre_id=book_genre.id JOIN book_publisher ON books.publisher_id=book_publisher.id WHERE books.id = ? LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, bookID);
            resultSet = preparedStatement.executeQuery();
            Book book = null;
            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String author = resultSet.getString("author");
                String publisher = resultSet.getString("publisher");
                String genre = resultSet.getString("genre");
                String isbn = resultSet.getString("isbn");
                String bookTitle = resultSet.getString("title");
                short year = resultSet.getShort("year_of_publication");
                String imageL = resultSet.getString("image_url_l");

                book = new Book(id, author, publisher, genre, isbn, bookTitle, year, imageL);
            }
            if (resultSet.next()) {
                throw new DatabaseException("Query returned more than 1 record.");
            }
            if (book == null) {
                throw new DatabaseException("No records matching ID: " + bookID);
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
    public boolean checkBorrowed(Long userID, Long bookID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT * FROM book_borrowed WHERE user_id = ? AND book_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            preparedStatement.setLong(2, bookID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return false;
            }
            if (resultSet.next()) {
                return true;
            } else return false;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }

    }

    @Override
    public boolean checkBorrowedActive(Long userID, Long bookID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT * FROM book_borrowed WHERE user_id = ? AND book_id = ? AND active = 'true'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            preparedStatement.setLong(2, bookID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return false;
            }
            if (resultSet.next()) {
                return true;
            } else return false;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public short checkBookAvailability(Long bookID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT availability FROM books WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, bookID);
            resultSet = preparedStatement.executeQuery();
            Short availability = null;
            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            if (resultSet.next()) {
                availability = resultSet.getShort("availability");
            }
            if (resultSet.next()) {
                throw new DatabaseException("Query returned more than 1 record.");
            }
            return availability;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public boolean updateBookAvailability(Long bookID, short availability) throws InputException, DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnector.getConnection();
            String query = "UPDATE books SET availability = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setShort(1, availability);
            preparedStatement.setLong(2, bookID);
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
    public boolean addBookBorrowed(Long userID, Long bookID) throws InputException, DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnector.getConnection();
            String query = "INSERT INTO book_borrowed (user_id, book_id, active) VALUES (?, ?, true)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            preparedStatement.setLong(2, bookID);
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
    public boolean updateBorrowedActive(Long userID, Long bookID, boolean active) throws InputException, DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnector.getConnection();
            String query = "UPDATE book_borrowed SET active = ? WHERE user_id = ? AND book_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, active);
            preparedStatement.setLong(2, userID);
            preparedStatement.setLong(3, bookID);
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
    public boolean checkRating(Long userID, Long bookID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT * FROM book_rating where user_id = ? AND book_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            preparedStatement.setLong(2, bookID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return false;
            }
            if (resultSet.next()) {
                return true;
            } else return false;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public boolean addRating(Long userID, Long bookID, short rating) throws InputException, DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnector.getConnection();
            String query = "INSERT INTO book_rating (user_id, book_id, rating) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            preparedStatement.setLong(2, bookID);
            preparedStatement.setLong(3, rating);
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
    public boolean updateRating(Long userID, Long bookID, short rating) throws InputException, DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnector.getConnection();
            String query = "UPDATE book_rating SET rating = ? WHERE user_id = ? AND book_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setShort(1, rating);
            preparedStatement.setLong(2, userID);
            preparedStatement.setLong(3, bookID);
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
    public boolean checkWaiting(Long userID, Long bookID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT * FROM book_waiting where user_id = ? AND book_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            preparedStatement.setLong(2, bookID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return false;
            }
            if (resultSet.next()) {
                return true;
            } else return false;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public boolean addWaiting(Long userID, Long bookID) throws InputException, DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnector.getConnection();
            String query = "INSERT INTO book_waiting (user_id, book_id) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            preparedStatement.setLong(2, bookID);
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
    public boolean deleteWaiting(Long userID, Long bookID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnector.getConnection();
            String query = "DELETE FROM book_waiting WHERE user_id = ? AND book_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            preparedStatement.setLong(2, bookID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DatabaseException("Delete failed. ", e);
        } finally {
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public Book usersAndRating(Long bookID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT COUNT(user_id) as users_number, CAST(AVG(rating) AS NUMERIC (10, 2)) as avg_rating FROM book_rating WHERE book_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, bookID);
            resultSet = preparedStatement.executeQuery();
            Book book = null;
            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            if (resultSet.next()) {
                int usersNumber = resultSet.getInt("users_number");
                double avgRating = resultSet.getDouble("avg_rating");
                book = new Book(usersNumber, avgRating);
            }
            if (resultSet.next()) {
                throw new DatabaseException("Query returned more than 1 record.");
            }
            if (book == null) {
                //throw new DatabaseException("No records matching: id);
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

}
