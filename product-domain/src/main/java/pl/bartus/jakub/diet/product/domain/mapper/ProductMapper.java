package pl.bartus.jakub.diet.product.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.bartus.jakub.diet.product.domain.entity.Product;
import pl.bartus.jakub.diet.product.domain.model.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO mapToDTO(Product product);
    @Mapping(target = "id", ignore = true)
    Product mapToEntity(ProductDTO dto);
}
