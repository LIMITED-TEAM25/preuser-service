package com.sparta.limited.preuser_service.preuser_product.application.mapper;

import com.sparta.limited.preuser_service.preuser_product.application.dto.request.PreuserProductCreateRequest;
import com.sparta.limited.preuser_service.preuser_product.application.dto.response.PreuserProductCreateResponse;
import com.sparta.limited.preuser_service.preuser_product.application.dto.response.PreuserProductGetResponse;
import com.sparta.limited.preuser_service.preuser_product.domain.model.PreuserProduct;

public class PreuserProductMapper {

    public static PreuserProductCreateResponse toPreuserProductCreateResponse(
            PreuserProduct preuserProduct) {
        return PreuserProductCreateResponse.of(
                preuserProduct.getProductId(),
                preuserProduct.getTitle(),
                preuserProduct.getDescription()
        );
    }

    public static PreuserProductGetResponse toPreuserProductGetResponse(
            PreuserProduct preuserProduct) {
        return PreuserProductGetResponse.of(
                preuserProduct.getId(),
                preuserProduct.getProductId(),
                preuserProduct.getTitle(),
                preuserProduct.getDescription(),
                preuserProduct.getQuantity(),
                preuserProduct.getPrice()
        );
    }

    public static PreuserProduct toEntity(
            PreuserProductCreateRequest request) {
        return PreuserProduct.of(
                request.getProductId(),
                request.getQuantity()
        );
    }
}
