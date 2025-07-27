package com.infiniterecipebook.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Junction entity representing the many-to-many relationship between recipes and ingredients.
 * Contains additional metadata like quantity, unit, and whether the ingredient is required.
 */
@Entity
@Table(name = "recipe_ingredients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Recipe is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;
    
    @NotNull(message = "Ingredient is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;
    
    @Positive(message = "Quantity must be positive")
    @Column(precision = 10, scale = 2)
    private BigDecimal quantity;
    
    @Size(max = 50, message = "Unit cannot exceed 50 characters")
    @Column(length = 50)
    private String unit;
    
    @Column(name = "is_required")
    @Builder.Default
    private Boolean isRequired = true;
    
    @Column(name = "step_order")
    private Integer stepOrder;
} 