package pl.bartus.jakub.diet.application.api.collection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class Product {
    private String name;
    private String secondName;
    private String thirdName;
    private String description;
    private Float price;
    private String currency;
    private Float value;
    private String unit;
    private Integer kcal;
    private Float protein;
    private Float fat;
    private Float carbohydrate;
    private Set<Product> similarProducts;
}
