package pl.jedralski.LibraryRecommendationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.jedralski.LibraryRecommendationSystem.dbconnection.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@SpringBootApplication
public class LibraryRecommendationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryRecommendationSystemApplication.class, args);
/*
        Connection connection = null;
        try {
            connection = DBConnector.getConnection();
            Statement statement = connection.createStatement();
            System.out.println("Udało się połączyć z bazą danych.");


        } catch (Exception e) {
            System.out.println("Próba połączenia zakończona niepowodzeniem. " + e.getMessage());
            DBConnector.closeConnection(connection);
            System.exit(0);
        }

        DBConnector.closeConnection(connection);
        System.exit(0);

*/
    }

}
