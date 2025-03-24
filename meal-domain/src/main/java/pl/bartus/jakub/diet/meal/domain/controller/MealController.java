package pl.bartus.jakub.diet.meal.domain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartus.jakub.diet.meal.domain.model.MealDTO;
import pl.bartus.jakub.diet.meal.domain.service.MealService;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;
    @GetMapping
    public ResponseEntity<List<MealDTO>> getAll() {
        return ResponseEntity.ok(mealService.getAll());
    }

    @PostMapping
    public ResponseEntity<MealDTO> create(@RequestBody MealDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mealService.create(request));
    }
}
