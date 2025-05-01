package com.sparta.limited.preuser_service.preuser.infrastructure.repository;


import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;
import com.sparta.limited.preuser_service.preuser.domain.model.PreuserUser;
import com.sparta.limited.preuser_service.preuser.domain.repository.PreuserUserRepository;
import com.sparta.limited.preuser_service.preuser.infrastructure.persistence.JpaPreuserUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PreuserUserRepositoryImpl implements PreuserUserRepository {
    private final JpaPreuserUserRepository jpaPreuserUserRepository;


    @Override
    public boolean existsByPreuserIdAndUserId(UUID preuserId, Long userId) {
        if (jpaPreuserUserRepository.existsByPreuserIdAndUserId(preuserId, userId)) {
            throw new BusinessException(ErrorCode.DUPLICATE_RESOURCE, "이미 신청한 사용자입니다.");
        }
        return false;
    }

    @Override
    public void save(PreuserUser preuserUser) {
        jpaPreuserUserRepository.save(preuserUser);
    }

    @Override
    public List<PreuserUser> findByPreuserId(UUID preuserId) {
        return jpaPreuserUserRepository.findByPreuserId(preuserId);
    }

    @Override
    public void saveAll(List<PreuserUser> selectedUsers) {
        jpaPreuserUserRepository.saveAll(selectedUsers);
    }

    @Override
    public void existsByPreuserIdAndIsSelectedTrue(UUID preuserId) {
        if (jpaPreuserUserRepository.existsByPreuserIdAndIsSelectedTrue(preuserId)) {
            throw new BusinessException(ErrorCode.DUPLICATE_RESOURCE, "이미 선정한 체험단 입니다");
        }
    }
}
