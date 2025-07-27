package com.infiniterecipebook.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entity tracking commonly used ingredients for quick access and suggestions.
 * Links to the main Ingredient entity and tracks popularity.
 */
@Entity
@Table(name = "common_ingredients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonIngredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Ingredient is required")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;
    
    @PositiveOrZero(message = "Popularity score must be non-negative")
    @Column(name = "popularity_score")
    @Builder.Default
    private Integer popularityScore = 0;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
} 