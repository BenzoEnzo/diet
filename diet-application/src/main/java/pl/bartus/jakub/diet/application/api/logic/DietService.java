package pl.bartus.jakub.diet.application.api.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartus.jakub.diet.application.api.collection.Meal;
import pl.bartus.jakub.diet.application.api.repository.MealRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DietService {
    private final MealRepository mealRepository;

    public List<Meal> getAll(){
        return mealRepository.findAll()
                .stream()
                .toList();
    }
}
