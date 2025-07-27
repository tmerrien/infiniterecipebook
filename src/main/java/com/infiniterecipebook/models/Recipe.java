package com.infiniterecipebook.models;

import com.infiniterecipebook.enums.Difficulty;
import com.infiniterecipebook.enums.MealType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a recipe in the system.
 * Contains all recipe metadata, ingredients, and instructions.
 */
@Entity
@Table(name = "recipes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Recipe name is required")
    @Size(max = 255, message = "Recipe name cannot exceed 255 characters")
    @Column(nullable = false, length = 255)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @NotNull(message = "Meal type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;
    
    @Positive(message = "Cooking time must be positive")
    @Column(name = "cooking_time_minutes")
    private Integer cookingTimeMinutes;
    
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    
    @Positive(message = "Servings must be positive")
    private Integer servings;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "calories", column = @Column(name = "nutritional_calories")),
        @AttributeOverride(name = "protein", column = @Column(name = "nutritional_protein")),
        @AttributeOverride(name = "carbs", column = @Column(name = "nutritional_carbs")),
        @AttributeOverride(name = "fat", column = @Column(name = "nutritional_fat")),
        @AttributeOverride(name = "fiber", column = @Column(name = "nutritional_fiber")),
        @AttributeOverride(name = "sugar", column = @Column(name = "nutritional_sugar")),
        @AttributeOverride(name = "sodium", column = @Column(name = "nutritional_sodium"))
    })
    private NutritionalInfo nutritionalInfo;
    
    @Column(columnDefinition = "TEXT[]")
    private List<String> tags;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<RecipeIngredient> ingredients = new ArrayList<>();
    
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("stepOrder ASC")
    @Builder.Default
    private List<RecipeInstruction> instructions = new ArrayList<>();
    
    /**
     * Adds an ingredient to this recipe.
     * @param recipeIngredient the recipe ingredient to add
     */
    public void addIngredient(RecipeIngredient recipeIngredient) {
        ingredients.add(recipeIngredient);
        recipeIngredient.setRecipe(this);
    }
    
    /**
     * Removes an ingredient from this recipe.
     * @param recipeIngredient the recipe ingredient to remove
     */
    public void removeIngredient(RecipeIngredient recipeIngredient) {
        ingredients.remove(recipeIngredient);
        recipeIngredient.setRecipe(null);
    }
    
    /**
     * Adds an instruction to this recipe.
     * @param recipeInstruction the recipe instruction to add
     */
    public void addInstruction(RecipeInstruction recipeInstruction) {
        instructions.add(recipeInstruction);
        recipeInstruction.setRecipe(this);
    }
    
    /**
     * Removes an instruction from this recipe.
     * @param recipeInstruction the recipe instruction to remove
     */
    public void removeInstruction(RecipeInstruction recipeInstruction) {
        instructions.remove(recipeInstruction);
        recipeInstruction.setRecipe(null);
    }
} 