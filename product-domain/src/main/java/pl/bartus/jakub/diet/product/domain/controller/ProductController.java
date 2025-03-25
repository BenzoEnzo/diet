package pl.bartus.jakub.diet.product.domain.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartus.jakub.diet.product.domain.model.ProductDTO;
import pl.bartus.jakub.diet.product.domain.model.ProductListDTO;
import pl.bartus.jakub.diet.product.domain.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.create(request));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ProductDTO>> createProducts(@Valid @RequestBody ProductListDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createBulk(request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        productService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(null);

    }
}
