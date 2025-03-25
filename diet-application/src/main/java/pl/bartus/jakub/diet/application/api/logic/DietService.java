package pl.bartus.jakub.diet.application.api.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pl.bartus.jakub.diet.application.api.collection.Meal;
import pl.bartus.jakub.diet.application.api.model.MealSpecificationDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DietService {
    private final MongoTemplate mongoTemplate;

    public List<Meal> getAll(MealSpecificationDTO spec) {
        Query query = new Query();

        if (spec.mealTypeList() != null) {
            query.addCriteria(Criteria.where("mealType").in(spec.mealTypeList()));
        }

        if (spec.minKcal() != null || spec.maxKcal() != null) {
            Criteria kcalCriteria = Criteria.where("kcal");
            if (spec.minKcal() != null) {
                kcalCriteria.gte(spec.minKcal());
            }
            if (spec.maxKcal() != null) {
                kcalCriteria.lte(spec.maxKcal());
            }
            query.addCriteria(kcalCriteria);
        }

        if (spec.maxPrice() != 0) {
            query.addCriteria(Criteria.where("price").lte(spec.maxPrice()));
        }

        return mongoTemplate.find(query, Meal.class);
    }
}
