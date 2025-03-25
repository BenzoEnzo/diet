package pl.bartus.jakub.diet.application.api.model;
import java.util.List;

public record MealSpecificationDTO(List<String> mealTypeList,
                                   Integer minKcal,
                                   Integer maxKcal,
                                   float maxPrice) {
}
