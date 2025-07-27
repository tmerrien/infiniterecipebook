package com.infiniterecipebook;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Basic Spring Boot test to verify application context loading.
 */
@SpringBootTest
@ActiveProfiles("test")
class InfiniteRecipeBookApplicationTests {

    @Test
    void contextLoads() {
        // This test verifies that the Spring application context loads successfully
    }
} 