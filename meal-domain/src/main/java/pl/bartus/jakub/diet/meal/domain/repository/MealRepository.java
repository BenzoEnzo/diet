package pl.bartus.jakub.diet.meal.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartus.jakub.diet.meal.domain.entity.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, String> {
}
