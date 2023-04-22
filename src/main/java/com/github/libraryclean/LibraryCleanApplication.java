package com.github.libraryclean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class LibraryCleanApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryCleanApplication.class, args);
    }

}
