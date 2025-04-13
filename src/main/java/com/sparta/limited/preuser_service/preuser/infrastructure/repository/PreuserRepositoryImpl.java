package com.sparta.limited.preuser_service.preuser.infrastructure.repository;

import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;
import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;
import com.sparta.limited.preuser_service.preuser.domain.repository.PreuserRepository;
import com.sparta.limited.preuser_service.preuser.infrastructure.persistence.JpaPreuserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PreuserRepositoryImpl implements PreuserRepository {

    private final JpaPreuserRepository jpaPreuserRepository;

    @Override
    public Preuser save(Preuser preuser) {
        return jpaPreuserRepository.save(preuser);
    }

    @Override
    public Preuser findById(UUID preuserId) {
        return jpaPreuserRepository.findById(preuserId)
            .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCES_NOT_FOUND));
    }
}
