package pl.bartus.jakub.diet.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartus.jakub.diet.product.domain.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
