package pl.bartus.jakub.diet.application.api.collection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Set;

@Document
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String currency;
    private BigDecimal value;
    private String unit;
    private Integer kcal;
    private Float protein;
    private Float fat;
    private Float carbohydrate;
    private Set<Product> similarProducts;
}
