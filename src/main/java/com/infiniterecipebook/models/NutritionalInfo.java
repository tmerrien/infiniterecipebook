package com.infiniterecipebook.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Embeddable class representing nutritional information for recipes.
 * This will be stored as JSONB in the database for flexibility.
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NutritionalInfo {
    private Integer calories;
    private Integer protein;
    private Integer carbs;
    private Integer fat;
    private Integer fiber;
    private Integer sugar;
    private Integer sodium;
} 