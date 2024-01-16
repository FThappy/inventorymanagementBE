package com.example.mockbe.mapper;

import com.example.mockbe.dto.ProductDto;
import com.example.mockbe.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);
    ProductDto toProductDto(Product product);
    Product toProduct(ProductDto productDto);
}
