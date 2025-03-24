package pl.bartus.jakub.diet.product.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.bartus.jakub.diet.product.domain.collection.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
