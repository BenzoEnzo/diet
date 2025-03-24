package pl.bartus.jakub.diet.meal.domain.collection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.bartus.jakub.diet.meal.domain.model.MealType;
import pl.bartus.jakub.diet.meal.domain.model.ProductMeal;

import java.util.List;

@Document(collection = "meal")
@NoArgsConstructor
@Getter
@Setter
public class Meal {
    @Id
    private String id;
    private String name;
    private MealType mealType;
    private String description;
    private List<ProductMeal> products;
}
