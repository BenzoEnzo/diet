package pl.bartus.jakub.diet.meal.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.bartus.jakub.diet.meal.domain.model.MealType;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "mealType", length = 5)
    private MealType mealType;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Integer kcal;
    private Float protein;
    private Float fat;
    private Float carbohydrate;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToMany
    @JoinTable(
            name = "meal_product",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}
