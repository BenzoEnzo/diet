package pl.bartus.jakub.diet.product.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import pl.bartus.jakub.diet.product.domain.model.Currency;
import pl.bartus.jakub.diet.product.domain.model.UnitType;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", length = 3)
    private Currency currency;
    private BigDecimal value;
    @Enumerated(EnumType.STRING)
    @Column(name = "unit", length = 5)
    private UnitType unit;
    private int kcal;
    private float protein;
    private float fat;
    private float carbohydrate;
}
