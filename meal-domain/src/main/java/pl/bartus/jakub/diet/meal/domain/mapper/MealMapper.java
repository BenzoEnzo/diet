package pl.bartus.jakub.diet.meal.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.bartus.jakub.diet.meal.domain.entity.Meal;
import pl.bartus.jakub.diet.meal.domain.model.MealDTO;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface MealMapper {
    MealDTO mapToDTO(Meal meal);
    @Mapping(target = "id", ignore = true)
    Meal mapToEntity(MealDTO mealDTO);
}
