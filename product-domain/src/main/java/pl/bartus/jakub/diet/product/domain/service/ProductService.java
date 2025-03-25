package pl.bartus.jakub.diet.product.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartus.jakub.diet.product.domain.entity.Product;
import pl.bartus.jakub.diet.product.domain.model.ProductDTO;
import pl.bartus.jakub.diet.product.domain.model.ProductListDTO;
import pl.bartus.jakub.diet.product.domain.mapper.ProductMapper;
import pl.bartus.jakub.diet.product.domain.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAll(){
        return productRepository.findAll().stream()
                .map(productMapper::mapToDTO)
                .toList();
    }

    public ProductDTO create(ProductDTO dto){
        Product product = productMapper.mapToEntity(dto);

        return productMapper.mapToDTO(productRepository.save(product));
    }

    public List<ProductDTO> createBulk(ProductListDTO request){
        List<Product> products = request.products().stream()
                .map(productMapper::mapToEntity)
                .toList();

        return productRepository.saveAll(products).stream()
                .map(productMapper::mapToDTO)
                .toList();
    }

    public void deleteAll(){
        productRepository.deleteAll();
    }
}
