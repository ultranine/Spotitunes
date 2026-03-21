package com.ultranine.spotitunes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class SpotitunesApplication {
    private static String username;
    private static String password;

    static void main(String[] args) {
        if (args.length < 2) {
            IO.println("Please enter your credentials for the Postgres database.");
            IO.print("Username: ");
            username = IO.readln();
            IO.println("Password: ");
            password = IO.readln();
        }
        else {
            username = args[0];
            password = args[1];
        }
        dataSource();
        SpringApplication.run(SpotitunesApplication.class, args);
    }

    @Bean
    public static DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public static JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
