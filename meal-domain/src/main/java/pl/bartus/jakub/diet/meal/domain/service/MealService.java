package pl.bartus.jakub.diet.meal.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartus.jakub.diet.meal.domain.entity.Meal;
import pl.bartus.jakub.diet.meal.domain.model.MealDTO;
import pl.bartus.jakub.diet.meal.domain.mapper.MealMapper;
import pl.bartus.jakub.diet.meal.domain.repository.MealRepository;
import pl.bartus.jakub.diet.meal.domain.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealMapper mealMapper;
    private final MealRepository mealRepository;
    private final ProductRepository productRepository;

    public List<MealDTO> getAll(){
        return mealRepository.findAll().stream()
                .map(mealMapper::mapToDTO)
                .toList();
    }

    @Transactional
    public MealDTO create(MealDTO dto){
        Meal meal =  mealMapper.mapToEntity(dto);

        meal.setProducts(
                meal.getProducts().stream()
                        .map(prod -> productRepository.findByName(prod.getName())
                                .orElseGet(()->productRepository.save(prod)))
                        .toList()
        );

        return mealMapper.mapToDTO(mealRepository.save(meal));
    }
}
