package kz.bitlab.amazon.amazon.mapper;

import kz.bitlab.amazon.amazon.dto.ProductDto;
import kz.bitlab.amazon.amazon.models.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    List<ProductDto> toDtoList(List<Product> products);
}
