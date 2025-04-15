package com.sparta.limited.preuser_service.preuser.infrastructure.repository;


import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;
import com.sparta.limited.preuser_service.preuser.domain.model.PreuserUser;
import com.sparta.limited.preuser_service.preuser.domain.repository.PreuserUserRepository;
import com.sparta.limited.preuser_service.preuser.infrastructure.persistence.JpaPreuserUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PreuserUserRepositoryImpl implements PreuserUserRepository {
    private final JpaPreuserUserRepository jpaPreuserUserRepository;


    @Override
    public void existsByPreuserIdAndUserId(UUID preuserId, Long userId) {
        if (jpaPreuserUserRepository.existsByPreuserIdAndUserId(preuserId, userId)) {
            throw new BusinessException(ErrorCode.DUPLICATE_RESOURCE, "이미 신청한 사용자입니다.");
        }
    }

    @Override
    public void save(PreuserUser preuserUser) {
        jpaPreuserUserRepository.save(preuserUser);
    }
}
