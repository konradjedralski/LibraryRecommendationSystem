package pl.jedralski.LibraryRecommendationSystem.dao;

import org.springframework.stereotype.Repository;
import pl.jedralski.LibraryRecommendationSystem.dbconnection.DBConnector;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;
import pl.jedralski.LibraryRecommendationSystem.exception.InputException;
import pl.jedralski.LibraryRecommendationSystem.model.Book;
import pl.jedralski.LibraryRecommendationSystem.model.Neighbour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class RecommendationDAOImpl implements RecommendationDAO {

    //List of every ratings from user. Has fields: book_id and rating.
    @Override
    public List<Book> userRatingsList(Long userID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT book_id, rating FROM book_rating WHERE book_rating.user_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            resultSet = preparedStatement.executeQuery();
            List<Book> readBooks = new ArrayList<>();

            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            while (resultSet.next()) {
                Long id = resultSet.getLong("book_id");
                short rating = resultSet.getShort("rating");

                readBooks.add(new Book(id, rating));
            }
            return readBooks;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    //List of users (neighbour) who have the same books (as given user) which have been rating. Has fields : user_id, book_id and distance which now is 0.0.
    @Override
    public List<Neighbour> getNeighbour(List<Book> userRatingsList, Long userID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder builder = new StringBuilder();
        try {
            connection = DBConnector.getConnection();

            for (int i = 0; i < userRatingsList.size(); i++) {
                builder.append("?,");
            }

            String query = "SELECT user_id, COUNT(*) AS book_count FROM book_rating WHERE book_id IN (" + builder.deleteCharAt(builder.length() - 1).toString() + ") AND user_id != ? GROUP BY user_id ORDER BY book_count DESC LIMIT 50";
            preparedStatement = connection.prepareStatement(query);

            for (int i = 0; i < userRatingsList.size(); i++) {
                preparedStatement.setLong(i + 1, (userRatingsList.get(i).getId()));
            }

            preparedStatement.setLong(1 + userRatingsList.size(), userID);
            resultSet = preparedStatement.executeQuery();
            List<Neighbour> neighbour = new ArrayList<>();

            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            while (resultSet.next()) {
                Long id = resultSet.getLong("user_id");
                int sameBooks = resultSet.getInt("book_count");

                neighbour.add(new Neighbour(id, sameBooks));
            }
            return neighbour;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    //Add distance to neighbour
    @Override
    public List<Neighbour> getNeighbourDistance(List<Book> userRatingsList, List<Neighbour> neighborhood) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<Long, Integer> booksIndexes = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        try {
            connection = DBConnector.getConnection();

            for (int i = 0; i < userRatingsList.size(); i++) {
                builder.append("?,");
            }

            String query = "SELECT rating, book_id FROM book_rating WHERE user_id = ? AND book_id IN (" + builder.deleteCharAt(builder.length() - 1).toString() + ")";
            preparedStatement = connection.prepareStatement(query);

            for (int i = 0; i < userRatingsList.size(); i++) {
                preparedStatement.setLong(i + 2, (userRatingsList.get(i).getId()));
                booksIndexes.put(userRatingsList.get(i).getId(), i);
            }

            for (Neighbour neighbour : neighborhood) {
                preparedStatement.setLong(1, neighbour.getUserID());
                resultSet = preparedStatement.executeQuery();
                if (resultSet == null) {
                    throw new DatabaseException("Error returning result.");
                }
                while (resultSet.next()) {
                    short rating = resultSet.getShort("rating");
                    short bookRating = userRatingsList.get(booksIndexes.get(resultSet.getLong("book_id"))).getRating();
                    neighbour.setDistance(neighbour.getDistance() + Math.abs(rating - bookRating)); //Add the distance between user and neighbor based on the bookList.
                }
            }

            for (Neighbour neighbour : neighborhood) {
                neighbour.setDistance(neighbour.getDistance() / neighbour.getSameBooks()); //Divide result by the number of books so that the result is fair.
            }
            Collections.sort(neighborhood);
            return neighborhood;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    //List of user most liked genres
    @Override
    public HashMap<Long, Double> avgGenreRating(Long userID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT genre_id, AVG(rating) AS avg_rating FROM book_rating JOIN books ON book_rating.book_id=books.id WHERE user_id = ? GROUP BY genre_id";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            resultSet = preparedStatement.executeQuery();
            HashMap<Long, Double> avgGenreRatingMap = new HashMap<>();
            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            while (resultSet.next()) {
                Long genreID = resultSet.getLong("genre_id");
                double avgRating = resultSet.getDouble("avg_rating");

                avgGenreRatingMap.put(genreID, avgRating);
            }
            return avgGenreRatingMap;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    //List of user most liked authors
    @Override
    public HashMap<Long, Double> avgAuthorRating(Long userID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT author_id, AVG(rating) AS avg_rating FROM book_rating JOIN books ON book_rating.book_id=books.id WHERE user_id = ? GROUP BY author_id";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            resultSet = preparedStatement.executeQuery();
            HashMap<Long, Double> avgAuthorRatingMap = new HashMap<>();
            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            while (resultSet.next()) {
                Long authorID = resultSet.getLong("author_id");
                double avgRating = resultSet.getDouble("avg_rating");

                avgAuthorRatingMap.put(authorID, avgRating);
            }
            return avgAuthorRatingMap;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }


    //Choose 30 books from 5 most similar neighbours and add them new rating given most liked genres and authors from user who will get recommendation
    @Override
    public List<Book> booksToRecomended(List<Book> userRatingsList, List<Neighbour> neighborhood, HashMap<Long, Double> avgGenreRating, HashMap<Long, Double> avgAuthorRating) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            List<Book> booksToRecommend = new ArrayList<>();
            int k;
            if (userRatingsList.size() < 5) {
                k = userRatingsList.size();
            } else {
                k = 5;
            }
            StringBuilder builderRating = new StringBuilder();
            StringBuilder builderUser = new StringBuilder();
            for (int i = 0; i < userRatingsList.size(); i++) {
                builderRating.append("?,");
            }
            for (int i = 0; i < k; i++) {
                builderUser.append("?,");
            }
            String query = "SELECT rating, book_id, genre_id, author_id FROM book_rating JOIN books ON book_rating.book_id=books.id WHERE user_id IN(" + builderUser.deleteCharAt(builderUser.length() - 1).toString() + ") AND book_id NOT IN(" + builderRating.deleteCharAt(builderRating.length() - 1).toString() + ") ORDER BY rating DESC LIMIT 30";
            preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < k; i++) {
                preparedStatement.setLong(i + 1, (neighborhood.get(i).getUserID()));
            }
            int counter = k + 1;

            for (int i = 0; i < userRatingsList.size(); i++) {
                preparedStatement.setLong(counter, (userRatingsList.get(i).getId()));
                counter++;
            }
            resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            while (resultSet.next()) {
                Long bookID = resultSet.getLong("book_id");
                short rating = resultSet.getShort("rating");
                Long genreID = resultSet.getLong("genre_id");
                Long authorID = resultSet.getLong("author_id");
                Book recommendedBook = new Book(bookID, (double) rating);

                double newRating = (0.5 * recommendedBook.getRatingRecommended());
                if (!avgAuthorRating.containsKey(authorID)) {
                    newRating += (0.25 * recommendedBook.getRatingRecommended());
                } else {
                    newRating += (0.25 * avgAuthorRating.get(authorID));
                }
                if (!avgGenreRating.containsKey(genreID)) {
                    newRating += (0.25 * recommendedBook.getRatingRecommended());
                } else {
                    newRating += (0.25 * avgGenreRating.get(genreID));
                }
                recommendedBook.setRatingRecommended(newRating);
                booksToRecommend.add(recommendedBook);
            }
            Collections.sort(booksToRecommend);
            return booksToRecommend;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public List<Book> showRecommended(Long userID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            String query = "SELECT book_id, image_url_l FROM book_recommendation JOIN books ON book_id=books.id WHERE user_id = ? ORDER BY rating DESC LIMIT 12";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
            resultSet = preparedStatement.executeQuery();
            List<Book> recommendation = new ArrayList<>();

            if (resultSet == null) {
                throw new DatabaseException("Error returning result.");
            }
            while (resultSet.next()) {
                Long id = resultSet.getLong("book_id");
                String imageL = resultSet.getString("image_url_l");

                recommendation.add(new Book(id, imageL));
            }
            return recommendation;
        } catch (SQLException e) {
            throw new DatabaseException("Problem with ID: " + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(preparedStatement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public boolean deleteRecommended(Long userID) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnector.getConnection();
            String query = "DELETE FROM book_recommendation WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userID);
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
    public boolean addRecommended(Long userID, List<Book> booksToRecommended) throws InputException, DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnector.getConnection();
            String query = "INSERT INTO book_recommendation(user_id, book_id, rating) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            Iterator<Book> it = booksToRecommended.iterator();
            while (it.hasNext()) {
                Book book = it.next();
                preparedStatement.setLong(1, userID);
                preparedStatement.setLong(2, book.getId());
                preparedStatement.setDouble(3, book.getRatingRecommended());
                preparedStatement.executeUpdate();
            }
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
}
