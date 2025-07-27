package com.infiniterecipebook.models;

import com.infiniterecipebook.enums.Difficulty;
import com.infiniterecipebook.enums.MealType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Recipe entity.
 */
class RecipeTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldCreateRecipeWithBuilder() {
        // Given & When
        Recipe recipe = Recipe.builder()
                .name("Chicken and Rice")
                .description("A simple and delicious chicken and rice dish")
                .mealType(MealType.DINNER)
                .cookingTimeMinutes(30)
                .difficulty(Difficulty.EASY)
                .servings(4)
                .tags(List.of("quick", "healthy", "simple"))
                .build();

        // Then
        assertNotNull(recipe);
        assertEquals("Chicken and Rice", recipe.getName());
        assertEquals("A simple and delicious chicken and rice dish", recipe.getDescription());
        assertEquals(MealType.DINNER, recipe.getMealType());
        assertEquals(30, recipe.getCookingTimeMinutes());
        assertEquals(Difficulty.EASY, recipe.getDifficulty());
        assertEquals(4, recipe.getServings());
        assertEquals(List.of("quick", "healthy", "simple"), recipe.getTags());
        assertNotNull(recipe.getIngredients());
        assertTrue(recipe.getIngredients().isEmpty());
        assertNotNull(recipe.getInstructions());
        assertTrue(recipe.getInstructions().isEmpty());
    }

    @Test
    void shouldCreateRecipeWithNoArgsConstructor() {
        // Given & When
        Recipe recipe = new Recipe();

        // Then
        assertNotNull(recipe);
        assertNull(recipe.getName());
        assertNull(recipe.getDescription());
        assertNull(recipe.getMealType());
        assertNull(recipe.getCookingTimeMinutes());
        assertNull(recipe.getDifficulty());
        assertNull(recipe.getServings());
        assertNull(recipe.getTags());
        assertNotNull(recipe.getIngredients());
        assertTrue(recipe.getIngredients().isEmpty());
        assertNotNull(recipe.getInstructions());
        assertTrue(recipe.getInstructions().isEmpty());
    }

    @Test
    void shouldValidateRequiredFields() {
        // Given
        Recipe recipe = new Recipe();

        // When
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("mealType")));
    }

    @Test
    void shouldPassValidationWithValidData() {
        // Given
        Recipe recipe = Recipe.builder()
                .name("Test Recipe")
                .mealType(MealType.BREAKFAST)
                .cookingTimeMinutes(15)
                .servings(2)
                .build();

        // When
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWithEmptyName() {
        // Given
        Recipe recipe = Recipe.builder()
                .name("")
                .mealType(MealType.BREAKFAST)
                .build();

        // When
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void shouldFailValidationWithNullMealType() {
        // Given
        Recipe recipe = Recipe.builder()
                .name("Test Recipe")
                .mealType(null)
                .build();

        // When
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("mealType")));
    }

    @Test
    void shouldFailValidationWithNegativeCookingTime() {
        // Given
        Recipe recipe = Recipe.builder()
                .name("Test Recipe")
                .mealType(MealType.BREAKFAST)
                .cookingTimeMinutes(-5)
                .build();

        // When
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("cookingTimeMinutes")));
    }

    @Test
    void shouldFailValidationWithNegativeServings() {
        // Given
        Recipe recipe = Recipe.builder()
                .name("Test Recipe")
                .mealType(MealType.BREAKFAST)
                .servings(-2)
                .build();

        // When
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("servings")));
    }

    @Test
    void shouldAddAndRemoveIngredients() {
        // Given
        Recipe recipe = new Recipe();
        RecipeIngredient ingredient = new RecipeIngredient();

        // When
        recipe.addIngredient(ingredient);

        // Then
        assertTrue(recipe.getIngredients().contains(ingredient));
        assertEquals(recipe, ingredient.getRecipe());

        // When removing
        recipe.removeIngredient(ingredient);

        // Then
        assertFalse(recipe.getIngredients().contains(ingredient));
        assertNull(ingredient.getRecipe());
    }

    @Test
    void shouldAddAndRemoveInstructions() {
        // Given
        Recipe recipe = new Recipe();
        RecipeInstruction instruction = new RecipeInstruction();

        // When
        recipe.addInstruction(instruction);

        // Then
        assertTrue(recipe.getInstructions().contains(instruction));
        assertEquals(recipe, instruction.getRecipe());

        // When removing
        recipe.removeInstruction(instruction);

        // Then
        assertFalse(recipe.getInstructions().contains(instruction));
        assertNull(instruction.getRecipe());
    }

    @Test
    void shouldHandleNutritionalInfo() {
        // Given
        NutritionalInfo nutritionalInfo = NutritionalInfo.builder()
                .calories(250)
                .protein(15)
                .carbs(30)
                .fat(8)
                .build();

        Recipe recipe = Recipe.builder()
                .name("Test Recipe")
                .mealType(MealType.BREAKFAST)
                .nutritionalInfo(nutritionalInfo)
                .build();

        // Then
        assertNotNull(recipe.getNutritionalInfo());
        assertEquals(250, recipe.getNutritionalInfo().getCalories());
        assertEquals(15, recipe.getNutritionalInfo().getProtein());
        assertEquals(30, recipe.getNutritionalInfo().getCarbs());
        assertEquals(8, recipe.getNutritionalInfo().getFat());
    }
} 