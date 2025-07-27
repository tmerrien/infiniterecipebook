# Infinite Recipe Book - API Specification

## Overview
The Infinite Recipe Book API provides intelligent recipe generation based on available ingredients and meal preferences. This document outlines the RESTful API endpoints, request/response models, and data structures.

## Base URL
```
https://api.infiniterecipebook.com/v1
```

## Authentication
Currently, the API operates without authentication for simplicity. Future versions may include user authentication.

## Data Models

### Ingredient
```json
{
  "id": "string",
  "name": "string",
  "quantity": "number",
  "unit": "string",
  "category": "string"
}
```

### Recipe
```json
{
  "id": "string",
  "name": "string",
  "description": "string",
  "mealType": "BREAKFAST|LUNCH|DINNER",
  "cookingTime": "number",
  "difficulty": "EASY|MEDIUM|HARD",
  "servings": "number",
  "ingredients": [
    {
      "name": "string",
      "quantity": "number",
      "unit": "string",
      "isRequired": "boolean"
    }
  ],
  "instructions": [
    {
      "step": "number",
      "description": "string"
    }
  ],
  "nutritionalInfo": {
    "calories": "number",
    "protein": "number",
    "carbs": "number",
    "fat": "number"
  },
  "tags": ["string"],
  "createdAt": "datetime"
}
```

### RecipeRequest
```json
{
  "ingredients": [
    {
      "name": "string",
      "quantity": "number",
      "unit": "string"
    }
  ],
  "mealType": "BREAKFAST|LUNCH|DINNER",
  "preferences": {
    "maxCookingTime": "number",
    "difficulty": "EASY|MEDIUM|HARD",
    "servings": "number",
    "dietaryRestrictions": ["string"]
  }
}
```

### RecipeResponse
```json
{
  "recipes": [
    {
      "id": "string",
      "name": "string",
      "description": "string",
      "mealType": "BREAKFAST|LUNCH|DINNER",
      "cookingTime": "number",
      "difficulty": "EASY|MEDIUM|HARD",
      "servings": "number",
      "ingredients": [
        {
          "name": "string",
          "quantity": "number",
          "unit": "string",
          "isRequired": "boolean",
          "isAvailable": "boolean"
        }
      ],
      "instructions": [
        {
          "step": "number",
          "description": "string"
        }
      ],
      "nutritionalInfo": {
        "calories": "number",
        "protein": "number",
        "carbs": "number",
        "fat": "number"
      },
      "tags": ["string"],
      "confidence": "number"
    }
  ],
  "metadata": {
    "totalRecipes": "number",
    "generationTime": "number",
    "source": "LLM|MOCK"
  }
}
```

## API Endpoints

### 1. Generate Recipes
Generate recipes based on available ingredients and preferences.

**Endpoint:** `POST /recipes/generate`

**Request Body:**
```json
{
  "ingredients": [
    {
      "name": "chicken breast",
      "quantity": 2,
      "unit": "pieces"
    },
    {
      "name": "rice",
      "quantity": 1,
      "unit": "cup"
    }
  ],
  "mealType": "DINNER",
  "preferences": {
    "maxCookingTime": 30,
    "difficulty": "EASY",
    "servings": 2,
    "dietaryRestrictions": ["gluten-free"]
  }
}
```

**Response:**
```json
{
  "recipes": [
    {
      "id": "recipe-001",
      "name": "Simple Chicken and Rice",
      "description": "A quick and easy chicken and rice dish using your available ingredients.",
      "mealType": "DINNER",
      "cookingTime": 25,
      "difficulty": "EASY",
      "servings": 2,
      "ingredients": [
        {
          "name": "chicken breast",
          "quantity": 2,
          "unit": "pieces",
          "isRequired": true,
          "isAvailable": true
        },
        {
          "name": "rice",
          "quantity": 1,
          "unit": "cup",
          "isRequired": true,
          "isAvailable": true
        },
        {
          "name": "salt",
          "quantity": 1,
          "unit": "teaspoon",
          "isRequired": true,
          "isAvailable": false
        }
      ],
      "instructions": [
        {
          "step": 1,
          "description": "Season chicken breasts with salt and pepper"
        },
        {
          "step": 2,
          "description": "Cook rice according to package instructions"
        }
      ],
      "nutritionalInfo": {
        "calories": 450,
        "protein": 35,
        "carbs": 45,
        "fat": 8
      },
      "tags": ["quick", "healthy", "simple"],
      "confidence": 0.95
    }
  ],
  "metadata": {
    "totalRecipes": 1,
    "generationTime": 2.3,
    "source": "LLM"
  }
}
```

### 2. Get Recipe by ID
Retrieve a specific recipe by its ID.

**Endpoint:** `GET /recipes/{id}`

**Response:**
```json
{
  "id": "recipe-001",
  "name": "Simple Chicken and Rice",
  "description": "A quick and easy chicken and rice dish.",
  "mealType": "DINNER",
  "cookingTime": 25,
  "difficulty": "EASY",
  "servings": 2,
  "ingredients": [...],
  "instructions": [...],
  "nutritionalInfo": {...},
  "tags": ["quick", "healthy"],
  "createdAt": "2024-01-15T10:30:00Z"
}
```

### 3. Get Common Ingredients
Retrieve a list of common household ingredients for reference.

**Endpoint:** `GET /ingredients/common`

**Response:**
```json
{
  "ingredients": [
    {
      "id": "salt",
      "name": "salt",
      "category": "seasoning",
      "commonUnits": ["teaspoon", "tablespoon", "pinch"]
    },
    {
      "id": "pepper",
      "name": "black pepper",
      "category": "seasoning",
      "commonUnits": ["teaspoon", "tablespoon", "pinch"]
    },
    {
      "id": "olive-oil",
      "name": "olive oil",
      "category": "cooking-oil",
      "commonUnits": ["tablespoon", "cup", "teaspoon"]
    }
  ]
}
```

### 4. Validate Ingredients
Validate and normalize ingredient names.

**Endpoint:** `POST /ingredients/validate`

**Request Body:**
```json
{
  "ingredients": [
    {
      "name": "chicken breast",
      "quantity": 2,
      "unit": "pieces"
    }
  ]
}
```

**Response:**
```json
{
  "validatedIngredients": [
    {
      "originalName": "chicken breast",
      "normalizedName": "chicken breast",
      "isValid": true,
      "suggestions": ["chicken breast", "chicken fillet"],
      "category": "protein"
    }
  ]
}
```

## Error Responses

### Standard Error Format
```json
{
  "error": {
    "code": "string",
    "message": "string",
    "details": "object"
  }
}
```

### Common Error Codes
- `400` - Bad Request (invalid input)
- `404` - Recipe not found
- `422` - Unprocessable Entity (validation errors)
- `500` - Internal Server Error
- `503` - Service Unavailable (LLM service down)

### Example Error Response
```json
{
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Invalid ingredient format",
    "details": {
      "field": "ingredients[0].quantity",
      "issue": "Quantity must be a positive number"
    }
  }
}
```

## Rate Limiting
- 100 requests per hour per IP address
- 10 requests per minute per IP address

## Versioning
API versioning is handled through the URL path (`/v1/`). Future versions will be available at `/v2/`, `/v3/`, etc.

## Support
For API support and questions, please refer to the project documentation or create an issue in the repository.

---

**Document Version**: 1.0  
**Created**: July 26, 2025  
**Last Updated**: July 26, 2025 