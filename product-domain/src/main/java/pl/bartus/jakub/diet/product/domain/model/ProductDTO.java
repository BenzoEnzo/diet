package pl.bartus.jakub.diet.product.domain.model;

import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    @Null
    private Long id;
    private String name;
    private String description;
    private Currency currency;
    private BigDecimal price;
    private BigDecimal value;
    private UnitType unit;
    private int kcal;
    private float protein;
    private float fat;
    private float carbohydrate;
}
