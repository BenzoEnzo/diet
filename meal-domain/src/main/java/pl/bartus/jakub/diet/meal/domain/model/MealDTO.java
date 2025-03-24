package pl.bartus.jakub.diet.meal.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MealDTO {
    private String id;
    private String name;
    private MealType mealType;
    private String description;
    private List<ProductMeal> products;
}
