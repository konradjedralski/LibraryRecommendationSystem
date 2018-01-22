package pl.jedralski.LibraryRecommendationSystem.dbconnection;

import java.sql.*;

public class DBConnector {

    public static String driverClassName = "org.postgresql.Driver";
    public static String url = "jdbc:postgresql://localhost/LibraryDatabase";
    public static String username = "postgres";
    public static String password = "konrad";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            System.out.println("Problem with database driver.");
        } catch (SQLException e) {
            System.out.println("Problem with database connection.");
        }
        return connection;
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            resultSet = null;
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            statement = null;
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            connection = null;
        }
    }

}
