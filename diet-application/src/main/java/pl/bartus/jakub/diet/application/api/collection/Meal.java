package pl.bartus.jakub.diet.application.api.collection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "Meal")
@NoArgsConstructor
@Getter
@Setter
public class Meal {
    @Id
    private String id;
    private String name;
    private String mealType;
    private String description;
    private Integer kcal;
    private Float protein;
    private Float fat;
    private Float carbohydrate;
    private BigDecimal price;
    private List<Product> products;
}