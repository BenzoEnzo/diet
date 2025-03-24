package pl.bartus.jakub.diet.meal.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.bartus.jakub.diet.meal.domain.collection.Meal;

@Repository
public interface MealRepository extends MongoRepository<Meal, String> {
}
