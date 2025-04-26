package com.sparta.limited.preuser_service.preuser_product.domain.repository;

import com.sparta.limited.preuser_service.preuser_product.domain.model.PreuserProduct;

import java.util.UUID;

public interface PreuserProductRepository {

    void save(PreuserProduct preuserProduct);

    void findByProductId(UUID productId);

    PreuserProduct findById(UUID preuserProductId);

}
