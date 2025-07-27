package com.infiniterecipebook.models;

import com.infiniterecipebook.enums.IngredientCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
 * Entity representing an ingredient in the recipe system.
 * Supports normalization and categorization for better recipe matching.
 */
@Entity
@Table(name = "ingredients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Ingredient name is required")
    @Size(max = 255, message = "Ingredient name cannot exceed 255 characters")
    @Column(nullable = false, length = 255)
    private String name;
    
    @Size(max = 255, message = "Normalized name cannot exceed 255 characters")
    @Column(name = "normalized_name", length = 255)
    private String normalizedName;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private IngredientCategory category;
    
    @Column(name = "common_units", columnDefinition = "VARCHAR[]")
    private List<String> commonUnits;
    
    @Column(name = "is_common")
    @Builder.Default
    private Boolean isCommon = false;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();
    
    /**
     * Adds a recipe ingredient to this ingredient's list.
     * @param recipeIngredient the recipe ingredient to add
     */
    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        recipeIngredients.add(recipeIngredient);
        recipeIngredient.setIngredient(this);
    }
    
    /**
     * Removes a recipe ingredient from this ingredient's list.
     * @param recipeIngredient the recipe ingredient to remove
     */
    public void removeRecipeIngredient(RecipeIngredient recipeIngredient) {
        recipeIngredients.remove(recipeIngredient);
        recipeIngredient.setIngredient(null);
    }
} 