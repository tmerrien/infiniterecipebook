package com.infiniterecipebook.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MealType enum.
 */
class MealTypeTest {

    @Test
    void shouldHaveCorrectNumberOfValues() {
        // Given & When
        MealType[] values = MealType.values();
        
        // Then
        assertEquals(5, values.length, "MealType should have exactly 5 values");
    }

    @Test
    void shouldContainAllExpectedValues() {
        // Given & When
        MealType[] values = MealType.values();
        
        // Then
        assertTrue(containsValue(values, "BREAKFAST"), "Should contain BREAKFAST");
        assertTrue(containsValue(values, "LUNCH"), "Should contain LUNCH");
        assertTrue(containsValue(values, "DINNER"), "Should contain DINNER");
        assertTrue(containsValue(values, "SNACK"), "Should contain SNACK");
        assertTrue(containsValue(values, "DESSERT"), "Should contain DESSERT");
    }

    @Test
    void shouldConvertFromStringCorrectly() {
        // Given & When & Then
        assertEquals(MealType.BREAKFAST, MealType.valueOf("BREAKFAST"));
        assertEquals(MealType.LUNCH, MealType.valueOf("LUNCH"));
        assertEquals(MealType.DINNER, MealType.valueOf("DINNER"));
        assertEquals(MealType.SNACK, MealType.valueOf("SNACK"));
        assertEquals(MealType.DESSERT, MealType.valueOf("DESSERT"));
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        // Given & When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            MealType.valueOf("INVALID");
        });
    }

    private boolean containsValue(MealType[] values, String expectedValue) {
        for (MealType value : values) {
            if (value.name().equals(expectedValue)) {
                return true;
            }
        }
        return false;
    }
} 