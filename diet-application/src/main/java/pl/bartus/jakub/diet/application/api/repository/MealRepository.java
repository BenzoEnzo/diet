package pl.bartus.jakub.diet.application.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartus.jakub.diet.application.api.entity.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal,Long> {
}
