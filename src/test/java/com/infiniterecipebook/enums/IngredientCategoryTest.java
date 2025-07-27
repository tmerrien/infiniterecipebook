package com.infiniterecipebook.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for IngredientCategory enum.
 */
class IngredientCategoryTest {

    @Test
    void shouldHaveCorrectNumberOfValues() {
        // Given & When
        IngredientCategory[] values = IngredientCategory.values();
        
        // Then
        assertEquals(10, values.length, "IngredientCategory should have exactly 10 values");
    }

    @Test
    void shouldContainAllExpectedValues() {
        // Given & When
        IngredientCategory[] values = IngredientCategory.values();
        
        // Then
        assertTrue(containsValue(values, "PROTEIN"), "Should contain PROTEIN");
        assertTrue(containsValue(values, "VEGETABLE"), "Should contain VEGETABLE");
        assertTrue(containsValue(values, "FRUIT"), "Should contain FRUIT");
        assertTrue(containsValue(values, "GRAIN"), "Should contain GRAIN");
        assertTrue(containsValue(values, "DAIRY"), "Should contain DAIRY");
        assertTrue(containsValue(values, "SEASONING"), "Should contain SEASONING");
        assertTrue(containsValue(values, "COOKING_OIL"), "Should contain COOKING_OIL");
        assertTrue(containsValue(values, "SWEETENER"), "Should contain SWEETENER");
        assertTrue(containsValue(values, "BAKING_INGREDIENT"), "Should contain BAKING_INGREDIENT");
        assertTrue(containsValue(values, "OTHER"), "Should contain OTHER");
    }

    @Test
    void shouldConvertFromStringCorrectly() {
        // Given & When & Then
        assertEquals(IngredientCategory.PROTEIN, IngredientCategory.valueOf("PROTEIN"));
        assertEquals(IngredientCategory.VEGETABLE, IngredientCategory.valueOf("VEGETABLE"));
        assertEquals(IngredientCategory.FRUIT, IngredientCategory.valueOf("FRUIT"));
        assertEquals(IngredientCategory.GRAIN, IngredientCategory.valueOf("GRAIN"));
        assertEquals(IngredientCategory.DAIRY, IngredientCategory.valueOf("DAIRY"));
        assertEquals(IngredientCategory.SEASONING, IngredientCategory.valueOf("SEASONING"));
        assertEquals(IngredientCategory.COOKING_OIL, IngredientCategory.valueOf("COOKING_OIL"));
        assertEquals(IngredientCategory.SWEETENER, IngredientCategory.valueOf("SWEETENER"));
        assertEquals(IngredientCategory.BAKING_INGREDIENT, IngredientCategory.valueOf("BAKING_INGREDIENT"));
        assertEquals(IngredientCategory.OTHER, IngredientCategory.valueOf("OTHER"));
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        // Given & When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            IngredientCategory.valueOf("INVALID");
        });
    }

    private boolean containsValue(IngredientCategory[] values, String expectedValue) {
        for (IngredientCategory value : values) {
            if (value.name().equals(expectedValue)) {
                return true;
            }
        }
        return false;
    }
} 