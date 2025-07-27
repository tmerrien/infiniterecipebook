package com.infiniterecipebook.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Difficulty enum.
 */
class DifficultyTest {

    @Test
    void shouldHaveCorrectNumberOfValues() {
        // Given & When
        Difficulty[] values = Difficulty.values();
        
        // Then
        assertEquals(3, values.length, "Difficulty should have exactly 3 values");
    }

    @Test
    void shouldContainAllExpectedValues() {
        // Given & When
        Difficulty[] values = Difficulty.values();
        
        // Then
        assertTrue(containsValue(values, "EASY"), "Should contain EASY");
        assertTrue(containsValue(values, "MEDIUM"), "Should contain MEDIUM");
        assertTrue(containsValue(values, "HARD"), "Should contain HARD");
    }

    @Test
    void shouldConvertFromStringCorrectly() {
        // Given & When & Then
        assertEquals(Difficulty.EASY, Difficulty.valueOf("EASY"));
        assertEquals(Difficulty.MEDIUM, Difficulty.valueOf("MEDIUM"));
        assertEquals(Difficulty.HARD, Difficulty.valueOf("HARD"));
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        // Given & When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            Difficulty.valueOf("INVALID");
        });
    }

    private boolean containsValue(Difficulty[] values, String expectedValue) {
        for (Difficulty value : values) {
            if (value.name().equals(expectedValue)) {
                return true;
            }
        }
        return false;
    }
} 