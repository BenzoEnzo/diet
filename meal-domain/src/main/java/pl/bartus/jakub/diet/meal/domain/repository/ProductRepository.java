package pl.bartus.jakub.diet.meal.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.bartus.jakub.diet.meal.domain.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT mp FROM Product mp WHERE mp.name = :name")
    Optional<Product> findByName(@Param("name") String name);
}
