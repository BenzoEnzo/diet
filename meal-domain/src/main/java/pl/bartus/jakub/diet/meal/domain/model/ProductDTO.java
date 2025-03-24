package pl.bartus.jakub.diet.meal.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String value;
    private UnitType unit;
    private String description;
}
