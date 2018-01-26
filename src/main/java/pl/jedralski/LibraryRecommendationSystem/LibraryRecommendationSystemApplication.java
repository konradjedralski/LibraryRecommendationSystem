package pl.jedralski.LibraryRecommendationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class LibraryRecommendationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryRecommendationSystemApplication.class, args);
     /*

        int i = 0;

            String password = "Iza";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);

            System.out.println(hashedPassword);
            i++;
*/
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
