package com.backend.system.models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import com.backend.system.models.dto.ProductDTO;
import com.backend.system.models.domain.product.Product;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    ProductDTO map(Product product);
}
