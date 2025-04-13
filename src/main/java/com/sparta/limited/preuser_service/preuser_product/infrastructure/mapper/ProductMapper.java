package com.sparta.limited.preuser_service.preuser_product.infrastructure.mapper;

import com.sparta.limited.preuser_service.preuser_product.domain.model.PreuserProduct;
import com.sparta.limited.preuser_service.preuser_product.infrastructure.dto.response.ProductReadResponse;

public class ProductMapper {

    public static PreuserProduct toEntity(ProductReadResponse response, int quantity) {
        return PreuserProduct.ofFegin(
            response.getTitle(),
            response.getDescription(),
            response.getPrice(),
            response.getId(),
            quantity
        );
    }


}
