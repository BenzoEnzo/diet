package pl.bartus.jakub.diet.product.domain.collection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.bartus.jakub.diet.product.domain.model.Currency;
import pl.bartus.jakub.diet.product.domain.model.UnitType;

import java.math.BigDecimal;

@Document(collection = "product")
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Currency currency;
    private BigDecimal value;
    private UnitType unit;
    private int kcal;
    private float protein;
    private float fat;
    private float carbohydrate;
}
