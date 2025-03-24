package pl.bartus.jakub.diet.application.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "meal")
@NoArgsConstructor
@Getter
@Setter
public class Meal {

    @Id
    @Column(length = 24, nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(length = 50)
    private String mealType;

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
    private Set<Product> products = new HashSet<>();
}