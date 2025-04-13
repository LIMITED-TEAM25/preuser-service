package com.sparta.limited.preuser_service.preuser_product.infrastructure.persistence;

import com.sparta.limited.preuser_service.preuser_product.domain.model.PreuserProduct;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPreuserProductRepository extends JpaRepository<PreuserProduct, UUID> {

    Optional<PreuserProduct> findByProductId(UUID productId);
}
