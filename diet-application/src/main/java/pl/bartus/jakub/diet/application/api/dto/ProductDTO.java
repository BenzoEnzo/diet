package pl.bartus.jakub.diet.application.api.dto;

import java.math.BigDecimal;
import java.util.Set;

public record ProductDTO(
        String id,
        String name,
        String description,
        BigDecimal price,
        String currency,
        BigDecimal value,
        String unit,
        Integer kcal,
        Float protein,
        Float fat,
        Float carbohydrate,
        Set<ProductDTO> similarProducts){
}
