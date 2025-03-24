package pl.bartus.jakub.diet.application.api.dto;

import java.math.BigDecimal;
import java.util.Set;

public record MealDTO (
        String id,
        String name,
        String mealType,
        String description,
        Integer kcal,
        Float protein,
        Float fat,
        Float carbohydrate,
        BigDecimal price,
        Set<ProductDTO> products
) {
}
