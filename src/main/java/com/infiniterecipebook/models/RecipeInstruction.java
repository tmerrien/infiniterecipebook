package com.infiniterecipebook.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing individual cooking instructions for a recipe.
 * Each instruction represents a step in the cooking process.
 */
@Entity
@Table(name = "recipe_instructions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeInstruction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Recipe is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;
    
    @Positive(message = "Step number must be positive")
    @Column(name = "step_number")
    private Integer stepNumber;
    
    @NotBlank(message = "Instruction description is required")
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "step_order")
    private Integer stepOrder;
} 