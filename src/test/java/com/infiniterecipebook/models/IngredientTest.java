package com.infiniterecipebook.models;

import com.infiniterecipebook.enums.IngredientCategory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for Ingredient entity.
 */
@ExtendWith(MockitoExtension.class)
class IngredientTest {

    private Validator validator;

    @Mock
    private RecipeIngredient mockRecipeIngredient;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldCreateIngredientWithBuilder() {
        // Given & When
        Ingredient ingredient = Ingredient.builder()
                .name("Chicken Breast")
                .normalizedName("chicken breast")
                .category(IngredientCategory.PROTEIN)
                .commonUnits(List.of("piece", "pound", "gram"))
                .isCommon(true)
                .build();

        // Then
        assertNotNull(ingredient);
        assertEquals("Chicken Breast", ingredient.getName());
        assertEquals("chicken breast", ingredient.getNormalizedName());
        assertEquals(IngredientCategory.PROTEIN, ingredient.getCategory());
        assertEquals(List.of("piece", "pound", "gram"), ingredient.getCommonUnits());
        assertTrue(ingredient.getIsCommon());
        assertNotNull(ingredient.getRecipeIngredients());
        assertTrue(ingredient.getRecipeIngredients().isEmpty());
    }

    @Test
    void shouldCreateIngredientWithNoArgsConstructor() {
        // Given & When
        Ingredient ingredient = new Ingredient();

        // Then
        assertNotNull(ingredient);
        assertNull(ingredient.getName());
        assertNull(ingredient.getNormalizedName());
        assertNull(ingredient.getCategory());
        assertNull(ingredient.getCommonUnits());
        assertFalse(ingredient.getIsCommon());
        assertNotNull(ingredient.getRecipeIngredients());
        assertTrue(ingredient.getRecipeIngredients().isEmpty());
    }

    @Test
    void shouldSetAndGetValues() {
        // Given
        Ingredient ingredient = new Ingredient();
        LocalDateTime now = LocalDateTime.now();

        // When
        ingredient.setName("Tomato");
        ingredient.setNormalizedName("tomato");
        ingredient.setCategory(IngredientCategory.VEGETABLE);
        ingredient.setCommonUnits(List.of("medium", "large", "small"));
        ingredient.setIsCommon(true);
        ingredient.setCreatedAt(now);
        ingredient.setUpdatedAt(now);

        // Then
        assertEquals("Tomato", ingredient.getName());
        assertEquals("tomato", ingredient.getNormalizedName());
        assertEquals(IngredientCategory.VEGETABLE, ingredient.getCategory());
        assertEquals(List.of("medium", "large", "small"), ingredient.getCommonUnits());
        assertTrue(ingredient.getIsCommon());
        assertEquals(now, ingredient.getCreatedAt());
        assertEquals(now, ingredient.getUpdatedAt());
    }

    @Test
    void shouldValidateRequiredFields() {
        // Given
        Ingredient ingredient = new Ingredient();

        // When
        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void shouldPassValidationWithValidData() {
        // Given
        Ingredient ingredient = Ingredient.builder()
                .name("Salt")
                .normalizedName("salt")
                .category(IngredientCategory.SEASONING)
                .commonUnits(List.of("teaspoon", "tablespoon"))
                .isCommon(true)
                .build();

        // When
        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWithEmptyName() {
        // Given
        Ingredient ingredient = Ingredient.builder()
                .name("")
                .build();

        // When
        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void shouldFailValidationWithNullName() {
        // Given
        Ingredient ingredient = Ingredient.builder()
                .name(null)
                .build();

        // When
        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void shouldFailValidationWithNameTooLong() {
        // Given
        String longName = "a".repeat(256); // 256 characters
        Ingredient ingredient = Ingredient.builder()
                .name(longName)
                .build();

        // When
        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void shouldFailValidationWithNormalizedNameTooLong() {
        // Given
        String longNormalizedName = "a".repeat(256); // 256 characters
        Ingredient ingredient = Ingredient.builder()
                .name("Valid Name")
                .normalizedName(longNormalizedName)
                .build();

        // When
        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("normalizedName")));
    }

    @Test
    void shouldAddRecipeIngredient() {
        // Given
        Ingredient ingredient = new Ingredient();
        RecipeIngredient recipeIngredient = new RecipeIngredient();

        // When
        ingredient.addRecipeIngredient(recipeIngredient);

        // Then
        assertTrue(ingredient.getRecipeIngredients().contains(recipeIngredient));
        assertEquals(ingredient, recipeIngredient.getIngredient());
    }

    @Test
    void shouldRemoveRecipeIngredient() {
        // Given
        Ingredient ingredient = new Ingredient();
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        ingredient.addRecipeIngredient(recipeIngredient);

        // When
        ingredient.removeRecipeIngredient(recipeIngredient);

        // Then
        assertFalse(ingredient.getRecipeIngredients().contains(recipeIngredient));
        assertNull(recipeIngredient.getIngredient());
    }

    @Test
    void shouldBeEqualWhenSameId() {
        // Given
        Ingredient ingredient1 = Ingredient.builder().id(1L).name("Salt").build();
        Ingredient ingredient2 = Ingredient.builder().id(1L).name("Salt").build();

        // When & Then
        assertEquals(ingredient1, ingredient2);
        assertEquals(ingredient1.hashCode(), ingredient2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentIds() {
        // Given
        Ingredient ingredient1 = Ingredient.builder().id(1L).name("Salt").build();
        Ingredient ingredient2 = Ingredient.builder().id(2L).name("Salt").build();

        // When & Then
        assertNotEquals(ingredient1, ingredient2);
        assertNotEquals(ingredient1.hashCode(), ingredient2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenNullId() {
        // Given
        Ingredient ingredient1 = Ingredient.builder().id(1L).name("Salt").build();
        Ingredient ingredient2 = Ingredient.builder().name("Salt").build();

        // When & Then
        assertNotEquals(ingredient1, ingredient2);
        assertNotEquals(ingredient1.hashCode(), ingredient2.hashCode());
    }

    @Test
    void shouldHandleNullRecipeIngredientsList() {
        // Given
        Ingredient ingredient = new Ingredient();
        ingredient.setRecipeIngredients(null);

        // When & Then
        assertNull(ingredient.getRecipeIngredients());
    }

    @Test
    void shouldHandleNullCommonUnits() {
        // Given
        Ingredient ingredient = Ingredient.builder()
                .name("Test Ingredient")
                .commonUnits(null)
                .build();

        // When & Then
        assertNull(ingredient.getCommonUnits());
    }
} 