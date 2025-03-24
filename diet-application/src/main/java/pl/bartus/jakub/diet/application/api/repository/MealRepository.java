package pl.bartus.jakub.diet.application.api.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.bartus.jakub.diet.application.api.collection.Meal;

@Repository
public interface MealRepository extends MongoRepository<Meal,Long> {
}
