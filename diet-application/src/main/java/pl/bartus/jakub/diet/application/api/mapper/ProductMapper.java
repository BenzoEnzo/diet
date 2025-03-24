package pl.bartus.jakub.diet.application.api.mapper;

import org.mapstruct.Mapper;
import pl.bartus.jakub.diet.application.api.dto.ProductDTO;
import pl.bartus.jakub.diet.application.api.collection.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO dto);
}
