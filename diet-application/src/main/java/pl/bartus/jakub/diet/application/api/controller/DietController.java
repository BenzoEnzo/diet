package pl.bartus.jakub.diet.application.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartus.jakub.diet.application.api.dto.MealDTO;
import pl.bartus.jakub.diet.application.api.logic.DietService;

import java.util.List;

@RestController
@RequestMapping("/api/diets")
@RequiredArgsConstructor
public class DietController {
    private final DietService dietService;

    @GetMapping()
    public ResponseEntity<List<MealDTO>> getAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dietService.getAll());
    }
}
