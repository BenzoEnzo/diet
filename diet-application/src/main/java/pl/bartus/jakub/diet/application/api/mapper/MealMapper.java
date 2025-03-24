package pl.bartus.jakub.diet.application.api.mapper;

import org.mapstruct.Mapper;
import pl.bartus.jakub.diet.application.api.dto.MealDTO;
import pl.bartus.jakub.diet.application.api.entity.Meal;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface MealMapper {
    MealDTO toDTO(Meal meal);
    Meal toEntity(MealDTO dto);
}
