package pl.jedralski.LibraryRecommendationSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class Config {

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost/LibraryDatabase");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("konrad");
        return driverManagerDataSource;
    }
}
