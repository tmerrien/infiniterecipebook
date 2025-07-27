package com.infiniterecipebook.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NutritionalInfo embeddable class.
 */
class NutritionalInfoTest {

    @Test
    void shouldCreateNutritionalInfoWithBuilder() {
        // Given & When
        NutritionalInfo nutritionalInfo = NutritionalInfo.builder()
                .calories(250)
                .protein(15)
                .carbs(30)
                .fat(8)
                .fiber(5)
                .sugar(10)
                .sodium(300)
                .build();

        // Then
        assertNotNull(nutritionalInfo);
        assertEquals(250, nutritionalInfo.getCalories());
        assertEquals(15, nutritionalInfo.getProtein());
        assertEquals(30, nutritionalInfo.getCarbs());
        assertEquals(8, nutritionalInfo.getFat());
        assertEquals(5, nutritionalInfo.getFiber());
        assertEquals(10, nutritionalInfo.getSugar());
        assertEquals(300, nutritionalInfo.getSodium());
    }

    @Test
    void shouldCreateNutritionalInfoWithNoArgsConstructor() {
        // Given & When
        NutritionalInfo nutritionalInfo = new NutritionalInfo();

        // Then
        assertNotNull(nutritionalInfo);
        assertNull(nutritionalInfo.getCalories());
        assertNull(nutritionalInfo.getProtein());
        assertNull(nutritionalInfo.getCarbs());
        assertNull(nutritionalInfo.getFat());
        assertNull(nutritionalInfo.getFiber());
        assertNull(nutritionalInfo.getSugar());
        assertNull(nutritionalInfo.getSodium());
    }

    @Test
    void shouldCreateNutritionalInfoWithAllArgsConstructor() {
        // Given
        Integer calories = 300;
        Integer protein = 20;
        Integer carbs = 40;
        Integer fat = 10;
        Integer fiber = 6;
        Integer sugar = 15;
        Integer sodium = 400;

        // When
        NutritionalInfo nutritionalInfo = new NutritionalInfo(calories, protein, carbs, fat, fiber, sugar, sodium);

        // Then
        assertNotNull(nutritionalInfo);
        assertEquals(calories, nutritionalInfo.getCalories());
        assertEquals(protein, nutritionalInfo.getProtein());
        assertEquals(carbs, nutritionalInfo.getCarbs());
        assertEquals(fat, nutritionalInfo.getFat());
        assertEquals(fiber, nutritionalInfo.getFiber());
        assertEquals(sugar, nutritionalInfo.getSugar());
        assertEquals(sodium, nutritionalInfo.getSodium());
    }

    @Test
    void shouldSetAndGetValues() {
        // Given
        NutritionalInfo nutritionalInfo = new NutritionalInfo();

        // When
        nutritionalInfo.setCalories(200);
        nutritionalInfo.setProtein(12);
        nutritionalInfo.setCarbs(25);
        nutritionalInfo.setFat(6);
        nutritionalInfo.setFiber(4);
        nutritionalInfo.setSugar(8);
        nutritionalInfo.setSodium(250);

        // Then
        assertEquals(200, nutritionalInfo.getCalories());
        assertEquals(12, nutritionalInfo.getProtein());
        assertEquals(25, nutritionalInfo.getCarbs());
        assertEquals(6, nutritionalInfo.getFat());
        assertEquals(4, nutritionalInfo.getFiber());
        assertEquals(8, nutritionalInfo.getSugar());
        assertEquals(250, nutritionalInfo.getSodium());
    }

    @Test
    void shouldHandleNullValues() {
        // Given & When
        NutritionalInfo nutritionalInfo = NutritionalInfo.builder()
                .calories(null)
                .protein(null)
                .carbs(null)
                .fat(null)
                .fiber(null)
                .sugar(null)
                .sodium(null)
                .build();

        // Then
        assertNotNull(nutritionalInfo);
        assertNull(nutritionalInfo.getCalories());
        assertNull(nutritionalInfo.getProtein());
        assertNull(nutritionalInfo.getCarbs());
        assertNull(nutritionalInfo.getFat());
        assertNull(nutritionalInfo.getFiber());
        assertNull(nutritionalInfo.getSugar());
        assertNull(nutritionalInfo.getSodium());
    }

    @Test
    void shouldBeEqualWhenSameValues() {
        // Given
        NutritionalInfo info1 = NutritionalInfo.builder()
                .calories(250)
                .protein(15)
                .carbs(30)
                .fat(8)
                .fiber(5)
                .sugar(10)
                .sodium(300)
                .build();

        NutritionalInfo info2 = NutritionalInfo.builder()
                .calories(250)
                .protein(15)
                .carbs(30)
                .fat(8)
                .fiber(5)
                .sugar(10)
                .sodium(300)
                .build();

        // When & Then
        assertEquals(info1, info2);
        assertEquals(info1.hashCode(), info2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentValues() {
        // Given
        NutritionalInfo info1 = NutritionalInfo.builder()
                .calories(250)
                .protein(15)
                .build();

        NutritionalInfo info2 = NutritionalInfo.builder()
                .calories(300)
                .protein(15)
                .build();

        // When & Then
        assertNotEquals(info1, info2);
        assertNotEquals(info1.hashCode(), info2.hashCode());
    }
} 