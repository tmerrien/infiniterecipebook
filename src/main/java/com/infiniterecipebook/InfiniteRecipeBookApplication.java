package com.infiniterecipebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for the Infinite Recipe Book API.
 * 
 * This application provides intelligent recipe generation based on available ingredients
 * and meal preferences. It supports meal type categorization, cooking time optimization,
 * and dietary restriction handling.
 * 
 * @author Infinite Recipe Book Team
 * @version 1.0.0
 */
@SpringBootApplication
public class InfiniteRecipeBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfiniteRecipeBookApplication.class, args);
    }
} 