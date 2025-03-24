package pl.bartus.jakub.diet.meal.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartus.jakub.diet.meal.domain.collection.Meal;
import pl.bartus.jakub.diet.meal.domain.model.MealDTO;
import pl.bartus.jakub.diet.meal.domain.mapper.MealMapper;
import pl.bartus.jakub.diet.meal.domain.repository.MealRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealMapper mealMapper;
    private final MealRepository mealRepository;

    public List<MealDTO> getAll(){
        return mealRepository.findAll().stream()
                .map(mealMapper::mapToDTO)
                .toList();
    }

    public MealDTO create(MealDTO dto){
        Meal meal =  mealMapper.mapToEntity(dto);

        return mealMapper.mapToDTO(mealRepository.save(meal));
    }
}
