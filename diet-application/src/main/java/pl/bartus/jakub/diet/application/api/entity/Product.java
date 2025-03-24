package pl.bartus.jakub.diet.application.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @Column(length = 24, nullable = false)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(length = 10)
    private String currency;

    @Column(precision = 10, scale = 2)
    private BigDecimal value;

    @Column(length = 50)
    private String unit;

    private Integer kcal;
    private Float protein;
    private Float fat;
    private Float carbohydrate;

    @ManyToMany
    @JoinTable(
            name = "similar_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "similar_product_id")
    )
    private Set<Product> similarProducts = new HashSet<>();
}
