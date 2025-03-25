package pl.bartus.jakub.diet.application.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartus.jakub.diet.application.api.collection.Meal;
import pl.bartus.jakub.diet.application.api.logic.DietService;
import pl.bartus.jakub.diet.application.api.model.MealSpecificationDTO;

import java.util.List;

@RestController
@RequestMapping("/api/diets")
@RequiredArgsConstructor
public class DietController {
    private final DietService dietService;

    @PostMapping()
    public ResponseEntity<List<Meal>> getAll(@RequestBody MealSpecificationDTO request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dietService.getAll(request));
    }
}
