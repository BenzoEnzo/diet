package pl.bartus.jakub.diet.application.api.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartus.jakub.diet.application.api.dto.MealDTO;
import pl.bartus.jakub.diet.application.api.mapper.MealMapper;
import pl.bartus.jakub.diet.application.api.repository.MealRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DietService {
    private final MealMapper mealMapper;
    private final MealRepository mealRepository;

    public List<MealDTO> getAll(){
        return mealRepository.findAll()
                .stream()
                .map(mealMapper::toDTO)
                .toList();
    }
}
