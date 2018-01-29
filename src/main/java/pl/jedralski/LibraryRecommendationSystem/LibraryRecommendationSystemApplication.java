package pl.jedralski.LibraryRecommendationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.jedralski.LibraryRecommendationSystem.exception.DatabaseException;

@SpringBootApplication
public class LibraryRecommendationSystemApplication {

    public static void main(String[] args) throws DatabaseException {
        SpringApplication.run(LibraryRecommendationSystemApplication.class, args);

    }
}
