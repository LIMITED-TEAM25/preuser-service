package com.sparta.limited.preuser_service.preuser_product.infrastructure.repositroy;

import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;
import com.sparta.limited.preuser_service.preuser_product.domain.model.PreuserProduct;
import com.sparta.limited.preuser_service.preuser_product.domain.repository.PreuserProductRepository;
import com.sparta.limited.preuser_service.preuser_product.infrastructure.persistence.JpaPreuserProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PreuserProductRepositoryImpl implements PreuserProductRepository {

    private final JpaPreuserProductRepository jpaPreuserProductRepository;


    @Override
    public void save(PreuserProduct preuserProduct) {
        jpaPreuserProductRepository.save(preuserProduct);
    }

    @Override
    public void findByProductId(UUID productId) {
        jpaPreuserProductRepository.findByProductId(productId)
                .ifPresent(jpaPreuserProduct -> {
                    throw new BusinessException(ErrorCode.DUPLICATE_RESOURCE);
                });
    }

    @Override
    public PreuserProduct findById(UUID preuserProductId) {
        return jpaPreuserProductRepository.findById(preuserProductId)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCES_NOT_FOUND));
    }


}
